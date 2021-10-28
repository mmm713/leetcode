package com.home.learn.doordash;

public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length-1;
        int max = 0, area;
        while(left < right) {
            int l = height[left];
            int r = height[right];
            area = (right-left) * Math.min(l, r);
            if(l > r){
                while (height[--right] <= r);
            } else {
                while (height[++left] < l);
            }
            max = Math.max(max, area);
        }
        return max;
    }
}
