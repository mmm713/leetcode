package com.home.learn.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class ShortestPathVisitingAllNodes {
    public int shortestPathLength(int[][] graph) {
        int len=graph.length;
        boolean[][] book=new boolean[len][1<<len];
        int k=(1<<len)-1;
        Queue<int[]> queue=new ArrayDeque<>();
        for (int i=0;i<len;i++) {
            queue.offer(new int[]{i,1<<i});
        }
        int step=0;
        while (!queue.isEmpty()) {
            int size=queue.size();
            while (size-->0) {
                int[] node=queue.poll();
                if(k == node[1])return step;
                for (int next:graph[node[0]]) {
                    int next_state=node[1]|(1<<next);
                    if(book[next][next_state])continue;
                    book[next][next_state]=true;
                    queue.offer(new int[]{next,next_state});
                }
            }
            step++;
        }
        return step;
    }
}
