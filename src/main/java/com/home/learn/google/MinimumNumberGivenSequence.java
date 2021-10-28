package com.home.learn.google;

public class MinimumNumberGivenSequence {
    static String PrintMinNumberForPattern(String str) {
        StringBuilder ans = new StringBuilder(); // Minimum number following pattern
        int i = 0;
        int cur = 1; // cur val following pattern
        int dCount = 0; // Count of char 'D'
        while (i < str.length()) {
            char ch = str.charAt(i);
            // If 1st ch == 'I', incr and add to ans
            if (i == 0 && ch == 'I') {
                ans.append(cur);
                cur++;
            }
            // If cur char == 'D',
            // incr dCount as well, since we always
            // start counting for dCount from i+1
            if (ch == 'D') {
                dCount++;
            }
            int j = i + 1; // Count 'D' from i+1 index
            while (j < str.length() && str.charAt(j) == 'D') {
                dCount++;
                j++;
            }
            int k = dCount;  // Store dCount
            while (dCount >= 0) {
                ans.append(cur + dCount);
                dCount--;
            }
            cur += (k + 1); // Manages next cur val
            dCount = 0;
            i = j;
        }
        return ans.toString();
    }
}
