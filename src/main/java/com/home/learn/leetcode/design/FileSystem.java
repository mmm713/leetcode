package com.home.learn.leetcode.design;

import java.util.HashMap;
import java.util.Map;

public class FileSystem {
    Map<String, Integer> pathMap;

    public FileSystem() {
        pathMap = new HashMap<>();
        pathMap.put("", -1);
    }

    public boolean createPath(String path, int value) {
        int lastIndex = path.lastIndexOf('/');
        if (!pathMap.containsKey(path.substring(0, lastIndex)) || pathMap.containsKey(path)) {
            return false;
        }
        pathMap.put(path, value);
        return true;
    }

    public int get(String path) {
        return pathMap.getOrDefault(path, -1);
    }
}
