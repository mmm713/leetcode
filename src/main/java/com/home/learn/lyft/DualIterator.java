package com.home.learn.lyft;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

public class DualIterator<T extends Comparable<T>> implements Iterator<T> {

    Iterator<T> iterator0;
    Iterator<T> iterator1;
    Queue<T> buffer;
    Iterator<T> toCompare;
    public DualIterator(Iterator<T> iterator0, Iterator<T> iterator1) {
        this.iterator0 = iterator0;
        this.iterator1 = iterator1;
        buffer = new ArrayDeque<>(1);
    }

    @Override
    public boolean hasNext() {
        if(!buffer.isEmpty()) {
            return true;
        }
        return iterator0.hasNext() || iterator1.hasNext();
    }

    @Override
    public T next() {
        if(!buffer.isEmpty()) {
            T buf = buffer.peek();
            if(toCompare.hasNext()) {
                T val = toCompare.next();
                if(val.compareTo(buf) <= 0) {
                    return val;
                } else {
                    buffer.offer(val);
                    toCompare = swap(toCompare);
                    return buffer.poll();
                }
            } else {
                toCompare = swap(toCompare);
                return buffer.poll();
            }

        } else if(iterator0.hasNext() && iterator1.hasNext()) {
            T val0 = iterator0.next();
            T val1 = iterator1.next();
            if(val0.compareTo(val1) <= 0) {
                buffer.offer(val1);
                toCompare = iterator0;
                return val0;
            } else {
                buffer.offer(val0);
                toCompare = iterator1;
                return val1;
            }
        } else if(iterator0.hasNext()) {
            return iterator0.next();
        } else if(iterator1.hasNext()) {
            return iterator1.next();
        }
        return null;
    }

    private Iterator<T> swap(Iterator<T> i0) {
        if(i0.equals(iterator0)) {
            return iterator1;
        } else {
            return iterator0;
        }
    }
}
