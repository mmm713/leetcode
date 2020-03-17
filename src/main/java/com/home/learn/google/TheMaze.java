package com.home.learn.google;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TheMaze {
    //The Maze I
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return dfs(maze, start, destination, visited);
    }
    public boolean dfs(int[][] maze, int[] start, int[] destination, boolean[][] visited) {
        if (visited[start[0]][start[1]])
            return false;
        if (start[0] == destination[0] && start[1] == destination[1])
            return true;
        visited[start[0]][start[1]] = true;
        int r = start[1] + 1, l = start[1] - 1, u = start[0] - 1, d = start[0] + 1;
        while (r < maze[0].length && maze[start[0]][r] == 0) // right
            r++;
        if (dfs(maze, new int[] {start[0], r - 1}, destination, visited))
            return true;
        while (l >= 0 && maze[start[0]][l] == 0) //left
            l--;
        if (dfs(maze, new int[] {start[0], l + 1}, destination, visited))
            return true;
        while (u >= 0 && maze[u][start[1]] == 0) //up
            u--;
        if (dfs(maze, new int[] {u + 1, start[1]}, destination, visited))
            return true;
        while (d < maze.length && maze[d][start[1]] == 0) //down
            d++;
        return dfs(maze, new int[]{d - 1, start[1]}, destination, visited);
    }

    //The Maze II
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        int[][] distance = new int[maze.length][maze[0].length];
        for (int[] row: distance)
            Arrays.fill(row, Integer.MAX_VALUE);
        distance[start[0]][start[1]] = 0;
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int[] s = queue.remove();
            for (int[] dir: dirs) {
                int x = s[0] + dir[0];
                int y = s[1] + dir[1];
                int count = 0;
                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    count++;
                }
                if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                    distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
                    queue.add(new int[] {x - dir[0], y - dir[1]});
                }
            }
        }
        return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }

    //The Maze III
    final int[] dx = new int[]{1,0,0,-1};//down-left,right,up
    final int[] dy = new int[]{0,-1,1,0};
    String res = "";
    int min;

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        //Solution1 DFS
        min = Integer.MAX_VALUE;
        res = null;
        int[][] distances = new int[maze.length][maze[0].length];
        for(int[] distance : distances){
            Arrays.fill(distance,Integer.MAX_VALUE);
        }
        dfsHelper(ball[0],ball[1],0,maze,hole,"",-1,distances);
        return res == null ? "impossible" : res;
    }

    public void dfsHelper(int x, int y, int count, int[][] maze, int[] hole,String path,int dir,int[][] distances){
        if(count > min || count > distances[x][y]){return;}//this path could be the smallest or this point has been visited
        if(dir != -1){
            if(dir == 0) path += "d";
            else if(dir == 1) path += "l";
            else if(dir == 2) path += "r";
            else path += "u";
            //we need to move along this direction until we meet the wall or hole
            while(x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0){
                distances[x][y] = Math.min(distances[x][y], count);
                //If we meet a hole
                if(x == hole[0] && y == hole[1]){
                    if(count == min && path.compareTo(res) < 0){
                        res = path;
                    }else if(count < min){
                        min = count;
                        res = path;
                    }
                    return;//stop the movement
                }
                x += dx[dir];
                y += dy[dir];
                count++;
            }
            count--;
            x -= dx[dir];
            y -= dy[dir];
        }
        //now we have meet the wall
        for(int i = 0;i < 4;i++){
            if(i == dir || 3-i == dir) continue;//the same direction or opposite direction, we just skip it
            int newX = x + dx[i];
            int newY = y + dy[i];
            if(newX >= 0 && newX < maze.length && newY >= 0 && newY < maze[0].length && maze[newX][newY] == 0){
                dfsHelper(x,y,count,maze,hole,path,i,distances);
            }
        }
    }
}
