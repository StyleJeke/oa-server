package com.jxwgroup.oaserver.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.stream.Collectors;

public class SqlUtils {
	
	/**
	 * 将列表转换成字符串, 用逗号和单引号分隔    ','
	 * [a,b,c,d]  => 'a','b','c','d'
	* @param collection
	* @return
	* @return: String
	* @author  jinlk
	* @date 2021-01-13 17:35
	 */
	public static String listToString(Collection<String> collection) {
		if (collection == null) {
			return null;
		}
		return collection.stream().collect(Collectors.joining("','", "'", "'"));
	}
	
	/**
	 * 将列表转换成字符串, 用逗号分隔  
	 * [a,b,c,d]  => a,b,c,d
	* @param collection
	* @return
	* @return: String
	* @author  jinlk
	* @date 2021-01-13 17:35
	 */
	public static String listToStringComma(Collection<String> collection) {
		if (collection == null) {
			return null;
		}
		return collection.stream().collect(Collectors.joining(","));
	}
	
	/**
	 * 将列表转换成字符串
	* @param collection 列表
	* @param ch 分隔符
	* @author  jinlk
	* @date 2021-01-13 17:35
	 */
	public static String listToString(Collection<String> collection, String ch) {
		if (collection == null) {
			return null;
		}
		return collection.stream().collect(Collectors.joining(ch));
	}
	
	/**
	 * 当字符串为null 时返回空字符串, 否则返回原值
	 * @param str
	 * @return
	 */
	public static String orGetEmpty(String str) {
		if (str == null) {
			return "";
		}
		return str;
	}
	
	/**
	 * 将第1个字符串拼接到第2个字符串最前面, 用ch拼接
	* @param str 字符串
	* @param ch 拼接符
	* @author jinlk
	* @date 2021-03-10 15:25
	 */
	public static String spliceHead(String str1, String str2, String ch) {
		if (StringUtils.isEmpty(str2)) {
			return str1;
		} else if (StringUtils.isEmpty(str1)) {
			return str2;
		}
		return str1  + ch + str2;
	}

}
