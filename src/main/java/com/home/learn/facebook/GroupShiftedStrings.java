package com.home.learn.facebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupShiftedStrings {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for(String s : strings) {
            char[] sc = s.toCharArray();
            StringBuilder code = new StringBuilder("0");
            for(int i = 1; i < sc.length; i++) {
                int diff = sc[i] - sc[i - 1];
                code.append((diff < 0 ? (diff + 26) : diff) * 26);
            }
            map.putIfAbsent(code.toString(), new ArrayList<>());
            map.get(code.toString()).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
