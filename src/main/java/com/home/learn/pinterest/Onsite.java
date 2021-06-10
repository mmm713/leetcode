package com.home.learn.pinterest;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Onsite {
    public static boolean validShards(List<List<int[]>> intervals) {
        for(int i = 0; i < intervals.size(); i++) {
            //merge within shard
            intervals.set(i, mergeIntervals(intervals.get(i)));
            int finalI = i;
            sortedIntervals.addAll(intervals.get(i).stream().map(o -> Pair.of(o, String.valueOf(finalI))).collect(Collectors.toList()));
        }
        List<int[]> allIntervals = intervals.stream().flatMap(Collection::stream)
                .sorted(Comparator.comparingInt(o -> o[0])).collect(Collectors.toList());
        for(int i = 1; i < allIntervals.size(); i++) {
            if(allIntervals.get(i)[0] <= allIntervals.get(i - 1)[1]) {
                return false;
            }
        }
        sortedIntervals.sort(Comparator.comparing(o -> o.getKey()[0]));
        return true;
    }

    private static List<Pair<int[], String>> sortedIntervals = new ArrayList<>();

    private static List<int[]> mergeIntervals(List<int[]> input) {
        List<int[]> result = new ArrayList<>();
        input.sort(Comparator.comparingInt(o -> o[0]));
        int[] window = input.get(0);
        for(int i = 1; i < input.size(); i++) {
            if(input.get(i)[0] > window[1]) {
                result.add(window.clone());
                window[0] = input.get(i)[0];
            }
            window[1] = input.get(i)[1];
        }
        result.add(window.clone());
        return result;
    }
}
