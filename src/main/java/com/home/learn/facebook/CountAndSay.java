package com.home.learn.facebook;

public class CountAndSay {
    public String countAndSay(int n) {
        String ret = "" + 1;
        while(--n  > 0)
            ret = apply(ret);
        return ret;
    }

    String apply(String s){
        StringBuilder ret = new StringBuilder();
        int i = 0, count = 1;
        while(i < s.length()){
            while(i + count < s.length() && s.charAt(i) == s.charAt(i + count)){
                count ++;
            }
            ret.append(count).append(s.charAt(i));
            i += count;
            count = 1;
        }
        return ret.toString();
    }
}
