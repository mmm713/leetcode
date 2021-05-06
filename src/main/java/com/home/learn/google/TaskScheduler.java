package com.home.learn.google;

public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        int max = 0, maxCount = 0;
        int[] hash = new int[26];
        for (char task : tasks) {
            if (++hash[task - 'A'] >= max) {
                if (hash[task - 'A'] == max) {
                    maxCount++;
                } else {
                    max = hash[task - 'A'];
                    maxCount = 1;
                }
            }
        }
        int singleOpen = n - maxCount + 1;
        if(singleOpen == 0) return tasks.length;
        int openSpot = singleOpen * (max - 1);
        int taskLeft = tasks.length - max * maxCount;
        int idleNeeded = openSpot - taskLeft;
        return tasks.length + Math.max(0, idleNeeded);
    }
}
