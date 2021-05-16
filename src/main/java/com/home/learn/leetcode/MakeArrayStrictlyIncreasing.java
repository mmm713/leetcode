package com.home.learn.leetcode;

import java.util.Arrays;

public class MakeArrayStrictlyIncreasing {
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        int start = 0;
        //prepare array 2 without duplicate
        for(int i = 0; i < arr2.length;i++){
            if(arr2[i] != arr2[start])
                arr2[++start] = arr2[i];
        }
        int l2 = start+1;

        int [] dp = new int [l2 + 2];
        for(int i = 0; i< arr1.length; i++){
            int noChange = dp[dp.length - 1];
            if(i > 0 && (arr1[i - 1] >= arr1[i])) {
                noChange = -1;
            }
            for(int j = dp.length-2; j>0 ; j--){
                if(arr2[j-1] < arr1[i]  && dp[j] != -1) {
                    noChange = noChange == -1 ? dp[j] : Math.min(noChange, dp[j]);
                }
                if(dp[j-1] != -1) {
                    dp[j] = 1 + dp[j - 1];
                } else {
                    dp[j] = -1;
                }
                if(i > 0 && arr1[i - 1] < arr2[j - 1] && dp[dp.length-1] >= 0) {
                    dp[j] = dp[j] == -1 ? (dp[dp.length - 1] + 1) : Math.min(dp[j], dp[dp.length - 1] + 1);
                }
            }
            dp[0] = -1;
            dp[dp.length-1] = noChange;
        }
        int res = -1;
        for(int num : dp){
            if(num != -1) {
                res = res == -1 ? num : Math.min(res, num);
            }
        }
        return res;
    }
}
