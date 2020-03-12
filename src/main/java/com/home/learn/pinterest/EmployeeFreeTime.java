package com.home.learn.pinterest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EmployeeFreeTime {
    static class Interval {
        public int start;
        public int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public List<Interval> employeeFreeTime(List<List<Interval>> avails) {
        List<Interval> sortedIntervals = avails.stream()
                .flatMap(Collection::stream)
                .sorted(Comparator.comparingInt(o -> o.start))
                .collect(Collectors.toList());

        return IntStream.range(1, sortedIntervals.size())
                .mapToObj(i -> {
                    if(sortedIntervals.get(i - 1).end < sortedIntervals.get(i).start) {
                        return new Interval(sortedIntervals.get(i - 1).end, sortedIntervals.get(i).start);
                    } else {
                        sortedIntervals.get(i).end = Math.max(sortedIntervals.get(i - 1).end, sortedIntervals.get(i).end);
                        return null;
                    }})
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    static class Job {
        int employeeId, intervalIndex;
        public Job(int employeeId, int intervalIndex) {
            this.employeeId = employeeId;
            this.intervalIndex = intervalIndex;
        }
    }

    public List<Interval> employeeFreeTimeII(List<List<Interval>> avails) {
        List<Interval> result = new ArrayList<>();
        PriorityQueue<Job> pq = new PriorityQueue<>(Comparator.comparingInt(a -> avails.get(a.employeeId).get(a.intervalIndex).start));
        int employeeId = 0, startTime = Integer.MAX_VALUE;
        for (List<Interval> employee: avails) {
            pq.offer(new Job(employeeId++, 0));
            startTime = Math.min(startTime, employee.get(0).start);
        }
        while (!pq.isEmpty()) {
            Job job = pq.poll();
            Interval interval = avails.get(job.employeeId).get(job.intervalIndex);
            if (startTime < interval.start)
                result.add(new Interval(startTime, interval.start));
            startTime = Math.max(startTime, interval.end);
            if (++job.intervalIndex < avails.get(job.employeeId).size())
                pq.offer(job);
        }
        return result;
    }
}
