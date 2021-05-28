package com.home.learn.facebook;

public class LongestRepeatingCharacterReplacement {
    //需要注意的是，当滑动窗口的左边界向右移动了后，窗口内的相同字母的最大个数貌似可能会改变啊，
    // 为啥这里不用更新 maxCnt 呢？这是个好问题，原因是此题让求的是最长的重复子串，maxCnt 相当于卡了一个窗口大小，
    // 我们并不希望窗口变小，虽然窗口在滑动，但是之前是出现过跟窗口大小相同的符合题意的子串，缩小窗口没有意义，
    // 并不会使结果 res 变大，所以我们才不更新 maxCnt 的
    public int characterReplacement(String s, int k) {
        int res = 0;
        int window = 0;
        int start = 0;
        int[] counts = new int[26];
        char[] sc = s.toCharArray();
        for (int i = 0; i < sc.length; i++) {
            window = Math.max(window, ++counts[sc[i] - 'A']);
            if(i - start + 1 - window > k) {
                --counts[sc[start++] - 'A'];
            }
            res = Math.max(res, i - start + 1);
        }
        return res;
    }
}
