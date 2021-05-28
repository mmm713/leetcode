package com.home.learn.facebook;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class KClosestPointsOrigin {

    public int[][] kClosestSort(int[][] points, int k) {
        Arrays.sort(points, Comparator.comparingInt(this::getDist));
        int[][] answer = new int[k][2];
        int count = 0;
        for(int i = 0; i < points.length;i++){
            if(count<k){
                answer[i] = points[i];
                count++;
            }
        }
        return answer;
    }

    private int getDist(int[] arr){
        int x = arr[0];
        int y = arr[1];
        return x * x + y * y;
    }

    public int[][] kClosestHeap(int[][] points, int k) {
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(this::getDist).reversed());
        int[][] answer = new int[k][2];
        for(int[] point : points){
            if(q.size() < k) {
                q.offer(point);
            } else if(getDist(point) < getDist(q.peek())) {
                q.poll();
                q.offer(point);
            }
        }
        for (int i = 0; i < k; i++) {
            answer[i] = q.poll();
        }
        return answer;
    }


    public int[][] kClosest(int[][] points, int K) {
        int h = points.length - 1;
        int l = 0;
        while(l <= h){
            int partition = partition(points, l, h);
            if(partition == K - 1)  break;
            else if(partition > K - 1)   h = partition - 1;
            else if(partition < K - 1)  l = partition + 1;
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    public int partition(int[][] points, int l, int h){
        int pivotDis = getDistance(points, h);
        int i = l - 1;
        for(int j = l; j <= h - 1; j++){
            if(getDistance(points, j) <= pivotDis){
                i++;
                swap(points, i, j);
            }
        }
        swap(points, i + 1, h);
        return i + 1;
    }

    public void swap(int[][] points, int a, int b){
        int[] temp = points[a];
        points[a] = points[b];
        points[b] = temp;
    }

    public int getDistance(int[][] points, int index){
        return (points[index][0] * points[index][0] + points[index][1] * points[index][1]);
    }
}
