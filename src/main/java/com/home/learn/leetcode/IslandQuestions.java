package com.home.learn.leetcode;

import java.util.*;

public class IslandQuestions {


    private static final int[][] dirs = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    //一共几个岛屿
    public int numIslands(char[][] grid) {
        int result = 0;
        if(grid.length < 1) return result;
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if ((!visited[i][j]) && (grid[i][j] == '1')) {
                    result++;
                    dfsMark(grid, visited, i, j);
                }
            }
        }
        return result;
    }
    public void dfsMark(char[][] grid, boolean[][] visited, int i, int j) {
        visited[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int x = i + dirs[k][0];
            int y = j + dirs[k][1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == '1' && visited[x][y] == false) {
                dfsMark(grid, visited, x, y);
            }
        }
    }

    //每次填海，有几个岛屿
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>(positions.length);
        int[] rankedRoots = new int[m * n];
        Arrays.fill(rankedRoots, Integer.MIN_VALUE);
        int count = 0;
        for (int[] position : positions) {
            int id = position[0] * n + position[1];
            if (rankedRoots[id] == Integer.MIN_VALUE) {
                rankedRoots[id] = -1;
                count++;
                for(int[] dir : dirs) {
                    int x = position[0] + dir[0];
                    int y = position[1] + dir[1];
                    int nextId = n * x + y;
                    if (x < 0 || x >= m || y < 0 || y >= n) continue;
                    if(union(rankedRoots, nextId, id)) {
                        count--;
                    }
                }
            }
            result.add(count);
        }
        return result;
    }

    private boolean union(int[] rankedRoots, int first, int second) {
        if (rankedRoots[first] == Integer.MIN_VALUE || rankedRoots[second] == Integer.MIN_VALUE) {
            return false;
        }
        int firstRoot = find(rankedRoots, first);
        int secondRoot = find(rankedRoots, second);
        if (firstRoot != secondRoot) {
            if (rankedRoots[firstRoot] < rankedRoots[secondRoot]) {
                rankedRoots[firstRoot] += rankedRoots[secondRoot];
                rankedRoots[secondRoot] = firstRoot;
            } else {
                rankedRoots[secondRoot] += rankedRoots[firstRoot];
                rankedRoots[firstRoot] = secondRoot;
            }
            return true;
        }
        return false;
    }

    private int find(int[] rankedRoots, int target) {
        // rankedRoots[target] != Integer.MIN_VALUE
        while (rankedRoots[target] >= 0) {
            rankedRoots[target] = find(rankedRoots, rankedRoots[target]);
            target = rankedRoots[target];
        }
        return target;
    }

    //形状一样的个数，通过记录dfs路径区别
    public int numDistinctIslands(int[][] grid) {
        Set<String> set = new HashSet<>();
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, sb, 'x');
                    set.add(sb.toString());
                }
            }
        }

        return set.size();
    }
    private void dfs(int[][] grid, int x, int y, StringBuilder sb, char c) {

        sb.append(c);
        grid[x][y] = 0;

        for (int i = 0; i < 4; ++i) {
            int nx = x + dirs[i][0];
            int ny = y + dirs[i][1];
            if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && grid[nx][ny] == 1) {
                dfs(grid, nx, ny, sb, (char) ('a' + i));
            }
        }
        sb.append('z');
    }

    int[][] grid;
    boolean[][] seen;
    List<Integer> shape;

    public void explore(int r, int c) {
        if (0 <= r && r < grid.length
                && 0 <= c && c < grid[0].length
                && grid[r][c] == 1 && !seen[r][c]) {
            seen[r][c] = true;
            shape.add(r * grid[0].length + c);
            explore(r + 1, c);
            explore(r - 1, c);
            explore(r, c + 1);
            explore(r, c - 1);
        }
    }

    public String canonical(List<Integer> shape) {
        String ans = "";
        int lift = grid.length + grid[0].length;
        int[] out = new int[shape.size()];
        int[] xs = new int[shape.size()];
        int[] ys = new int[shape.size()];

        for (int c = 0; c < 8; ++c) {
            int t = 0;
            for (int z: shape) {
                int x = z / grid[0].length;
                int y = z % grid[0].length;
                //x y, x -y, -x y, -x -y
                //y x, y -x, -y x, -y -x
                xs[t] = c <= 1 ? x : c <= 3 ? -x : c <= 5 ? y : -y;
                ys[t++] = c <= 3 ? (c % 2 == 0 ? y : -y) : (c % 2 == 0 ? x : -x);
            }

            int mx = xs[0], my = ys[0];
            for (int x: xs) mx = Math.min(mx, x);
            for (int y: ys) my = Math.min(my, y);

            for (int j = 0; j < shape.size(); ++j) {
                out[j] = (xs[j] - mx) * lift + (ys[j] - my);
            }
            Arrays.sort(out);
            String candidate = Arrays.toString(out);
            if (ans.compareTo(candidate) < 0) ans = candidate;
        }
        return ans;
    }

    public int numDistinctIslands2(int[][] grid) {
        this.grid = grid;
        seen = new boolean[grid.length][grid[0].length];
        Set<String> shapes = new HashSet<>();

        for (int r = 0; r < grid.length; ++r) {
            for (int c = 0; c < grid[0].length; ++c) {
                shape = new ArrayList<>();
                explore(r, c);
                if (!shape.isEmpty()) {
                    shapes.add(canonical(shape));
                }
            }
        }

        return shapes.size();
    }
}
