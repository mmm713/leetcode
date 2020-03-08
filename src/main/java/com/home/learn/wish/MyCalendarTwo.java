package com.home.learn.wish;

import java.util.TreeMap;

public class MyCalendarTwo {
    TreeMap<Integer, Integer> treeMap;
    TreeMap<Integer, Integer> overlap;

    public MyCalendarTwo() {
        this.treeMap = new TreeMap<>();
        this.overlap = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        if(checkTripleBooked(start, end)) return false;
        Integer pre_key = treeMap.floorKey(start);
        if(pre_key != null && treeMap.get(pre_key) > start) {
            overlap.put(start, Math.min(end, treeMap.get(pre_key)));
            start = Math.min(start, pre_key);
            end = Math.max(end, treeMap.get(pre_key));
        }
        Integer next_key = treeMap.higherKey(start);
        while(next_key != null && next_key < end) {
            overlap.put(next_key, Math.min(end, treeMap.get(next_key)));
            end = Math.max(end, treeMap.get(next_key));
            treeMap.remove(next_key);
            next_key = treeMap.higherKey(next_key);
        }
        treeMap.put(start, end);
        return true;
    }

    private boolean checkTripleBooked(int start, int end) {
        Integer pre_key = overlap.floorKey(start);
        if(pre_key != null && overlap.get(pre_key) > start) {
            return true;
        }
        Integer next_key = overlap.ceilingKey(start);
        return next_key != null && next_key < end;
    }
}
