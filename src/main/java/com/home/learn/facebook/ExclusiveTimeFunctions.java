package com.home.learn.facebook;

import java.util.List;
import java.util.Stack;

public class ExclusiveTimeFunctions {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<Integer> id = new Stack<>();
        int prev = 0;
        // pre means the start of the interval
        for (String s : logs) {
            String[] log = s.split(":");
            if (log[1].equals("start")) {
                if (!id.isEmpty()) {
                    res[id.peek()] += Integer.parseInt(log[2]) - prev;
                }
                id.push(Integer.parseInt(log[0]));
                prev = Integer.parseInt(log[2]);
            } else {
                res[id.pop()] += Integer.parseInt(log[2]) - prev + 1;
                if (!id.isEmpty()) {
                    prev = Integer.parseInt(log[2]) + 1;
                }
            }
        }
        return res;
    }
}
