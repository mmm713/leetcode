package com.home.learn.facebook;

import com.home.learn.Helpers;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> temp =  new ArrayList<>();
            if(i == 0) {
                temp.add(1);
            } else {
                List<Integer> prev = res.get(i - 1);
                for (int j = 0; j <= prev.size(); j++) {
                    if(j == 0 || j == prev.size()) {
                        temp.add(1);
                    } else {
                        int a = prev.get(j);
                        int b = prev.get(j - 1);
                        temp.add(a + b);
                    }
                }
            }
            res.add(temp);
        }
        return res;
    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        result.add(1);
        for(int i = 1; i < rowIndex + 1; i++) {
            if((i * 2) >= (rowIndex + 1) ) {
                result.add(result.get(rowIndex - i));
            } else if(i == 1) {
                result.add(i + rowIndex - 1);
            } else {
                double res = (double)(result.get(i - 1)) * (double)(result.get(1) - i + 1) / (double) i;
                result.add((int) res);
            }
        }
        return result;
    }

    public List<Integer> getRowII(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            res.add((int) Helpers.combination(rowIndex, i));
        }
        return res;
    }
}
