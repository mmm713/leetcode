package com.home.learn.google;

public class TaskScheduler {
    //贪婪思想。任务最多的人相当于开头。
    //比如A最多，idle = 2， 那么起始状态为 A _ _ A _ _ 去填空
    //singleOpen代表一个A _ _ 内可否被最频繁的任务填满，如果为非正数，则表示所有人可以依次运行，返回总长度即可`
    //openSpot代表剩下还有多少空位 max - 1因为最后一个空白可以不填满
    //taskLeft代表剩下多少非最频繁的任务
    //需要的idle数等于空位减去任务。如果任务多了则不需要idle
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
        if(singleOpen <= 0) return tasks.length;
        int openSpot = singleOpen * (max - 1);
        int taskLeft = tasks.length - max * maxCount;
        int idleNeeded = openSpot - taskLeft;
        return tasks.length + Math.max(0, idleNeeded);
    }
}
