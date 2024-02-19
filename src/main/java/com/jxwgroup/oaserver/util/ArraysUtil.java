package com.jxwgroup.oaserver.util;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class ArraysUtil {

    /**
     * 切割List集合对象
     * @param list 要切割的List
     * @param len 切割长度
     * @return 切割后的List
     * @param <T> 泛型
     */
    public static <T> List<List<T>> chopped(List<T> list, int len){
        if(list == null || list.isEmpty() || len < 1){
            return Collections.emptyList();
        }
        List<List<T>> result = new ArrayList<>();
        int size = list.size();
        int count = (size + len - 1) / len;
        for (int i = 0; i < count; i++) {
            List<T> subList = list.subList(i * len, (Math.min((i + 1) * len, size)));
            result.add(subList);
        }
        return result;
    }

}
