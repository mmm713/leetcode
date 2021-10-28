package com.home.learn.doordash;

public class FindNearestPointThatHasSameXorYCoordinate {
    public int nearestValidPoint(int x, int y, int[][] points) {
        int res = -1, dist = Integer.MAX_VALUE;
        for(int i = 0; i < points.length; i++) {
            if(points[i][0] == x || points[i][1] == y) {
                int temp = Math.abs(x - points[i][0]) + Math.abs(y - points[i][1]);
                if(temp == 0) return i;
                if(temp < dist) {
                    dist = temp;
                    res = i;
                }
            }
        }
        return res;
    }
}
