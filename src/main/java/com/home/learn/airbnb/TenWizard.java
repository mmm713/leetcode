package com.home.learn.airbnb;

import java.util.*;

public class TenWizard {
    public static void main(String[] args) {
    TenWizard sol = new TenWizard();
    int[][] wizards = new int[][] {
            {5},
            {3},
            {4, 7},
            {4},
            {8},
            {2},
            {8, 9},
            {9},
            {9},
            {1, 2, 3}
    };
    List<List<Integer>> wizardList = new ArrayList<>();
    for (int i = 0; i < wizards.length; i++) {
        wizardList.add(new ArrayList<>());
        for (int j = 0; j < wizards[i].length; j++) {
            wizardList.get(i).add(wizards[i][j]);
        }
    }
    System.out.println(sol.findMinCost(wizardList, 0, 9).path);
    System.out.println(sol.findMinCost(wizardList, 0, 9).cost);
}

    public Result findMinCost(List<List<Integer>> wizards, int from, int to) {
        int[] minCost = new int[wizards.size()];  // the min cost for each wizard so far
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[from] = 0;
        Queue<Wizard> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        Set<Integer> closed = new HashSet<>();
        pq.offer(new Wizard(from, 0, null));
        while (!pq.isEmpty()) {
            Wizard curr = pq.poll();
            if (closed.contains(curr.id)) continue;
            closed.add(curr.id);
            if (curr.id == to) {
                return new Result(curr.cost, getPath(curr));
            }
            for (int next : wizards.get(curr.id)) {
                int cost = curr.cost + (next - curr.id) * (next - curr.id);
                if (cost >= minCost[next]) continue;
                minCost[next] = cost;
                pq.offer(new Wizard(next, cost, curr));
            }
        }
        return null;
    }

    private List<Integer> getPath(Wizard end) {
        List<Integer> list = new ArrayList<>();
        while (end != null) {
            list.add(end.id);
            end = end.from;
        }
        Collections.reverse(list);
        return list;
    }

    static class Result {
        int cost;
        List<Integer> path;

        public Result(int cost, List<Integer> path) {
            this.cost = cost;
            this.path = path;
        }
    }
    static class Wizard {
        int id;
        int cost;
        Wizard from;

        public Wizard(int id, int cost, Wizard from) {
            this.id = id;
            this.cost = cost;
            this.from = from;
        }
    }
}

