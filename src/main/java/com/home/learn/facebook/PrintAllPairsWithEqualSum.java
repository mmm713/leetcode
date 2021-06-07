package com.home.learn.facebook;

import java.util.HashMap;
import java.util.Map;

public class PrintAllPairsWithEqualSum {
    public void findPairs(int[] arr){
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++){
            for(int j = i + 1; j < arr.length; j++){
                int sum = arr[i] + arr[j];
                if(map.containsKey(sum)){
                    int element = map.get(sum);
                    System.out.println("Pairs:(" + arr[i] +
                            "," + arr[j] + ") (" +
                            element + "," +
                            (sum - element) +
                            ") have sum:" + sum);
                } else {
                    map.put(sum, arr[i]);
                }
            }
        }

    }
}
