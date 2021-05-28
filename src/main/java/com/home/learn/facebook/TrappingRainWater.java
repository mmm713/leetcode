package com.home.learn.facebook;

import java.util.PriorityQueue;

public class TrappingRainWater {
    public int trap(int[] height) {
        if(height.length == 0) return 0;
        int ans = 0;
        int left = 0, right = height.length - 1;
        int left_max = Integer.MIN_VALUE, right_max = Integer.MIN_VALUE;
        while(left < right){
            if(height[left] < height[right]){
                if(height[left] >= left_max){
                    left_max = height[left];
                } else {
                    ans += left_max - height[left];
                }
                left++;
            } else {
                if(height[right] >= right_max){
                    right_max = height[right];
                } else {
                    ans += right_max - height[right];
                }
                right--;
            }
        }
        return ans;
    }




    private final int[] directions = new int[]{-1, 0, 1, 0, -1};
    public int trapRainWater(int[][] heightMap) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        int m = heightMap.length;
        int n = heightMap[0].length;

        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            visited[i][0] = true;
            pq.add(new int[]{i, 0, heightMap[i][0]});
            visited[i][n - 1] = true;
            pq.add(new int[]{i, n - 1, heightMap[i][n - 1]});
        }

        for (int j = 1; j < n - 1; j++) {
            visited[0][j] = true;
            pq.add(new int[]{0, j, heightMap[0][j]});
            visited[m - 1][j] = true;
            pq.add(new int[]{m - 1, j, heightMap[m - 1][j]});
        }

        int water = 0;
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            water += dfs(pq, heightMap, node[0], node[1], node[2], visited);
        }

        return water;
    }

    private int dfs(PriorityQueue<int[]> pq, int[][] heightMap, int i, int j, int height, boolean[][] visited) {
        int water = 0;
        if (height >= heightMap[i][j]) {
            visited[i][j] = true;
            water += height -  heightMap[i][j];
            for (int d = 0; d < directions.length - 1; d++) {
                int nexti = i + directions[d];
                int nextj = j + directions[d + 1];
                if (nexti >= 0 && nexti < heightMap.length && nextj >= 0 && nextj < heightMap[0].length && !visited[nexti][nextj]) {
                    water += dfs(pq, heightMap, nexti, nextj, height, visited);
                }
            }
        } else {
            if (!visited[i][j]) {
                visited[i][j] = true;
                pq.add(new int[]{i, j, heightMap[i][j]});
            }
        }

        return water;
    }
}
