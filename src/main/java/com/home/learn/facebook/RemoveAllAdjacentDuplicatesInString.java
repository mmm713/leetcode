package com.home.learn.facebook;

import java.util.Stack;

public class RemoveAllAdjacentDuplicatesInString {
    public String removeDuplicates(String S) {
        StringBuilder sb = new StringBuilder();
        int len = 0;
        for(char c : S.toCharArray()) {
            if (len != 0 && c == sb.charAt(len - 1))
                sb.deleteCharAt(len-- - 1);
            else {
                sb.append(c);
                len++;
            }
        }
        return sb.toString();
    }

    public String removeDuplicatesFast(String s) {
        if (s == null || s.length() <= 1) return s;
        int n = s.length();
        char[] arr = s.toCharArray();
        int slow = -1;  // slow means the top of stack
        int fast = 0;   // faster means the current one being processed
        while (fast < n) {
            if (slow == -1 || arr[fast] != arr[slow]) {       // push into stack
                arr[++slow] = arr[fast++];
            } else if (arr[fast] == arr[slow]) {
                fast++;
                slow--;
            }
        }
        return new String(arr, 0, slow + 1);
    }

    //直到有K个才可以删除
    //双指针法，i向前查找可以删除者，idx反向删除助手并且记录最终有效位置
    public String removeDuplicates(String s, int k) {
        Stack<Integer> counts = new Stack<>();
        char[] sa = s.toCharArray();
        int idx = 0;
        for (char c : sa) {
            sa[idx] = c;
            if (idx == 0 || sa[idx] != sa[idx - 1]) {
                counts.push(1);
            } else {
                int incremented = counts.pop() + 1;
                if (incremented == k) {
                    idx = idx - k;
                } else {
                    counts.push(incremented);
                }
            }
            idx++;
        }
        return new String(sa, 0, idx);
    }

    //直接用array代替stack加速
    public String removeDuplicatesFast(String s, int k) {
        int i = 0;
        int n = s.length();
        int[] counts = new int[n];
        char[] sc = s.toCharArray();
        for (int j = 0; j < n; ++j, ++i) {
            sc[i] = sc[j];
            counts[i] = i > 0 && sc[i - 1] == sc[j] ? counts[i - 1] + 1 : 1;
            if (counts[i] == k) i -= k;
        }
        return new String(sc, 0, i);
    }
}
