package com.home.learn.google;

import java.util.Arrays;
import java.util.Stack;

import static com.home.learn.Helpers.print2D;

public class EncodeDecodeString {
    public String decodeString(String s) {
        Stack<Integer> intStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder cur = new StringBuilder();
        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } else if ( ch == '[') {
                intStack.push(k);
                strStack.push(cur);
                cur = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder tmp = cur;
                cur = strStack.pop();
                for (k = intStack.pop(); k > 0; --k) cur.append(tmp);
            } else cur.append(ch);
        }
        return cur.toString();
    }

    public String encode(String s) {
        int n = s.length();
        String[][] dp = new String[n][n];
        for (String[] arr : dp) {
            Arrays.fill(arr, "");
        }
        for (int step = 1; step <= n; step++) {
            for (int i = 0; i + step - 1 < n; i++) {
                int j = i + step - 1;
                String subStr = s.substring(i, j + 1);
                String encoded = subStr;
                int pos = (subStr + subStr).indexOf(subStr, 1);
                if (pos < subStr.length()) {
                    int ctx = subStr.length() / pos;
                    encoded = ctx + "[" + dp[i][i + pos - 1] + "]";
                }
                if (encoded.length() < subStr.length()) {
                    dp[i][j] = encoded;
                } else {
                    dp[i][j] = subStr;
                    for (int k = i; k < j; k++) {
                        if (dp[i][k].length() + dp[k + 1][j].length() < dp[i][j].length()) {
                            dp[i][j] = dp[i][k] + dp[k + 1][j];
                        }
                    }
                }
            }
        }
        return dp[0][n - 1];
    }
}
