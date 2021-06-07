package com.home.learn.bytedance;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StringTransformsIntoAnotherString {
    //只要不把26个字符用光，就有操作的空间
    public boolean canConvert(String str1, String str2) {
        Set<Character> set = new HashSet<>();
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < str1.length(); i++) {
            char a = str1.charAt(i);
            char b = str2.charAt(i);
            set.add(b);
            if (map.getOrDefault(a, b) != b) {
                return false;
            }
            map.put(a, b);
        }
        return str1.equals(str2) || map.size() != 26 || set.size() != 26;
        //也可以 return map.size() >= set.size() && (str1.equals(str2) || set.size() != 26);
    }
}
