package com.home.learn.facebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BuildingWithOceanView {
    public int[] findBuildings(int[] heights) {
        int h = Integer.MIN_VALUE;
        List<Integer> res = new ArrayList<>();
        for (int i = heights.length - 1; i >= 0; i--) {
            if(i == heights.length - 1 || (heights[i] > h)) {
                res.add(i);
                h = heights[i];
            }
        }
        Collections.reverse(res);
        return res.stream().mapToInt(Integer::intValue).toArray();
    }


    public int maxDepth(String s) {
        int count = 0, res = 0;
        for(char c: s.toCharArray()) {
            if(c == '(') count++;
            res = Math.max(res, count);
            if(c == ')') count--;
        }
        return res;
    }
}
