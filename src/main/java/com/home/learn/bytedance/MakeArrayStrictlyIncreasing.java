package com.home.learn.bytedance;

import com.home.learn.Helpers;

import java.util.Arrays;

public class MakeArrayStrictlyIncreasing {
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        int l2 = 0;
        //保证arr2没有重复，终点为l2
        for(int i = 0; i < arr2.length;i++){
            if(arr2[i] != arr2[l2])
                arr2[++l2] = arr2[i];
        }
        l2++;
        //dp长度
        int [] dp = new int [l2 + 2];
        for(int i = 0; i < arr1.length; i++){
            int noChange = dp[l2 + 1];
            if(i > 0 && (arr1[i - 1] >= arr1[i])) {
                noChange = -1;
            }
            for(int j = l2; j > 0 ; j--){
                if(arr2[j - 1] < arr1[i]  && dp[j] != -1) {
                    noChange = noChange == -1 ? dp[j] : Math.min(noChange, dp[j]);
                }
                if(dp[j - 1] != -1) {
                    dp[j] = 1 + dp[j - 1];
                } else {
                    dp[j] = -1;
                }
                if(i > 0 && arr1[i - 1] < arr2[j - 1] && dp[l2 + 1] >= 0) {
                    dp[j] = dp[j] == -1 ? (dp[l2 + 1] + 1) : Math.min(dp[j], dp[l2 + 1] + 1);
                }
            }
            dp[0] = -1;
            dp[l2 + 1] = noChange;
        }
        int res = -1;
        for(int num : dp){
            if(num != -1) {
                res = res == -1 ? num : Math.min(res, num);
            }
        }
        return res;
    }

    public int makeArrayIncreasing2D(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        Arrays.sort(arr2);
        int l2 = 0;
        //保证arr2没有重复，终点为l2
        for(int i = 0; i < arr2.length; i++){
            if(arr2[i] != arr2[l2])
                arr2[++l2] = arr2[i];
        }
        int n2 = ++l2;
        //dp[i][j] 表示 arr1 i位置，替换成arr2 j位置的开销
        int[][] dp = new int[n1][n2];
        Arrays.fill(dp[0], 1);
        //res表示不变更的结果
        int res = 0;
        for(int i = 1; i < n1; i++) {
            Arrays.fill(dp[i], -1);
            int noChange = -1;
            //如果大于前置，不换直接继承
            if(arr1[i] > arr1[i - 1]) {
                noChange = res;
            }
            //尝试从arr2 j位置走过来不换
            for(int j = 0; j < n2; j++) {
                if(arr1[i] > arr2[j]) {
                    noChange = noChange == -1 ? dp[i - 1][j] :
                            (dp[i - 1][j] == -1 ? noChange : Math.min(noChange, dp[i - 1][j]));
                }
            }
            //若i-1不换有解，尝试替换当前数，j必比i-1大且
            for(int j = 0; j < n2; j++) {
                if(arr2[j] > arr1[i - 1] && res != -1) {
                    dp[i][j] = res + 1;
                }
            }
            //同样考虑i-1无解时，尝试所有j，如果i-1,j-1有解，尝试取min
            int min = Integer.MAX_VALUE;
            for(int j = 1; j < n2; j++) {
                if(dp[i - 1][j - 1] != -1) {
                    min = min == -1 ? dp[i - 1][j - 1] : Math.min(min, dp[i - 1][j - 1]);
                    if(min != -1) {
                        dp[i][j] = dp[i][j] == -1 ? min + 1 : Math.min(dp[i][j], min + 1);
                    }
                }
            }
            res = noChange;
        }
        for(int j = 0; j < n2; j++) {
            if (dp[n1 - 1][j] != -1) {
                res = res == -1 ? dp[n1 - 1][j] : Math.min(res, dp[n1 - 1][j]);
            }
        }
        return res;
    }

    public int makeArrayIncreasing2DS(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        Arrays.sort(arr2);

        int l2 = 0;
        //保证arr2没有重复，终点为l2
        for(int i = 0; i < arr2.length;i++){
            if(arr2[i] != arr2[l2])
                arr2[++l2] = arr2[i];
        }
        int n2 = ++l2;

        //dp[i][j+1] 表示 arr1 i位置，替换成arr2 j位置的开销 0 0为预留位表示无变化
        //dp[i][0] 用来比较 arr1[i-1] 和 arr1[i]，0位保持当前行最优结果
        int[][] dp = new int[n1][n2];
        //initialize for i = 0
        Arrays.fill(dp[0], 1);
        int res = 0;
        //如果大于前置，0位直接继承
        //检查是否有可以替换，并更新
        //如果arr2存在比前一位大，其结果必然为前行最优+1
        for(int i = 1; i < n1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            int tmp = Integer.MAX_VALUE;
            if(arr1[i] > arr1[i - 1]) {
                tmp = res;
            }
            for(int j = 0; j < n2; j++) {
                if(arr1[i] > arr2[j]) {
                    tmp = Math.min(tmp, dp[i - 1][j]);
                }
            }
            //if previous position (i - 1) was not replaced, check what arr2 elements can be put on i-th position
            for(int j = 0; j < n2; j++) {
                if(arr2[j] > arr1[i - 1] && res != Integer.MAX_VALUE) {
                    dp[i][j] = res + 1;
                }
            }
            res = tmp;
            //since arr2 is sorted, min record the minimum cost of (i-1)-th position before the j-the element in arr2
            int min = Integer.MAX_VALUE;
            for(int j = 1; j < n2; j++) {
                min = Math.min(min, dp[i - 1][j - 1]);
                if(min != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], min + 1);
                }
            }
            Helpers.print2D(dp);
            res = tmp;
            System.out.println("=================" + res);
        }
        for(int j = 0; j < n2; j++) {
            if (dp[n1 - 1][j] != Integer.MAX_VALUE) {
                res = Math.min(res, dp[n1 - 1][j]);
            }
        }
        return res != Integer.MAX_VALUE ? res : -1;
    }
}
