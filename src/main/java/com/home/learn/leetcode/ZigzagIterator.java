package com.home.learn.leetcode;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class ZigzagIterator {

    private Queue<Iterator<Integer>> q;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        q = new ArrayDeque<>();
        if (v1 != null && !v1.isEmpty()) q.offer(v1.iterator());
        if (v2 != null && !v2.isEmpty()) q.offer(v2.iterator());
    }

    public ZigzagIterator(List<List<Integer>> lists) {
        q = new ArrayDeque<>();
        for (List<Integer> list : lists)
            if (list != null && !list.isEmpty())
                q.offer(list.iterator());
    }

    public int next() {
        if(!q.isEmpty()) {
            Iterator<Integer> i = q.poll();
            int res = i.next();
            if (i.hasNext()) q.offer(i);
            return res;
        } else {
            throw new RuntimeException();
        }
    }

    public boolean hasNext() {
        return !q.isEmpty();
    }
}
