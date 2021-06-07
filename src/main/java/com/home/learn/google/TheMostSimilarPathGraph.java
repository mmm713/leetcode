package com.home.learn.google;

import java.util.*;

public class TheMostSimilarPathGraph {
    static class CityPathIndex {
        int city;
        int pathIndex;
        CityPathIndex(int city, int pathIndex) {
            this.city = city;
            this.pathIndex = pathIndex;
        }
    }

    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        int p = targetPath.length;
        // min distance for each city index at path index
        int[][] dist = new int[n][p];
        // prev city for city index at path index
        int[][] path = new int[n][p];

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }
        // sort by distance to city at specific path index
        PriorityQueue<CityPathIndex> pq = new PriorityQueue<>(Comparator.comparingInt(a -> dist[a.city][a.pathIndex]));
        for (int city = 0; city < n; city++) {
            Arrays.fill(dist[city], Integer.MAX_VALUE);
            dist[city][0] = targetPath[0].equals(names[city]) ? 0 : 1;
            pq.add(new CityPathIndex(city, 0));
        }
        while (!pq.isEmpty()) {
            CityPathIndex curr = pq.remove();
            int city = curr.city;
            int pathIndex = curr.pathIndex;
            int currDist = dist[city][pathIndex];
            if (pathIndex == p - 1)
                break;
            for (int adjCity : graph.get(city)) {
                int adjPathIndex = pathIndex + 1;
                int adjDist = targetPath[adjPathIndex].equals(names[adjCity]) ? currDist : currDist + 1;
                if (adjDist < dist[adjCity][adjPathIndex]) {
                    dist[adjCity][adjPathIndex] = adjDist;
                    path[adjCity][adjPathIndex] = city;
                    pq.add(new CityPathIndex(adjCity,adjPathIndex));
                }
            }
        }
        int cityOnPath = 0;
        int minDist = Integer.MAX_VALUE;
        for (int city = 0; city < n; city++) {
            if (dist[city][p-1] < minDist) {
                minDist = dist[city][p-1];
                cityOnPath = city;
            }
        }
        LinkedList<Integer> result = new LinkedList<>();
        for (int pathIndex = p - 1; pathIndex >= 0; pathIndex--) {
            result.addFirst(cityOnPath);
            cityOnPath = path[cityOnPath][pathIndex];
        }
        return result;
    }
}
