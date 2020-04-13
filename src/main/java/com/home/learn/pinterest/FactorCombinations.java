package com.home.learn.pinterest;

import java.util.ArrayList;
import java.util.List;

public class FactorCombinations {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), 2, n);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> temp, int start, int target) {
        for(int i = start; i <= Math.sqrt(target); i++) {
            if(target % i == 0) {
                List<Integer> tempNew = new ArrayList<>(temp);
                tempNew.add(i);
                backtrack(result, tempNew, i, target / i);
                tempNew.add(target / i);
                result.add(tempNew);
            }
        }
    }
}
