package com.home.learn.amazon;

import java.util.Arrays;

public class FindMinimumTimeFinishAllJobs {
    public int minimumTimeRequired(int[] jobs, int k) {
        Arrays.sort(jobs);
        int l = jobs[jobs.length - 1], r = Arrays.stream(jobs).sum();
        while (l < r) {
            int mid = l + (r - l) / 2;
            int[] workloads = new int[k];
            if (check(jobs, workloads, jobs.length - 1, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean check(int[] jobs, int[] workloads, int i, int limit) {
        if (i < 0) {
            return true;
        }
        int cur = jobs[i];
        for (int j = 0; j < workloads.length; ++j) {
            if (workloads[j] + cur <= limit) {
                workloads[j] += cur;
                if (check(jobs, workloads, i - 1, limit)) {
                    return true;
                }
                workloads[j] -= cur;
            }
            // 如果当前工人未被分配工作，那么下一个工人也必然未被分配工作
            // 或者当前工作恰能使该工人的工作量达到了上限
            // 这两种情况下我们无需尝试继续分配工作
            if (workloads[j] == 0 || workloads[j] + cur == limit) {
                break;
            }
        }
        return false;
    }
}
