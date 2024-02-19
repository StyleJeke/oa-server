package com.jxwgroup.oaserver.util;

import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.GZIPInputStream;

/**
 * 通过网络查询ip信息以及天气情况或归属地情况
 */
public class NetUtil {

    /**
     * 获取天气信息
     * @param city 归属地信息
     * @return 天气信息
     */
    public static Map<String,Object> getWeatherInfo(String city, String apiKey) throws IOException {
        String locationId = getLocationId(city, apiKey);
        Map<String, Object> map = GsonUtil.toMap(locationId);
        if(!Objects.equals(map.get("code"),"200")){
            return new HashMap<>();
        }
        List<Map<String, Object>> mapList = (List<Map<String, Object>>) map.get("location");
        Map<String, Object> locationMap = mapList.get(0);
        return GsonUtil.toMap(findWeatherInfo(String.valueOf(locationMap.get("id")), apiKey));
    }

    /**
     * 获取本机ip
     * @return 本机ip(外网)
     */
    public static String getExternalIP() throws IOException {
        // 使用一个速度较快的服务获取本机IP地址，例如icanhazip.com
        URL url = new URL("https://icanhazip.com/");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        try {
            return reader.readLine().trim();
        } finally {
            reader.close();
        }
    }

    /**
     * 获取ip归属地
     * @param ipAddress ip地址
     * @param apiKey api的key
     * @return ip归属地
     */
    public static String getCityFromIP(String ipAddress, String apiKey) throws IOException {
        String apiUrl = "http://apis.juhe.cn/ip/ipNew?ip=" + ipAddress + "&key=" + apiKey;
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response + "";
        } finally {
            connection.disconnect();
        }
    }

    /**
     * 获取地区编码
     * @param city 地区
     * @param apiKey apiKey
     * @return 地区编码
     */
    private static String getLocationId(String city, String apiKey) throws IOException {
        // 使用和风天气的城市搜索接口获取城市的 Location ID
//        String encodedCity = URLEncoder.encode(city, "UTF-8");
        String searchApiUrl = "https://geoapi.qweather.com/v2/city/lookup?key=" + apiKey + "&location=" + city;
        URL url = new URL(searchApiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            InputStream inputStream = connection.getInputStream();
            // 如果返回数据使用了 Gzip 压缩，使用 GZIPInputStream 解压缩数据
            if ("gzip".equals(connection.getContentEncoding())) {
                inputStream = new GZIPInputStream(inputStream);
            }
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            // 使用默认字符集（可能是 ISO-8859-1）读取原始字节数据
            int bytesRead;
            StringBuilder response = new StringBuilder();
            char[] buffer = new char[4096];
            while ((bytesRead = inputStreamReader.read(buffer)) != -1) {
                response.append(buffer, 0, bytesRead);
            }
            return response.toString();
        } finally {
            connection.disconnect();
        }
    }



    /**
     * 获取天气信息
     * @param city 地区编码
     * @param apiKey apiKey
     * @return 天气信息
     */
    private static String findWeatherInfo(String city, String apiKey) throws IOException {
        String apiUrl = "https://devapi.qweather.com/v7/weather/now?key=" + apiKey + "&location=" + city;
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            InputStream inputStream = connection.getInputStream();

            // 如果返回数据使用了 Gzip 压缩，使用 GZIPInputStream 解压缩数据
            if ("gzip".equals(connection.getContentEncoding())) {
                inputStream = new GZIPInputStream(inputStream);
            }
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            int bytesRead;
            StringBuilder response = new StringBuilder();
            char[] buffer = new char[4096];
            while ((bytesRead = inputStreamReader.read(buffer)) != -1) {
                response.append(buffer, 0, bytesRead);
            }
            return response.toString();
        } finally {
            connection.disconnect();
        }
    }
}
