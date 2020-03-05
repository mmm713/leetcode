package com.home.learn.airbnb;

//We are given an elevation map, heights[i] representing the height of the terrain at that index.
// The width at each index is 1. After V units of water fall at index K, how much water is at each index?
public class PourWater {
    public int[] pourWater(int[] heights, int V, int K) {
        for (int i = 0; i < V; i++)
            pour(heights, K);
        return heights;
    }

    private void pour(int[] heights, int x) {
        for (int i = x - 1; i >= 0; i--) {
            if (heights[i] == heights[x])
                continue;
            if (heights[i] > heights[x])
                break;
            pour(heights, i);
            return;
        }
        for (int i = x + 1; i < heights.length; i++) {
            if (heights[i] == heights[x])
                continue;
            if (heights[i] > heights[x])
                break;
            pour(heights, i);
            return;
        }
        heights[x]++;
    }

    public void print(int[] heights, int[] water) {
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            max = Math.max(max, heights[i] + water[i]);
        }
        for (int i = max; i > 0; i--) {
            for (int j = 0; j < heights.length; j++) {
                if (heights[j] + water[j] < i) {
                    System.out.print(" ");
                } else if (heights[j] + water[j] >= i && heights[j] < i){
                    System.out.print("w");
                } else {
                    System.out.print("#");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
