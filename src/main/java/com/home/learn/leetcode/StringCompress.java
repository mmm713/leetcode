package com.home.learn.leetcode;

import java.util.Arrays;

public class StringCompress {
    //贪婪即可，遇到重复就合并
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

    //将本题的描述换一种说法，实际上是让我们从字符串s中找到一个长度为length(s) - k的子序列，其行程长度编码的长度最短。
    // 我们可以将该子序列分成两部分，左半部分为1个或者多个连续相同字符c，右半部分为不以c开头任意子序列。
    // 分别求出左右两部分的编码长度，求和即是当前子序列编码后的长度。而右半部分我们又可以差分为两部分，
    // 他的左半部分为1个或者多个连续相同字符c'(c’!=c)，它的右半部分不以c’开头任意子序列。
    // 以此类推，这实际上是一个清晰的递归过程。我们举例来看，比如：
    //
    //s = "abacad";
    //k=2;
    //因为k等于2，也就意味着我们需要在s中找到一个长度为4的子序列。我们从s下标为0处向后循环，
    // 当前字母为a，首先构建子序列的左半部分，即找到n个相同的a，对于本例，我们有三种选择方案：
    //
    //选择第一个a，即子序列的左半部分为a，右半部分需要在bacad中找到一个长度为3的并不以a开头的序列（递归求解）。
    //选择前两个a，即子序列的左半部分为aa，右半部分需要在cad中找到一个长度为2的并不以a开头的序列（递归求解）。
    //选择前三个a，即子序列的左半部分为aaa，右半部分需要在d中找到一个长度为1的并不以a开头的序列（递归求解）。
    //除了上述三种情况，我们还可以向后查找以b开头或者以c开头的左半部分，所有选择中，编码后最短的子序列的长度即是返回结果。
    //
    //总结来看，递归方法中我们需要使用到两层循环，第一层循环用来找到子序列左半部分的开头字母，
    // 第二层循环来控制该字母在子序列中连续出现的次数，也就是左半部分的长度。
    // 而右半部分交给递归子问题去处理。所有组合中，长度最短的即为当前递归的返回结果。
    //
    //另外，递归方法一般都要有记忆数组相伴。递归函数的参数中，我们需要传递当前的index，
    // 即右半部分的起始位置，以及当前剩余长度两个变量，因此使用一个二维记忆数组即可保存递归函数的转态（相当于动态规划解法中的DP数组）。
    //
    //最后再看下可以优化的部分，第一层循环，我们在找左半部分的开头字母，因此循环中遇到已经出现过的字母时可以跳过。
    // 比如上例中的字符串abacad，我们从下标0向后循环，按照顺序先找以a开头的左半部分，接下来是以b开头，在接下来又是字母a，这里我们没有必要再做重复操作，
    // 从当前下标2开始做第二层循环与下标0处唯一的区别是，子序列左半部分的a的最大长度只能达到2，而这种情况已被下标0处的情况所包含。
    // 解题时，我们可以考虑使用一个访问数组来记录我们已经处理过的字母。
    //

    //可以删除K个
    public int getLengthOfOptimalCompression(String s, int k) {
        int[][] dp = new int[s.length()][k + 1];
        for (int i = 0; i < s.length(); i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        }
        char[]  cArr = s.toCharArray();
        return helper(0, k, dp, cArr);
    }

    private int helper(int i, int k, int[][] dp, char[] cArr) {
        if (k < 0) {
            return Integer.MAX_VALUE / 2;
        }
        if (i + k >= cArr.length) {
            return 0;
        }
        if (dp[i][k] != Integer.MAX_VALUE / 2) {
            return dp[i][k];
        }
        int ans = helper(i + 1, k - 1, dp, cArr); // discard
        // keep
        int len = 0;
        int same = 0;
        int diff = 0;
        for (int j = i; j < cArr.length && diff <= k; j++) {
            if (cArr[j] == cArr[i]) {
                ++same;
                if (same <= 2 || same ==10 || same == 100) {
                    ++len;
                }
            } else {
                ++diff;
            }
            ans = Math.min(ans, len + helper(j + 1, k - diff, dp, cArr));
        }

        dp[i][k] = ans;
        return ans;
    }
}
