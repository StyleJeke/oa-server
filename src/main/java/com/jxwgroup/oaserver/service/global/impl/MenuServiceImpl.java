package com.jxwgroup.oaserver.service.global.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jxwgroup.oaserver.config.ProjectConfig;
import com.jxwgroup.oaserver.entity.global.Menu;
import com.jxwgroup.oaserver.mapper.global.MenuMapper;
import com.jxwgroup.oaserver.service.global.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxwgroup.oaserver.util.GsonUtil;
import com.jxwgroup.oaserver.util.JSONUtil;
import com.jxwgroup.oaserver.vo.system.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.jxwgroup.oaserver.util.JSONUtil.extractField;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author 追梦
 * @since 2024-01-24
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    ProjectConfig projectConfig;

    @Override
    public List<Menu> findList() {
        return buildTree(menuMapper.findList());
    }


    // 保存树形结构的菜单，先保存父菜单，再保存子菜单
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void operate(Menu allMenus, UserDTO userDTO) {
        saveOrUpdate(allMenus);
    }

    @Override
    public void removeMenuById(String id) {
        //查询出底下所有的子菜单
        List<Menu> list = menuMapper.selectList(new QueryWrapper<Menu>().eq("parent_id", id));
        List<Menu> menuList = new ArrayList<>();
        menuList.addAll(list);
        for (Menu menu : list) {
            menuList.addAll(menuMapper.selectList(new QueryWrapper<Menu>().eq("parent_id", menu.getId())));
        }
        if(CollectionUtils.isNotEmpty(menuList)){
            menuMapper.deleteBatchIds(menuList.stream().map(Menu::getId).collect(Collectors.toList()));
        }
        //删除主菜单
        menuMapper.deleteById(id);
    }

    @Override
    public List<Map<String, String>> getFirstMenu(String search) {
        return menuMapper.getFirstMenu("'%" + search + "%'");
    }

    public Map<String,Object> getUserRoutes(){
        List<Menu> list = menuMapper.findList();
        for (Menu menu : list) {
            Menu meta = new Menu();
            meta.setTitle(menu.getTitle());
            meta.setIcon(menu.getIcon());
            meta.setI18nTitle(menu.getI18nTitle());
            meta.setRequiresAuth(menu.getRequiresAuth());
            menu.setMeta(meta);
            menu.setOrder(menu.getDisplayOrder());
        }
        List<Menu> menus = buildTree(list);
        Map<String,Object> map = new HashMap<>();
        map.put("home",projectConfig.getIndexPath());
        map.put("routes",menus);
        return map;
    }

    @Override
    public List<String> demo() {
        try {
            // 1. 读取JSON文件
            File file = new File("D:\\后端专用文件夹\\oa-server\\src\\main\\resources\\json\\carbon.json");
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(file);

            // 2. 提取字段并合并成列表
            List<String> extractedValues = JSONUtil.extractField(rootNode, "name");

            // 使用 StringBuilder 手动拼接，添加单引号
            StringBuilder stringBuilder = new StringBuilder();
            for (String item : extractedValues) {
                stringBuilder.append("'").append(item).append("', ");
            }

            // 删除末尾多余的逗号和空格
            if (stringBuilder.length() > 0) {
                stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            }

            // 打印或使用结果字符串
            System.out.println(stringBuilder.toString());

            // 3. 打印或使用合并后的列表
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private List<Menu> buildTree(List<Menu> allMenus) {
        Map<String, Menu> menuMap = new HashMap<>();

        // 将所有菜单放入map，key为菜单ID
        for (Menu menu : allMenus) {
            menuMap.put(menu.getId(), menu);
        }

        List<Menu> rootMenus = new ArrayList<>();

        // 遍历所有菜单，找到根菜单
        for (Menu menu : allMenus) {
            String parentId = menu.getParentId();

            if (parentId == null || !menuMap.containsKey(parentId)) {
                // 如果parentId为空或者在map中找不到对应的父菜单，说明是根菜单
                rootMenus.add(buildTreeRecursive(menu, menuMap));
            }
        }

        return rootMenus;
    }

    private Menu buildTreeRecursive(Menu currentMenu, Map<String, Menu> menuMap) {
        String menuId = currentMenu.getId();

        if (currentMenu.getChildren() == null) {
            currentMenu.setChildren(new ArrayList<>());
        }

        // 获取当前菜单的子菜单IDs
        List<String> childMenuIds = new ArrayList<>();
        for (Menu menu : menuMap.values()) {
            if (menu.getParentId() != null && menu.getParentId().equals(menuId)) {
                childMenuIds.add(menu.getId());
            }
        }

        // 递归构建子菜单
        for (String childMenuId : childMenuIds) {
            Menu childMenu = menuMap.get(childMenuId);
            currentMenu.getChildren().add(buildTreeRecursive(childMenu, menuMap));
        }

        return currentMenu;
    }
}
