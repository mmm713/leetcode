package com.home.learn.leetcode.segment.tree;

public class CountRangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        //preSum[i] = a[0] + a[1] + ... + a[i-1]
        //preSum[j] = a[0] + a[1] + ... +  ... + a[i] + ... + a[j-1]
        //preSum[j] - preSum[i] = a[j-1] + ... + a[i]
        //preSum[i+1] = a[0] + a[1] + ... + a[i]
        //preSum[i+1] = preSum[i] + a[i]
        //preSum[1] = preSum[0] + a[0]
        int n = nums.length;
        long[] preSum = new long[1 + n];
        for(int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        long[] aux = new long[1 + n];
        return dfs(preSum, 0, n, aux, lower, upper);
    }
    private int dfs(long[] nums, int lo, int hi, long[] aux, int lower, int upper) {
        if(lo >= hi) return 0;
        int mid = lo + (hi - lo) / 2;

        int a = dfs(nums, lo, mid, aux, lower, upper);
        int b = dfs(nums, mid + 1, hi, aux, lower, upper);

        int c = 0;
        int j = mid + 1;
        int k = mid + 1;
        for(int i = lo; i <= mid; i++) {
            while(j <= hi && nums[j] - nums[i] < lower) {
                j++;
            }
            while(k <= hi && nums[k] - nums[i] <= upper) {
                k++;
            }
            c += k - j;
        }
        merge(nums, lo, mid, hi, aux);
        return a + b + c;
    }

    private void merge(long[] a, int lo, int mid, int hi, long[] aux) {
        int i = lo;
        int j = mid + 1;
        for(int k = lo; k <= hi; k++) {
            if(i == mid + 1) {
                aux[k] = a[j++];
            }else if(j == hi + 1) {
                aux[k] = a[i++];
            }else if(a[i] > a[j]) {
                aux[k] = a[j++];
            }else {
                aux[k] = a[i++];
            }
        }
        if (hi + 1 - lo >= 0) System.arraycopy(aux, lo, a, lo, hi + 1 - lo);
    }
    private int solve(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] tree = new long[1 + n];
        for(int i = 0; i < nums.length; i++) {
            tree[i + 1] = nums[i];
        }
        init(tree);

        int count = 0;
        for(int j = 1; j <= n; j++) {
            for(int i = 1; i <= j; i++) {
                long sum = preSum(tree, j) - preSum(tree, i - 1);
                if(sum >= lower && sum <= upper){
                    count++;
                }
            }
        }
        return count;
    }

    private void init(long[] tree) {
        for(int i = 1; i < tree.length; i++) {
            int j = i + (i & -i);
            if(j < tree.length)
                tree[j] += tree[i];
        }
    }
    private void update(long[] tree, int i, int x) {
        while(i < tree.length) {
            tree[i] += x;
            i += i & -i;
        }
    }
    private long preSum(long[] tree, int i) {
        long sum = 0;
        while(i > 0) {
            sum += tree[i];
            i -= i & -i;
        }
        return sum;
    }
}
