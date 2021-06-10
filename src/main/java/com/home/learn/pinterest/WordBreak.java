package com.home.learn.pinterest;

import java.util.*;

public class WordBreak {

    //N方时间，n空间
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet=new HashSet<>(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[s.length()];
        queue.add(0);
        while (!queue.isEmpty()) {
            int start = queue.remove();
            if (!visited[start]) {
                for (int end = start + 1; end <= s.length(); end++) {
                    if (wordDictSet.contains(s.substring(start, end))) {
                        if (end == s.length()) {
                            return true;
                        } else {
                            queue.add(end);
                        }
                    }
                }
                visited[start] = true;
            }
        }
        return false;
    }
    //二维DP可解。dp[i]记录0 - i的string是否拆。递推公式为dp[i] = dp[j] && 包含剩余字母
    public boolean wordBreakDP(String s, List<String> wordDict) {
        Set<String> wordDictSet=new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static boolean isPossibleWordBreak(String s, Set<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for(int i = 1; i <= s.length(); i++){
            for(int j = i - 1; j >=0 ; j--) {
                dp[i] = dp[j] && wordDict.contains(s.substring(j, i));
                if(dp[i]) break;
            }
        }
        return dp[s.length()];
    }
}
