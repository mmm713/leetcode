package com.home.learn.leetcode;

import java.util.*;

public class MeetingRooms {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        for(int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] < intervals[i - 1][1])
                return false;
        }
        return true;
    }

    public int minMeetingRooms(int[][] intervals) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        for (int[] interval : intervals) {
            if (!heap.isEmpty() && interval[0] >= heap.peek()) {
                heap.poll();
            }
            heap.add(interval[1]);
        }
        return heap.size();
    }

    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(slots2, Comparator.comparingInt(a -> a[0]));
        List<Integer> result = new ArrayList<>();
        int i=0, j =0;
        while(i < slots1.length && j < slots2.length){
            int maxStart = Math.max(slots1[i][0], slots2[j][0]);
            int minEnd = Math.min(slots1[i][1], slots2[j][1]);
            if(maxStart < minEnd && minEnd - maxStart >= duration) {
                result.add(maxStart);
                result.add(maxStart + duration);
                return result;
            }
            if(slots2[j][1] >= slots1[i][1]) {
                i++;
            } else {
                j++;
            }
        }
        return result;
    }
}
