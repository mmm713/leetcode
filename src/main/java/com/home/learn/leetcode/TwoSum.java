package com.home.learn.leetcode;

import com.home.learn.library.TreeNode;

import java.util.*;

public class TwoSum {
    public int numberOfWays(int[] arr, int k) {
        Arrays.sort(arr);
        int i = 0, j = arr.length - 1;
        int count = 0;
        while(i < j) {
            if(arr[i] + arr[j] < k) {
                i++;
            } else if(arr[i] + arr[j] > k) {
                j--;
            } else {
                if(arr[i] == arr[j]) {
                    return count + combination(j - i + 1, 2);
                } else {
                    int left = 1;
                    int right = 1;
                    while(i + 1 < j && arr[i] == arr[i + 1]) {
                        i++;
                        left++;
                    }
                    while(i < j - 1 && arr[j] == arr[j - 1]) {
                        j--;
                        right++;
                    }
                    i++;
                    j--;
                    count += left * right;
                }
            }
        }
        return count;
    }

    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> table = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int second = target - nums[i];
            if(table.containsKey(second)) {
                result[0] = table.get(second);
                result[1] = i;
                break;
            }
            table.put(nums[i], i);
        }
        return result;
    }

    public int[] twoSumSorted(int[] numbers, int target) {
        int p1 = 0;
        int p2 = numbers.length-1;
        while(p1 < p2){
            if(numbers[p1] + numbers[p2]==target){
                return new int[]{p1 + 1, p2 + 1};
            } else if (numbers[p1] + numbers[p2] > target){
                p2--;
            } else {
                p1++;
            }
        }
        return new int[2];
    }
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        int i = 0, j = list.size() - 1;
        while (i < j) {
            int sum = list.get(i) + list.get(j);
            if (sum == k) return true;
            if (sum < k) {
                i++;
            }
            else {
                j--;
            }
        }
        return false;
    }

    private void inOrder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }

    Map<Integer, Integer> map;
    int min;
    int max;

    public TwoSum() {
        map= new HashMap<>();
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
    }

    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
        min = Math.min(min, number);
        max = Math.max(max, number);
    }

    public boolean find(int value) {
        if(value < min * 2 || value > max * 2){
            return false;
        }
        for (Map.Entry<Integer, Integer> m : map.entrySet()) {
            int c = value - m.getKey();
            if (c == m.getKey() && m.getValue() > 1)
                return true;
            if (c != m.getKey() && map.containsKey(c))
                return true;
        }
        return false;
    }

    private int combination(int n, int r) {
        return factorial(n) / (factorial(r) * factorial(n-r));
    }

    private int factorial(int n) {
        int fact = 1;
        int i = 1;
        while(i <= n) {
            fact *= i;
            i++;
        }
        return fact;
    }
}
