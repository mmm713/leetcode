package com.home.learn.google;

import java.util.ArrayList;
import java.util.List;

public class RectangleArea {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int areaA = (C-A) * (D-B);
        int areaB = (G-E) * (H-F);
        int leftMax = Math.max(A,E);
        int rightMin = Math.min(C,G);
        int bottomMax = Math.max(B,F);
        int topMin = Math.min(D,H);
        int overlay = 0;
        if(rightMin > leftMax && topMin >bottomMax){
            overlay = (rightMin-leftMax) * (topMin-bottomMax);
        }
        return areaA + areaB - overlay;
    }

    //第二问，长方形之间有重合
    public int rectangleArea(int[][] rectangles) {
        List<int[]> nonOverlap = new ArrayList<>();
        for(int[] rectangle : rectangles) {
            breakRec(nonOverlap, rectangle, 0);
        }
        int mod = (int) Math.pow(10, 9) + 7;
        long area = 0;
        for(int[] rec : nonOverlap) {
            area = ( area + (long) (rec[2] - rec[0]) * (rec[3] - rec[1]) ) % mod;
        }
        return (int) area;
    }

    private void breakRec(List<int[]> nonOverlap, int[] curr, int start) {
        if(nonOverlap.size() <= start) {
            nonOverlap.add(curr);
            return;
        }
        int[] reference = nonOverlap.get(start);
        if(reference[0] >= curr[2] || reference[2] <= curr[0] || reference[1] >= curr[3] || curr[1] >= reference[3]) {
            // no overlap
            breakRec(nonOverlap, curr, start + 1);
            return;
        }
        if(curr[0] < reference[0]){
            breakRec(nonOverlap, new int[]{curr[0], curr[1], reference[0], curr[3]}, start + 1);
        }
        if(reference[2] < curr[2]) {
            breakRec(nonOverlap, new int[]{reference[2], curr[1], curr[2], curr[3]}, start + 1);
        }
        if(curr[1] < reference[1]) {
            breakRec(nonOverlap, new int[]{Math.max(reference[0], curr[0]), curr[1], Math.min(curr[2], reference[2]), reference[1]}, start + 1);
        }
        if(curr[3] > reference[3]) {
            breakRec(nonOverlap, new int[]{Math.max(curr[0], reference[0]), reference[3], Math.min(reference[2], curr[2]),curr[3]}, start + 1);
        }
    }
}
