package com.home.learn.facebook;

public class MaximumSwap {
    //从后向前遍历，最大值记录在N1位置并实时更新，如果发现有可以替换，就更新替换位置
    public int maximumSwap(int num) {
        char[] sc = String.valueOf(num).toCharArray();
        int n = sc.length, n1 = n - 1, start = -1, end = -1;
        for (int i = n - 2; i >= 0; --i) {
            if (sc[i] < sc[n1]) {
                start = i;
                end = n1;
            } else if (sc[i] > sc[n1]) {
                n1 = i;
            }
        }
        if (start != -1) swap(sc, start, end);
        return Integer.parseInt(new String(sc));
    }

    private void swap(char[] sc, int a, int b) {
        char tmp = sc[a];
        sc[a] = sc[b];
        sc[b] = tmp;
    }
}
