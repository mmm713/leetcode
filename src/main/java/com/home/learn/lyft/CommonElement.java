package com.home.learn.lyft;

import java.util.Iterator;

public class CommonElement<T extends Comparable<T>> implements Iterator<T> {

    Iterator<T> iterator0;
    Iterator<T> iterator1;
    private T nextVal;

    public CommonElement(Iterator<T> iterator0, Iterator<T> iterator1) {
        this.iterator0 = iterator0;
        this.iterator1 = iterator1;
    }

    @Override
    public boolean hasNext() {
        nextVal = seekVal();
        return nextVal != null;
    }

    @Override
    public T next() {
        if(nextVal == null) {
            throw new RuntimeException("Calling iterator next illegally.");
        }
        return nextVal;
    }

    private T seekVal() {
        T val0 = getVal(iterator0);
        T val1 = getVal(iterator1);
        while(val0 != null && val1 != null) {
            if(nextVal != null) {
                if(val0.equals(nextVal)) {
                    val0 = getVal(iterator0);
                    continue;
                }
                if(val1.equals(nextVal)) {
                    val1 = getVal(iterator1);
                    continue;
                }
            }
            if(val0.equals(val1)) {
                return val0;
            } else if(val0.compareTo(val1) < 0) {
                val0 = getVal(iterator0);
            } else {
                val1 = getVal(iterator1);
            }
        }
        return null;
    }

    private T getVal(Iterator<T> iterator) {
        return iterator.hasNext() ? iterator.next() : null;
    }
}
