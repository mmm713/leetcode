package com.home.learn.uber;

import java.util.ArrayList;
import java.util.List;

public class MinimumPeakElements {
    public List<Integer> minPeaks(ArrayList<Integer> list) {
        int n = list.size();
        List<Integer> result = new ArrayList<>();

        // Traverse each element of list
        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int index = -1;
            int size = list.size();
            for (int j = 0; j < size; j++) {
                if (j == 0 && j + 1 < size) {
                    if (list.get(j) > list.get(j + 1)
                            && min > list.get(j)) {
                        min = list.get(j);
                        index = j;
                    }
                } else if (j == size - 1 && j - 1 >= 0) {
                    if (list.get(j)
                            > list.get(j - 1)
                            && min
                            > list.get(j)) {
                        min = list.get(j);
                        index = j;
                    }
                } else if (size == 1) {
                    min = list.get(j);
                    index = j;
                } else if (list.get(j) > list.get(j - 1) && list.get(j) > list.get(j + 1) && min > list.get(j)) {
                    min = list.get(j);
                    index = j;
                }
            }
            list.remove(index);
            result.add(min);
        }
        return result;
    }
}
