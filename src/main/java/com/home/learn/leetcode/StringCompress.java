package com.home.learn.leetcode;

public class StringCompress {
    public int compress(char[] chars) {
        if(chars.length <= 0) return 0;
        int ptr = 0, counter = 1;
        char prev = chars[0];
        for(int i = 1; i < chars.length; i++) {
            if(chars[i] == prev) counter++;
            else {
                ptr = update(chars, counter, ptr, prev);
                counter = 1;
                prev = chars[i];
            }
        }
        return update(chars, counter, ptr, prev);
    }

    private int update(char[] chars, int counter, int ptr, char prev) {
        chars[ptr++] = prev;
        if(counter > 1)
            for(char digit : String.valueOf(counter).toCharArray())
                chars[ptr++] = digit;
        return ptr;
    }
}
