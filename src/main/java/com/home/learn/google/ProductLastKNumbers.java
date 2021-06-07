package com.home.learn.google;

import java.util.ArrayList;
import java.util.List;

public class ProductLastKNumbers {
    List<Integer> list;
    int prev;
    public ProductLastKNumbers() {
        list = new ArrayList<>();
        list.add(1);
        prev = 1;
    }

    public void add(int num) {
        if(num > 0) {
            prev *= num;
            list.add(prev);
        } else {
            // if this is 0, then re-init structure
            list = new ArrayList<>();
            list.add(1);
            prev = 1;
        }
    }

    public int getProduct(int k) {
        //if k < N, means there is no 0 before
        int N = list.size();
        return k < N ? prev / list.get(N-k-1) : 0;
    }
}
