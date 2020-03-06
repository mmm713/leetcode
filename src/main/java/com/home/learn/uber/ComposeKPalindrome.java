package com.home.learn.uber;

/*
个字符串，可以打乱顺序，问能否组成k个palindrome。也就是说，
abb和bab或者bba作为input，给出的结果应该一样（注意区分本题和狸寇德730，
后者说的是subsequence，也就是顺序不可打乱，因此难度增加）。例如，abracadabra, k = 2, 返回false。
 */
public class ComposeKPalindrome {
    public boolean canForm(String input, int k) {
        int[] hash = new int[26];
        int odd = 0;
        for(char c : input.toCharArray()) {
            if(++hash[c - 'a'] % 2 == 1) {
                odd++;
            } else {
                odd--;
            }
        }
        return k <= input.length() && k >= odd;
    }
}
