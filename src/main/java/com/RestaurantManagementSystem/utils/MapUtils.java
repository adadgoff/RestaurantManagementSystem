package com.RestaurantManagementSystem.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapUtils {
    public static Map<Long, Long> FromStringStringMapToLongLongMap(Map<String, String> params, List<String> ignoredKeys) {
        return params.entrySet().stream()
                .filter(entry -> !ignoredKeys.contains(entry.getKey()))
                .collect(Collectors.toMap(
                        entry -> {
                            String key = entry.getKey();
                            return Long.parseLong(key.substring(key.indexOf("[") + 1, key.indexOf("]")));
                        },
                        entry -> Long.parseLong(entry.getValue())
                ));
    }
}
