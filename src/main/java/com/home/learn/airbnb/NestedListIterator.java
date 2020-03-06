package com.home.learn.airbnb;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/*
Iterator for list<list<int>> only
 */
public class NestedListIterator implements Iterator<Integer>  {
    private Iterator<List<Integer>> listIter;  // we use list iterator
    private Iterator<Integer> iter;

    public NestedListIterator(List<List<Integer>> list)  {
        listIter = list.iterator();
        iter = null;
    }

    @Override
    public boolean hasNext() {
        while ((iter == null || !iter.hasNext()) && listIter.hasNext()) {
            List<Integer> next = listIter.next();
            if (next != null) {
                iter = next.iterator();
            }
        }
        return iter != null && iter.hasNext();
    }

    @Override
    public Integer next() {
        return iter.next();
    }

    @Override
    public void remove() {
        if (iter == null) {
            return;
        }
        iter.remove();
    }
}
