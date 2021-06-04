package com.home.learn.google;

import java.util.Arrays;
import java.util.Stack;

import static com.home.learn.Helpers.print2D;

public class EncodeDecodeString {
    //遍历过程中，得到次数后进出堆栈即可。堆栈存储SB方便出答案
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

    //我们建立一个二维的DP数组，其中dp[i][j]表示s在[i, j]范围内的字符串的缩写形式(如果缩写形式长度大于子字符串，那么还是保留子字符串)，
    // 那么如果s字符串的长度是n，最终我们需要的结果就保存在dp[0][n-1]中，然后我们需要遍历s的所有子字符串，
    // 对于任意一段子字符串[i, j]，我们以中间任意位置k来拆分成两段，比较dp[i][k]加上dp[k+1][j]的总长度和dp[i][j]的长度，
    // 将长度较小的字符串赋给dp[i][j]，然后我们要做的就是在s中取出[i, j]范围内的子字符串t进行合并。
    // 合并的方法是我们在取出的字符串t后面再加上一个t，然后在这里面寻找子字符串t的第二个起始位置，
    // 如果第二个起始位置小于t的长度的话，说明t包含重复字符串，举个例子吧，
    //
    // 比如 t = "abab", 那么t+t = "abababab"，我们在里面找第二个t出现的位置为2，小于t的长度4，说明t中有重复出现，重复的个数为t.size()/pos = 2个，
    // 那么我们就要把重复的地方放入中括号中，注意中括号里不能直接放这个子字符串，而是应该从dp中取出对应位置的字符串，因为重复的部分有可能已经写成缩写形式了，
    //
    // 比如题目中的例子5。再看一个例子，如果t = "abc"，那么t+t = "abcabc"，我们在里面找第二个t出现的位置为3，等于t的长度3，说明t中没有重复出现，那么replace就还是t。
    // 然后我们比较我们得到的replace和dp[i][j]中的字符串长度，把长度较小的赋给dp[i][j]即可，时间复杂度为O(n3)，空间复杂度为O(n2)
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
