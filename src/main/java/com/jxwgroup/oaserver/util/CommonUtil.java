package com.jxwgroup.oaserver.util;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class CommonUtil {

    public static List<Map<String,Object>> createBindings(String str){
        List<Map<String,Object>> bindings = new ArrayList<>();
        for (String key : str.split(";")) {
            Map<String,Object> binding = new HashMap<>();
            binding.put("header", key.split("@")[1]);
            binding.put("binding", key.split("@")[0]);
            bindings.add(binding);
        }
        return bindings;
    }

}
