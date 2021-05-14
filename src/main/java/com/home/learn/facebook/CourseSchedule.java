package com.home.learn.facebook;

import java.util.*;

public class CourseSchedule {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 0; i < numCourses; i++) graph.put(i, new ArrayList<>());
        int[] inDegree = new int[numCourses];
        Queue<Integer> q = new ArrayDeque<>();
        int[] result = new int[numCourses];
        int start = 0;
        for(int[] edge : prerequisites) {
            graph.get(edge[1]).add(edge[0]);
            inDegree[edge[0]]++;
        }
        for(int i = 0; i < numCourses; i++) {
            if(inDegree[i] == 0) {
                q.offer(i);
            }
        }
        while(!q.isEmpty()) {
            int temp = q.poll();
            for(int u : graph.get(temp)) {
                if(--inDegree[u] == 0) {
                    q.offer(u);
                }
            }
            result[start++] = temp;
        }
        return (start == numCourses) ? result : new int[0];
    }


    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 0; i < numCourses; i++) graph.put(i, new ArrayList<>());
        for(int[] edge : prerequisites) graph.get(edge[1]).add(edge[0]);
        int[] status = new int[numCourses];
        for(int i = 0; i < numCourses; i++)
            if(!findCircle(i, status, graph))
                return false;

        return true;
    }

    boolean findCircle(int v, int[] status, Map<Integer, List<Integer>> graph) {
        if(status[v] == 2) return true;

        for(int u : graph.get(v)) {
            if(status[u] == 0) {
                status[u] = 1;
                if(!findCircle(u, status, graph)) {
                    return false;
                }
            } else if(status[u] == 1) {
                return false;
            }
        }

        status[v] = 2;
        return true;
    }

    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        int time = 0;
        for (int[] c: courses) {
            if (time + c[0] <= c[1]) {
                queue.offer(c[0]);
                time += c[0];
            } else if (!queue.isEmpty() && queue.peek() > c[0]) {
                time += c[0] - queue.poll();
                queue.offer(c[0]);
            }
        }
        return queue.size();
    }
}
