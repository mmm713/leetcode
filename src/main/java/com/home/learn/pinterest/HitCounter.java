package com.home.learn.pinterest;

import java.util.ArrayDeque;
import java.util.Queue;

public class HitCounter {

    Queue<Integer> hitQueue;
    /** Initialize your data structure here. */
    public HitCounter() {
        hitQueue = new ArrayDeque<>(300);
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        cleanQueue(timestamp);
        hitQueue.offer(timestamp);
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        cleanQueue(timestamp);
        return hitQueue.size();
    }

    private void cleanQueue(int timestamp) {
        while(!hitQueue.isEmpty() && hitQueue.peek() <= timestamp - 300) {
            hitQueue.poll();
        }
    }
}
