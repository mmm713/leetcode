package com.home.learn.airbnb;

import java.util.*;

/*
Assumption:
1. The input is a list of list, such as [[1, 2], [1,4], [3,4]...], represents 1 know 2 and 4, 3 know 4
2. The cost between two node are the square of diff.
3. return the min cost from one number to another number
4. if can not reach another number, we return -1

==========================
 */

public class TenWizard {
    public int findMinCost(int[][] wizards, int from, int to) {
        if(from == to) return 0;
        Map<Integer, Set<Integer>> graph = buildGraph(wizards);
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> bfs = new ArrayDeque<>();
        int cost = 0;
        int count = 1;
        bfs.add(from);
        while(!bfs.isEmpty()) {
            for(int i = 0; i < count; i++) {
                Set<Integer> children = graph.getOrDefault(bfs.poll(), new HashSet<>());
                for(Integer j : children) {
                    if(j == to) {
                        return ++cost;
                    } else if(!visited.contains(j)) {
                        bfs.add(j);
                        visited.add(j);
                    }
                }
            }
            cost++;
            count = bfs.size();
        }
        return -1;
    }

    private Map<Integer, Set<Integer>> buildGraph(int[][] wizards) {
        Map<Integer, Set<Integer>> result = new HashMap<>();
        for(int[] w : wizards) {
            Set<Integer> temp = result.getOrDefault(w[0], new HashSet<>());
            temp.add(w[1]);
            result.put(w[0], temp);
        }
        return result;
    }
}

