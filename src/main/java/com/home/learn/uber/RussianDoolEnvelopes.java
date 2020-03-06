package com.home.learn.uber;

import java.util.*;

public class RussianDoolEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);
        List<Integer> result = new ArrayList<>();
        for (int[] envelope : envelopes) {
            int height = envelope[1];
            if (result.isEmpty() || result.get(result.size() - 1) < height) {
                result.add(height);
            }
            int index = Collections.binarySearch(result, height);
            if (index < 0) {
                result.set(-index - 1, height);
            }
        }
        return result.size();
    }
}
