package com.home.learn.leetcode;

import com.home.learn.library.NestedInteger;

import java.util.Iterator;
import java.util.List;

public class NestedIntegerIterator implements Iterator<Integer> {
    private List<NestedInteger> nestedList;
    private int index;
    private NestedIntegerIterator curIterator = null;
    private Integer curValue = null;

    public NestedIntegerIterator(List<NestedInteger> nestedList) {
        this.nestedList = nestedList;
        this.index = 0;
    }

    @Override
    public Integer next() {
        if (curValue != null) {
            Integer toRet = curValue;
            curValue = null;
            return toRet;
        }

        if (curIterator != null) {
            if(curIterator.hasNext()) {
                return curIterator.next();
            } else {
                curIterator = null;
            }
        }

        while(index < nestedList.size()) {
            NestedInteger nestedInteger = nestedList.get(index);
            index++;

            //return if integer
            if (nestedInteger.isInteger()) {
                return nestedInteger.getInteger();
            } else {
                curIterator = new NestedIntegerIterator(nestedInteger.getList());
                return next();
            }
        }

        return null;
    }

    @Override
    public boolean hasNext() {
        if (curValue == null) {
            curValue = next();
        }
        return curValue != null;
    }

}
