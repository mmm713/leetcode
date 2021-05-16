package com.home.learn.google;

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence {
    public String getPermutation(int n, int k) {
        int[] factorials = new int[n];
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        factorials[0] = 1;
        for(int i = 1; i < n; ++i) {
            factorials[i] = factorials[i - 1] * i;
            nums.add(i + 1);
        }
        --k;
        // compute factorial representation of k
        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            int idx = k / factorials[i];
            k %= factorials[i];
            sb.append(nums.get(idx));
            nums.remove(idx);
        }
        return sb.toString();
    }
}
