package com.home.learn.facebook;

public class MaximumSumThreeNonOverlappingSubArrays {
    //因为这道题只让我们找出三个子数组，那么我们可以先确定中间那个子数组的位置，
    // 这样左右两边的子数组的位置范围就缩小了，中间子数组的起点不能是从开头到结尾整个区间，必须要在首尾各留出k个位置给其他两个数组。
    // 一旦中间子数组的起始位置确定了，那么其和就能通过累加和数组快速确定。
    // 那么现在就要在左右两边的区间内分别找出和最大的子数组，遍历所有的子数组显然不是很高效，
    // 如何快速求出呢，这里我们需要使用动态规划Dynamic Programming的思想来维护两个DP数组left和right，其中:
    //
    //left[i]表示在区间[0, i]范围内长度为k且和最大的子数组的起始位置
    //right[i]表示在区间[i, n - 1]范围内长度为k且和最大的子数组的起始位置
    //
    //这两个dp数组各需要一个for循环来更新，left数组都初始化为0，前k个数字没办法，肯定起点都是0，
    // 变量total初始化为前k个数字之和，然后从第k+1个数字开始，每次向前取k个，利用累加和数组sums快速算出数字之和，
    // 跟total比较，如果大于total的话，那么更新total和left数组当前位置值，否则的话left数组的当前值就赋值为前一位的值。
    //
    // 同理对right数组的更新也类似，total初始化为最后k个数字之和，然后从前一个数字向前遍历，如果大于total，更新total和right数组的当前位置，
    // 否则right数组的当前值就赋值为后一位的值。一旦left数组和right数组都更新好了，那么就可以遍历中间子数组的起始位置了，
    // 然后我们可以通过left和right数组快速定位出左边和右边的最大子数组的起始位置，并快速计算出这三个子数组的所有数字之和，
    // 用来更新全局最大值mx，如果mx被更新了的话，记录此时的三个子数组的起始位置到结果res中
    public int[] maxSumOfThreeSubArrays(int[] nums, int k) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        int[] dpL = new int[n];
        int[] dpR = new int[n];
        int[] res = new int[3];
        int max = 0;
        for (int i = k - 1; i < n; i++) {
            int arrSum = preSum[i + 1] - preSum[i - k + 1];
            if (arrSum > max) {
                dpL[i] = i - k + 1;
                max = arrSum;
            } else {
                dpL[i] = dpL[i - 1];
            }
        }
        max = 0;
        for (int i = n - k; i >= 0; i--) {
            int arrSum = preSum[i + k] - preSum[i];
            //因为返回最靠左的结果，所以等于也要更新
            if (arrSum >= max) {
                dpR[i] = i;
                max = arrSum;
            } else {
                dpR[i] = dpR[i + 1];
            }
        }
        int maxSum = 0;
        for (int j = k; j <= n - 2 * k; j++) {
            int l = dpL[j - 1];
            int r = dpR[j + k];
            int curSum = preSum[l + k] - preSum[l]
                    + preSum[j + k] - preSum[j]
                    + preSum[r + k] - preSum[r];
            if (curSum > maxSum) {
                maxSum = curSum;
                res[0] = l;
                res[1] = j;
                res[2] = r;
            }
        }
        return res;
    }
}
