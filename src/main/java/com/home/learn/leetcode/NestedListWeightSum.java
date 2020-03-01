package com.home.learn.leetcode;


import com.home.learn.library.NestedInteger;

import java.util.ArrayList;
import java.util.List;

public class NestedListWeightSum {
    public int depthSum(List<NestedInteger> nestedList) {
        return depthSumHelper(nestedList, 1);
    }

    public int depthSumInverse(List<NestedInteger> nestedList) {
        int unweighted = 0, weighted = 0;
        while (!nestedList.isEmpty()) {
            List<NestedInteger> nextLevel = new ArrayList<>();
            for (NestedInteger nestedInteger : nestedList) {
                if (nestedInteger.isInteger()) {
                    unweighted += nestedInteger.getInteger();
                } else {
                    nextLevel.addAll(nestedInteger.getList());
                }
            }
            weighted += unweighted;
            nestedList = nextLevel;
        }
        return weighted;
    }

    private int depthSumHelper(List<NestedInteger> nestedList, int level) {
        int result = 0;
        for(NestedInteger nestedInteger : nestedList) {
            if(nestedInteger.isInteger()) {
                result += level * nestedInteger.getInteger();
            } else {
                result += depthSumHelper(nestedInteger.getList(), level + 1);
            }
        }
        return result;
    }
}
