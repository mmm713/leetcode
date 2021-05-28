package com.home.learn.leetcode;

public class PaintHouse {
    //相邻颜色不同，颜色cost每户不同
    public int minCost(int[][] costs) {
        //res为最终结果，min2上次第二小的总结果
        int res = 0, min2 = 0, minIdx = -1;
        for (int[] house : costs) {
            int tempRes = Integer.MAX_VALUE, last2 = Integer.MAX_VALUE, curMinIdx = 0;
            for (int j = 0; j < 3; j++) {
                int cost = house[j] + ((j == minIdx) ? min2 : res);
                if (cost < tempRes) {
                    last2 = tempRes;
                    tempRes = cost;
                    curMinIdx = j;
                } else if (cost < last2) {
                    last2 = cost;
                }
            }
            res = tempRes;
            min2 = last2;
            minIdx = curMinIdx;
        }
        return res;
    }

    //当有k种颜色时
    public int minCostII(int[][] costs) {
        int k = costs[0].length;
        int res = 0, min2 = 0, minIndex = -1;
        for (int[] house : costs) {
            int tempRes = Integer.MAX_VALUE, curMin2 = Integer.MAX_VALUE, curMinIndex = 0;
            for (int j = 0; j < k; j++) {
                int cost = house[j] + (j == minIndex ? min2 : res);
                if (cost < tempRes) {
                    curMin2 = tempRes;
                    tempRes = cost;
                    curMinIndex = j;
                } else if (cost < curMin2) curMin2 = cost;
            }
            res = tempRes;
            min2 = curMin2;
            minIndex = curMinIndex;
        }
        return res;
    }
}
