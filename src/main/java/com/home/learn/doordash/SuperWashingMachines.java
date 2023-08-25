package com.home.learn.doordash;

public class SuperWashingMachines {
    /*
    test cases
        [1,0,5] - 3
        [0,3,0] - 2
        [0,2,0] - -1
        [17, 23, 4, 3, 28, 7, 8, 5, 6, 28, 30, 27, 21, 20, 4, 20, 3, 24, 23, 19] - 43
        [7, 8, 7, 14, 14, 19, 4, 13, 29, 5, 24, 16, 9, 27, 7, 21, 29,
            12, 14, 5, 13, 21, 19, 20, 14, 6, 18, 11, 8, 9, 25, 5, 11, 8, 20, 9, 15, 21, 8, 15] - 26
     */

    public int findMinMovesA(int[] machines) {
        int total = 0;
        for (int num : machines) {
            total += num;
        }
        int n = machines.length;
        if (total % n != 0) {
            return -1;
        }
        int mean = total / n;
        int moves = 0;
        int sum = 0, maxSum = 0;
        for (int machine : machines) {
            int difference = machine - mean;
            sum += difference;
            maxSum = Math.max(maxSum, Math.abs(sum));
            moves = Math.max(moves, Math.max(maxSum, difference));
        }
        return moves;
    }

    public int findMinMovesB(int[] machines) {
        int n = machines.length;
        int total = 0;
        for (int num : machines) {
            total += num;
        }
        if (total % n != 0) {
            return -1;
        }
        int avg = total / n;
        int[] left = new int[n];
        int[] right = new int[n];
        // 两端的两个洗衣机只能从一个方向进行接收或输出, 故需要提前处理, 其他洗衣机都有两个左右两个方向的输入、输出
        /*
        另外，我们想象一下，3台相邻的洗衣机 machines[i-1], machines[i] 和machines[i-1]。
        按上面的定义可知 right[i-1] = -left[i], 且 right[i] = -left[i+1] (1)。
        当前洗衣机，做哪些事情可以达到最终的ave?
        machines[i] - (left[i] + right[i]) = ave (2)
        我们可以先根据关系式(1)和(2)算出left和right数组。
         */
        right[0] = machines[0] - avg;
        left[n -1] = machines[n -1] - avg;
        for (int i = 1; i < n - 1; i++) {
            left[i] = -right[i-1];
            right[i] = machines[i] - avg - left[i];
        }
        int res = 0;
        for (int i = 0; i < n; i++) /* 计算输出净值的最大值 */ {
            int curMax = 0;
            if (left[i] >= 0) curMax += left[i];
            if (right[i] >= 0) curMax += right[i];
            res = Math.max(res, curMax);
        }
        return res;
    }

    //优化space
    public int findMinMovesC(int[] machines) {
        int n = machines.length;
        int total = 0;
        for (int num : machines) {
            total += num;
        }
        if (total % n != 0) {
            return -1;
        }
        int avg = total / n;
        int left, right = 0, res = 0;
        for (int i = 0; i < n; i++) {
            int curMax = 0;
            left = i == n - 1? machines[n -1] - avg : -right;
            right = machines[i] - avg - left;
            if(left >= 0) curMax += left;
            if(right >= 0) curMax += right;
            res = Math.max(res, curMax);
        }
        return res;
    }
}
