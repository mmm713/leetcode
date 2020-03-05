package com.home.learn.airbnb;

import com.home.learn.library.NestedInteger;

import java.util.Iterator;
import java.util.List;

public class NestedIntegerIteratorV2 implements Iterator<Integer> {
    Iterator<NestedInteger> nestedIntegerIter;
    NestedInteger currNode;
    NestedIntegerIteratorV2 iter;

    public NestedIntegerIteratorV2(List<NestedInteger> nestedList) {
        nestedIntegerIter = nestedList.iterator();
        findNext();
    }

    @Override
    public Integer next() {
        if(!currNode.isInteger()) {
            return iter.next();
        } else {
            Integer n = currNode.getInteger();
            findNext();
            return n;
        }
    }

    @Override
    public boolean hasNext() {
        if (currNode != null && !currNode.isInteger()) {
            if(iter.hasNext()) {
                return true;
            }
            findNext();
        }
        return currNode != null;
    }

    private void findNext() {
        while(nestedIntegerIter.hasNext()) {
            currNode = nestedIntegerIter.next();
            if(currNode.isInteger()) {
                return;
            } else {
                iter = new NestedIntegerIteratorV2(currNode.getList());
                if(iter.hasNext()) {
                    return;
                }
            }
        }
        currNode = null;
    }
}
