package com.home.learn.facebook;

public class RemoveAllAdjacentDuplicatesInString {
    public String removeDuplicates(String S) {
        StringBuilder sb = new StringBuilder();
        int len = 0;
        for(char c : S.toCharArray()) {
            if (len != 0 && c == sb.charAt(len - 1))
                sb.deleteCharAt(len-- - 1);
            else {
                sb.append(c);
                len++;
            }
        }
        return sb.toString();
    }
}
