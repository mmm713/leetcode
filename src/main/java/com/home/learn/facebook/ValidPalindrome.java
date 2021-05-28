package com.home.learn.facebook;

import com.home.learn.library.ListNode;

public class ValidPalindrome {
    //可删除K个是否能变成回文
    public boolean isValidPalindrome(String s, int k) {
        int n = s.length();
        char[] sc = s.toCharArray();
        int[][] dp = new int[n][n];
        for(int j = 1; j < n; j++){
            for(int i = j - 1; i >= 0; i--){
                if(sc[i] == sc[j]){
                    dp[i][j] = dp[i + 1][j - 1];
                }else{
                    dp[i][j] = 1 + Math.min(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n-1] <= k;
    }
    //看是否可以只删一个字母变成回文，遇到违规直接DFS双向贪婪删除
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

    //简单判定是否是回文
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

    //判定链表是否回文

    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode phead = head;
        slow = slow.next;
        ListNode prev = null;
        ListNode next;
        while(slow != null){
            next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        while(phead != null && prev != null){
            if(phead.val == prev.val){
                phead = phead.next;
                prev = prev.next;
            } else {
                return false;
            }
        }
        return true;
    }
}
