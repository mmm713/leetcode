package com.home.learn.google;

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence {
    //计算出1到n的阶乘数方便计算
    //a1 = k / (n - 1)!
    //k1 = k
    //
    //a2 = k1 / (n - 2)!
    //k2 = k1 % (n - 2)!
    //...
    //
    //an-1 = kn-2 / 1!
    //kn-1 = kn-2 % 1!
    //
    //an = kn-1 / 0!
    //kn = kn-1 % 0!
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
