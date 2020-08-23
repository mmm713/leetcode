package com.home.learn.airbnb;

import com.home.learn.library.NestedInteger;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

public class NestedIntegerIteratorV3 implements Iterator<Integer> {

    Stack<ListIterator<NestedInteger>> stack;
    NestedInteger current;

    public NestedIntegerIteratorV3(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        current = null;
        stack.push(nestedList.listIterator());
    }


    @Override
    public Integer next() {
        Integer result = null;
        if(hasNext()){
            result = current.getInteger();
            current = null;
        }
        return result;
    }

    @Override
    public boolean hasNext() {
        if(current !=null){
            return true;
        }
        while(!stack.isEmpty()){
            ListIterator<NestedInteger> peek = stack.peek();
            if(peek.hasNext()){
                NestedInteger curr = peek.next();
                if(curr.isInteger()){
                    current = curr;
                    return true;
                }else{
                    stack.push(curr.getList().listIterator());
                }
            } else {
                stack.pop();
            }
        }
        return false;
    }

}
