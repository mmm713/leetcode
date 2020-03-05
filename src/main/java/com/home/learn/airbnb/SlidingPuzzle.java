package com.home.learn.airbnb;

import java.util.*;

public class SlidingPuzzle {
    public int slidingPuzzle(int[][] board) {
        String target = "123450";
        StringBuilder sb = new StringBuilder();
        for (int[] ints : board) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append(ints[j]);
            }
        }
        int[][] adjacentPositions = new int[][] {
                {1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}
        };
        int moves = 0;
        String current = sb.toString();
        Queue<String> bfs = new ArrayDeque<>();
        bfs.offer(current);
        Set<String> visited = new HashSet<>();
        while (!bfs.isEmpty()) {
            int size = bfs.size();
            for (int i = 0; i < size; i++) {
                current = bfs.poll();
                if (current.equals(target)) {
                    return moves;
                }
                if (!visited.contains(current)) {
                    visited.add(current);
                    int zeroPos = current.indexOf('0');
                    for (int adjacentPos : adjacentPositions[zeroPos]) {
                        sb.replace(0, sb.length(), current);
                        sb.setCharAt(zeroPos, sb.charAt(adjacentPos));
                        sb.setCharAt(adjacentPos, '0');
                        bfs.offer(sb.toString());
                    }
                }
            }
            moves++;
        }
        return -1;
    }
}
