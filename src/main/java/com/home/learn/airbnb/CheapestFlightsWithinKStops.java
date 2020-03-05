package com.home.learn.airbnb;

import java.util.Arrays;

public class CheapestFlightsWithinKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[] prices = new int[n];
        Arrays.fill(prices, -1);
        prices[src] = 0;
        for(int i=0; i<=K; i++) {
            updatePrice(prices, flights);
        }
        return prices[dst] > 0 ? prices[dst] : -1;
    }

    private void updatePrice(int[] prices, int[][] flights) {
        int[] pre = new int[prices.length];
        System.arraycopy(prices, 0, pre, 0, prices.length);
        for (int[] flight : flights) {
            int start = flight[0];
            if (pre[start] >= 0) {
                int end = flight[1];
                int cost = flight[2];
                int newCost = pre[start] + cost;
                if (newCost < prices[end] || prices[end] == -1) {
                    prices[end] = newCost;
                }
            }
        }
    }
}
