package com.home.learn.leetcode;

import java.util.HashSet;
import java.util.Set;

public class EscapeLargeMaze {
    private int[][] dirs = {{0,-1},{0,1},{1,0},{-1,0}};
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        if(blocked == null || source == null || target == null || blocked.length == 0 || blocked[0].length == 0
                || source.length == 0 || target.length == 0){
            return true;
        }
        Set<String> block = new HashSet<>();
        for(int[] pos : blocked){
            block.add(pos[0] + "->" + pos[1]);
        }

        return dfs(source, target, source, block, new HashSet<>())
                && dfs(target, source, target, block, new HashSet<>());
    }

    private boolean dfs(int[] source, int[] target, int[] cur, Set<String> block, Set<String> visited){
        if(cur[0] == target[0] && cur[1] == target[1]) return true;
        if(Math.abs(source[0] - cur[0]) + Math.abs(source[1] - cur[1]) > 200) return true;

        visited.add(cur[0] + "->" + cur[1]);
        for(int[] dir: dirs){
            int x = cur[0] + dir[0];
            int y = cur[1] + dir[1];
            String str = x + "->" + y;

            if(x >= 0 && x < 1000000 && y >= 0 && y < 1000000 && !visited.contains(str) && !block.contains(str)){
                if(dfs(source, target, new int[]{x,y}, block, visited)){
                    return true;
                }
            }
        }
        return false;
    }
}
