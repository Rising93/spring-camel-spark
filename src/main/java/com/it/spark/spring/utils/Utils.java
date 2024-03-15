package com.it.spark.spring.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    Utils() {
    }
    private static final String REGEX = "^\"|\"$";
    public static final String COMMA_DELIMITER = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

    public static List<Map<String, Object>> getList(List<String> fileContent) {
        String id = fileContent.get(0);
        String key = fileContent.get(1).replaceAll(REGEX, "");
        String value = fileContent.get(6);

        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", id);
        map.put("key", key);
        map.put("value", value);
        list.add(map);
        return list;
    }
}
