package com.home.learn.facebook;

public class MinimumInsertionsToBalanceParenthesesString {
    public int minInsertions(String s) {
        int left = 0, right = 0;
        for(char ch : s.toCharArray()){
            if(ch == '(') {
                if(right %2 !=0){
                    left++;
                    right++;
                } else {
                    right += 2;
                }
            } else {
                if(--right < 0){
                    left++;
                    right += 2;
                }
            }
        }
        return left + right;
    }
}
