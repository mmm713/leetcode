package com.home.learn.facebook;

import java.util.Arrays;

public class WoodCut {
    public int woodCut(int[] L, int k) {
        // write your code here
        if(L.length == 0) return 0;
        int left = 1;
        int right = Arrays.stream(L).max().getAsInt();
        int ans = 0;
        while(left <= right) {
            int middle = left + (right - left) / 2;
            if(findCount(L, middle) >= k) {
                ans = middle;
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return ans;
    }

    private int findCount(int[] L, int m) {
        int count = 0;
        for(int l : L) {
            count += l / m;
        }
        return count;
    }
}
