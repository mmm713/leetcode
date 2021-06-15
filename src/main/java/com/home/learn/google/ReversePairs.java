package com.home.learn.google;

import java.util.Arrays;

public class ReversePairs {

    // T(i, j) = T(i, m) + T(m+1, j) + C。
    // 这里的C就是处理合并两个部分的子问题，那么用文字来描述就是"已知翻转对的两个数字分别在子数组 nums[i, m] 和 nums[m+1, j] 之中，
    // 求满足要求的翻转对的个数"，这里翻转对的两个条件中的顺序条件已经满足，就只需要找到满足大小关系的的数对即可。
    // 这里两个数字都是不确定的，如果用暴力搜索肯定会被 OJ 唾弃。
    // 但是如果两个子数组是有序的，那么我们可以用双指针的方法在线性时间内就可以统计出符合题意的翻转对的个数。
    // 要想办法产生有序的子数组，那么这就和 MergeSort 的核心思想完美匹配了。
    // 我们知道混合排序就是不断的将数组对半拆分成子数组，拆到最小的数组后开始排序，然后一层一层的返回，最后原数组也是有序的了。
    // 这里我们在混合排序的递归函数中，对有序的两个子数组进行统计翻转对的个数，
    // 区间 [left, mid] 和 [mid+1, right] 内的翻转对儿个数就被分别统计出来了，
    // 此时还要统计翻转对儿的两个数字分别在两个区间中的情况，那么i遍历 [left, mid] 区间所有的数字，
    // j则从 mid+1 开始检测，假如 nums[i] 大于 nums[j] 的二倍，则这两个数字就是翻转对，此时j再自增1，
    // 直到不满足这个条件停止，则j增加的个数就是符合题意的翻转对的个数，
    // 所以用当前的j减去其初始值 mid+1 即为所求，然后再逐层返回
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    int mergeSort(int[] nums, int left, int right) {
        if (left >= right) return 0;
        int mid = left + (right - left) / 2;
        int res = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);
        for (int i = left, j = mid + 1; i <= mid; ++i) {
            while (j <= right && nums[i] / 2.0 > nums[j]) {
                ++j;
            }
            res += j - (mid + 1);
        }
        Arrays.sort(nums, left, right + 1);
        return res;
    }
}
