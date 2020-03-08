package com.home.learn.wish;

import java.util.*;


/* LeetCode原题
1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times
 */
public class RandomPickWithWeight {
    double[] prob;

    public RandomPickWithWeight(int[] w) {
        prob = new double[w.length];
        double sum = Arrays.stream(w).sum();
        for(int i = 0; i < w.length; i++) {
            w[i] += i == 0 ? 0 : w[i - 1];
            prob[i] = w[i] / sum;
        }
    }

    public int pickIndex() {
        return Math.abs(Arrays.binarySearch(prob, Math.random())) - 1;
    }
}


/*
变形1：weight可以为0，解法是用二分找到最左边的index
Test的话，对于这种random的题，可以加个seed用pseudo random
或者画个histogram出来，详见main（）
*/
class RandomPickWithNonNegativeWeight {   // Notice: weight can be 0

    Random rand;
    int[] preSum;

    public RandomPickWithNonNegativeWeight(int[] weights) {  // weight[i] >= 0
        if (weights == null || weights.length == 0) return;
        rand = new Random();
        preSum = new int[weights.length];
        preSum[0] = weights[0];
        for (int i = 1; i < weights.length; i++) {
            preSum[i] = preSum[i-1] + weights[i];
        }
    }

    public RandomPickWithNonNegativeWeight(int[] weights, long seed) {  // weight[i] >= 0
        if (weights == null || weights.length == 0) return;
        rand = new Random(seed);
        preSum = new int[weights.length];
        preSum[0] = weights[0];
        for (int i = 1; i < weights.length; i++) {
            preSum[i] = preSum[i-1] + weights[i];
        }
    }

    public int pickIndex() {
        int max = preSum[preSum.length-1];
        int weight = rand.nextInt(max) + 1;   // range [1,max]
        return binarySearch(weight);
    }

    private int binarySearch(int target) {  // find the leftmost index
        int l = 0, r = preSum.length-1;
        while (l < r) {
            int mid = l + (r-l) / 2;
            if (preSum[mid] >= target) {
                r = mid;
            } else {
                l = mid+1;
            }
        }
        return l;
    }


    public static void main(String[] args) {

        // Test 1: pseudo-random
        int[] weights = {1,1,0,2,0,0,3,5};   // preSum = {1,2,2,4,4,4,7,12}
        RandomPickWithNonNegativeWeight picker = new RandomPickWithNonNegativeWeight(weights, 100);

        // pseudo seq for seed 100:  12  7  0  10 8  3  4 10  3  1
        // expected idx:              7, 6, 0, 7, 7, 3, 3, 7, 3, 0
        int[] expectedIdx = {7, 6, 0, 7, 7, 3, 3, 7, 3, 0};
        for (int i = 0; i < 10; i++) {
            System.out.print(picker.pickIndex() + " ");
        }
        System.out.println();

        // Test 2: histogram
        int[] weights1 = {1,1,0,2,0,0,3,5};
        RandomPickWithNonNegativeWeight picker1 = new RandomPickWithNonNegativeWeight(weights1);
        int[] hist = new int[8];
        for (int j = 0; j < 10000; j++) {
            int idx = picker1.pickIndex();
            hist[idx]++;
        }
        for (int h : hist)
            System.out.print(h + " ");

    }

}


/*
变形2：weight可变，用binary index tree
*/
class RandomPickWithMutableWeights {   // weights are mutable -> use BIT to get prefix sum

    int[] tree;
    int[] arr;
    Random rand;
    int N;

    public RandomPickWithMutableWeights(int[] w) {
        rand = new Random();
        N = w.length;
        arr = new int[N];
        tree = new int[N+1];
        for (int i = 0; i < N; i++)
            update(i, w[i]);
    }

    public RandomPickWithMutableWeights(int[] w, long seed) {
        rand = new Random(seed);
        N = w.length;
        arr = new int[N];
        tree = new int[N+1];
        for (int i = 0; i < N; i++)
            update(i, w[i]);
    }

    private void update(int i, int val) {
        int diff = val - arr[i];
        arr[i] = val;
        for (int j = i+1; j <= N; j += (j & (-j))) {
            tree[j] += diff;
        }
    }

    private int getSum(int k) {
        int sum = 0;
        for (int j = k+1; j >= 1; j -= (j & (-j))) {
            sum += tree[j];
        }
        return sum;
    }

    public int pickIndex() {
        int max = getSum(N-1);
        int weight = rand.nextInt(max) + 1;
        System.out.println("max = " + max + "; weight = " + weight);
        int l = 0, r = N-1;
        while (l < r) {   // find leftmost index
            int mid = l + (r-l) / 2;
            if (getSum(mid) >= weight) {
                r = mid;
            } else {
                l = mid+1;
            }
        }
        return l;
    }
}