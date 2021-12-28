package com.home.learn.amazon;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class MaximumNumberTasksAssign {
    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        Arrays.sort(tasks);
        Arrays.sort(workers);
        int left = 1, right = Math.min(tasks.length, workers.length), ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (check(mid, pills, strength, workers, tasks)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    private boolean check(int mid, int pills, int strength, int[] workers, int[] tasks) {
        //ws位置工作能力递减的queue
        Deque<Integer> ws = new LinkedList<>();
        int ptr = workers.length - 1;
        // 从大到小枚举每一个任务
        for (int i = mid - 1; i >= 0; --i) {
            while (ptr >= workers.length - mid && workers[ptr] + strength >= tasks[i]) {
                ws.offer(workers[ptr]);
                --ptr;
            }
            if (ws.isEmpty()) {
                return false;
            } else if (ws.peek() >= tasks[i]) {
                ws.poll();
            } else if (pills <= 0) {
                return false;
            } else {
                --pills;
                ws.pollLast();
            }
        }
        return true;
    }
}
