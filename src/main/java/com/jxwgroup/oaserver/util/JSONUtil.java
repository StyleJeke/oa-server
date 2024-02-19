package com.jxwgroup.oaserver.util;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JSONUtil {

    public static List<String> extractField(JsonNode rootNode, String fieldName) {
        List<String> values = new ArrayList<>();
        extractFieldRecursive(rootNode, fieldName, values);
        return values;
    }

    public static void extractFieldRecursive(JsonNode node, String fieldName, List<String> values) {
        if (node.isObject()) {
            Iterator<String> fieldNames = node.fieldNames();
            while (fieldNames.hasNext()) {
                String field = fieldNames.next();
                JsonNode fieldValue = node.get(field);
                if (field.equals(fieldName)) {
                    values.add(fieldValue.asText());
                }
                extractFieldRecursive(fieldValue, fieldName, values);
            }
        } else if (node.isArray()) {
            for (JsonNode arrayElement : node) {
                extractFieldRecursive(arrayElement, fieldName, values);
            }
        }
    }
}
