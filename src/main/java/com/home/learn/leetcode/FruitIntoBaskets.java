package com.home.learn.leetcode;

import java.util.HashMap;
import java.util.Map;

public class FruitIntoBaskets {
    public int totalFruit(int[] tree) {
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        int temp = 0;
        int type = 0;
        for(int i = 0; i < tree.length; i++) {
            if(!map.containsKey(tree[i])) {
                if(type++ >= 2) {
                    temp = i - map.get(tree[i - 1]);
                    int toDelete = -1;
                    for(Map.Entry<Integer, Integer> e: map.entrySet()) {
                        if(e.getKey() != tree[i - 1]) {
                            toDelete = e.getKey();
                        }
                    }
                    map.remove(toDelete);
                }
                map.put(tree[i], i);
            } else {
                if(tree[i] != tree[i - 1]) {
                    map.put(tree[i], i);
                }
            }
            result = Math.max(result, ++temp);
        }
        return result;
    }
}
