package com.home.learn.leetcode;

import java.util.*;

public class BestDeliverSpot {
    //从起点向4方遍历，记录VISITED，
    //已知所有c 位置，DFS停止条件为无法行走或所有C已经抵达
    //Map<Pair<D, C>, Dist>
    private static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int[] findBestLocation(char[][] arr) {
        List<int[]> allC = new ArrayList<>();
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[0].length; j++) {
                if(arr[i][j] == 'c') {
                    int[] tc = {i, j, 0};
                    allC.add(tc);
                }
            }
        }
        Map<int[], Map<int[], Integer>> dist = new HashMap<>();
        for(int[] c : allC) {
            //inside q = x, y, d
            Queue<int[]> q = new ArrayDeque<>();
            q.offer(c);
            while(!q.isEmpty()) {
                int[] curr = q.poll();
                for(int[] dir : dirs) {
                    int x = curr[0] + dir[0];
                    int y = curr[1] + dir[1];
                    if(x >= 0 && x < arr.length && y >= 0 && y < arr[0].length && arr[x][y] == 'd') {
                        //判断空key且创建map
                        int[] key = {x, y};
                        if(!dist.containsKey(key)) {
                            dist.put(key, new HashMap<>());
                        }
                        int[] xy = {x, y, curr[2] + 1};
                        dist.get(key).put(c, Math.min(dist.get(key).getOrDefault(c, 0), curr[2] + 1));
                        q.offer(xy);
                    }
                }
            }
        }
        //根据D的距离求最小点
        int[] res = {-1, -1};
        int totalD = Integer.MAX_VALUE;
        for(Map.Entry<int[], Map<int[], Integer>> e : dist.entrySet()) {
            if(e.getValue().size() < allC.size()) continue;
            int temp = e.getValue().values().stream().mapToInt(Integer::valueOf).sum();
            if(temp < totalD) {
                totalD = temp;
                res = e.getKey();
            }
        }
        return res;
    }

}
