package com.home.learn.leetcode;

import java.util.ArrayList;
import java.util.List;

public class ParenthesesQuestions {
    public int longestValidParentheses(String s) {
        int max = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }


    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    public void remove(String s, List<String> ans, int last_i, int last_j, char[] par) {
        for (int stack = 0, i = last_i; i < s.length(); ++i) {
            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;
            if (stack >= 0) continue;
            for (int j = last_j; j <= i; ++j)
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
                    remove(s.substring(0, j) + s.substring(j + 1), ans, i, j, par);
            return;
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') // finished left to right
            remove(reversed, ans, 0, 0, new char[]{')', '('});
        else // finished right to left
            ans.add(reversed);
    }

    public List<String> removeInvalidParenthesesLR(String s) {
        List<String> ans = new ArrayList<>();
        removeRight(s, ans, 0, 0);
        return ans;
    }

    public void removeRight(String s, List<String> ans, int last_i, int last_j) {
        int stack = 0;
        for (int i = last_i; i < s.length(); ++i) {
            if (s.charAt(i) == '(') stack++;
            if (s.charAt(i) == ')') stack--;
            if (stack == -1) {
                for (int j = last_j; j <= i; ++j) {
                    if (s.charAt(j) == ')' && (j == last_j || s.charAt(j - 1) != ')')) {
                        removeRight(s.substring(0, j) + s.substring(j + 1), ans, i, j);
                    }
                }
                return;
            }
        }
        removeLeft(s, ans, s.length() - 1, s.length() - 1);
    }

    public void removeLeft(String s, List<String> ans, int last_i, int last_j) {
        int stack = 0;
        for (int i = last_i; i >= 0; i--) {
            if (s.charAt(i) == ')') stack++;
            if (s.charAt(i) == '(') stack--;
            if (stack == -1) {
                for (int j = last_j; j >= i; --j) {
                    if (s.charAt(j) == '(' && (j == last_j || s.charAt(j + 1) != '(')) {
                        removeLeft(s.substring(0, j) + s.substring(j + 1), ans, i - 1, j - 1);
                    }
                }
                return;
            }
        }
        ans.add(s);
    }

    public boolean checkValidString(String s) {
        int left = 0, right = 0, n = s.length();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '(' || s.charAt(i) == '*') ++left;
            else --left;
            if (left < 0) return false;
        }
        for (int i = n - 1; i >= 0; --i) {
            if (s.charAt(i) == ')' || s.charAt(i) == '*') ++right;
            else --right;
            if (right < 0) return false;
        }
        return true;
    }

    public boolean checkValidStringV1(String s) {
        int lo = 0, hi = 0;
        for (char c: s.toCharArray()) {
            lo += c == '(' ? 1 : -1;
            hi += c != ')' ? 1 : -1;
            if (hi < 0) break;
            lo = Math.max(lo, 0);
        }
        return lo == 0;
    }

    public int minAddToMakeValid(String s) {
        int count = 0, res = 0;
        char[] sc = s.toCharArray();
        for(char c: sc) {
            if(c == '(') {
                count++;
            } else {
                if(--count < 0) {
                    res++;
                    count = 0;
                }
            }
        }
        return res + count;
    }

    //一个左括号顶2个
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

    public String minRemoveToMakeValid(String s) {
        if (s == null || s.isEmpty())
            return s;
        int left = 0;
        char[] sc = s.toCharArray();
        for (int i = 0; i < sc.length; i++) {
            char c = sc[i];
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (--left < 0) {
                    sc[i] = '#';
                    left = 0;
                }
            }
        }
        int j = sc.length - 1;
        while(left > 0) {
            char c = sc[j];
            if (c == '(') {
                sc[j] = '#';
                left--;
            }
            j--;
        }
        int idx = 0;
        for (int i = 0; i < sc.length; i++) {
            if (sc[i] != '#') {
                sc[idx++] = sc[i];
            }
        }
        return new String(sc, 0, idx);
    }
}
