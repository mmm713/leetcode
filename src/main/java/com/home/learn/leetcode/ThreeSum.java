package com.home.learn.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ThreeSum {
    //3 sum smaller
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            sum += twoSumSmaller(nums, i + 1, target - nums[i]);
        }
        return sum;
    }

    private int twoSumSmaller(int[] nums, int startIndex, int target) {
        int sum = 0;
        int left = startIndex;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                sum += right - left;
                left++;
            } else {
                right--;
            }
        }
        return sum;
    }

    //3 sum closest
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = nums[0] + nums[1] + nums[2];
        for (int left = 0; left <= nums.length - 3; left++) {
            int mid = left + 1;
            int right = nums.length -1;
            while (mid < right){
                int threeSum = nums[left] + nums[mid] + nums[right];

                if (Math.abs(sum - target) > Math.abs(threeSum - target)){
                    sum = threeSum;
                }
                if (threeSum == target)
                    return target;

                if (threeSum > target) right --;
                else mid ++;
            }

        }
        return sum;
    }

    //3 sum 有重复
    public int threeSumMulti(int[] A, int target) {
        long ans = 0;
        Arrays.sort(A);
        for (int i = 0; i < A.length; ++i) {
            // We'll try to find the number of i < j < k
            // with A[j] + A[k] == T, where T = target - A[i].
            // The below is a "two sum with multiplicity".
            int T = target - A[i];
            int j = i+1, k = A.length - 1;
            while (j < k) {
                // These steps proceed as in a typical two-sum.
                if (A[j] + A[k] < T)
                    j++;
                else if (A[j] + A[k] > T)
                    k--;
                else if (A[j] != A[k]) {  // We have A[j] + A[k] == T.
                    // Let's count "left": the number of A[j] == A[j+1] == A[j+2] == ...
                    // And similarly for "right".
                    int left = 1, right = 1;
                    while (j+1 < k && A[j] == A[j+1]) {
                        left++;
                        j++;
                    }
                    while (k-1 > j && A[k] == A[k-1]) {
                        right++;
                        k--;
                    }
                    ans += left * right;
                    j++;
                    k--;
                } else {
                    // M = k - j + 1
                    // We contributed M * (M-1) / 2 pairs.
                    ans += (k-j+1) * (k-j) / 2;
                    break;
                }
            }
        }
        return (int) ans;
    }

    //3sum

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int low, high, sum;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 2;i++) {
            if(i == 0 || nums[i] != nums[i - 1]) {
                low = i+1;
                high = nums.length - 1;
                sum = -nums[i];
                while(low < high) {
                    if(nums[low] + nums[high] == sum) {
                        result.add(Arrays.asList(nums[low], nums[high], nums[i]));
                        while(low < high && nums[high] == nums[high - 1]) {
                            high--;
                        }
                        while(low < high && nums[low] == nums[low + 1]) {
                            low++;
                        }
                        low++;
                        high--;
                    }
                    else if(nums[low] + nums[high] < sum) {
                        low++;
                    }
                    else {
                        high--;
                    }
                }
            }
        }
        return result;
    }
}
