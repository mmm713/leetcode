package com.home.learn.wish;

import java.util.PriorityQueue;

public class NearlySortedArray {
    public void kSort(int[] arr, int n, int k)
    {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for(int i = 0; i < k + 1; i++) {
            priorityQueue.add(arr[i]);
        }
        int index = 0;
        for(int i = k + 1; i < n; i++) {
            arr[index++] = priorityQueue.poll();
            priorityQueue.add(arr[i]);
        }
        while(!priorityQueue.isEmpty()) {
            arr[index++] = priorityQueue.poll();
        }
    }
}
