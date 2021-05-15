package com.home.learn.facebook;

public class ValidPalindrome {

    public boolean validPalindrome(String s) {
        if(s.length() <= 2){
            return true;
        }
        return isValid(s.toCharArray(), 0, s.length() - 1, 0);
    }
    private boolean isValid(char[] s, int left, int right, int error) {
        if(left > right){
            return true;
        }
        while(left < s.length && right >= 0 && left < right && s[left] == s[right]){
            left++;
            right--;
        }
        if(error < 2){
            return isValid(s, left + 1, right, error + 1) || isValid(s, left, right - 1, error + 1);
        } else {
            return false;
        }
    }

    public boolean isPalindrome(String s) {
        if ("".equals(s)){
            return true;
        }
        for (int i = 0, j = s.length() - 1; i < j; ++i, --j) {
            char ci = s.charAt(i);
            char cj = s.charAt(j);
            while (i < j && !(ci >= 'a' && ci <= 'z' || ci >= 'A' && ci <= 'Z' || ci >= '0' && ci <= '9'))
                ci = s.charAt(++i);
            while (i < j && !(cj >= 'a' && cj <= 'z' || cj >= 'A' && cj <= 'Z' || cj >= '0' && cj <= '9'))
                cj = s.charAt(--j);
            if (i < j) {
                if (ci >= 'A' && ci <= 'Z')
                    ci += 32;
                if (cj >= 'A' && cj <= 'Z')
                    cj += 32;
                if (ci != cj)
                    return false;
            }
        }
        return true;
    }
}
