package com.home.learn.wish;

public class SwapRedLights {
    public int[] maxGreenLights(String s) {
        int[] result = new int[2];
        int count = 0, maxSum = 0, minSum = 0;
        int left = -1;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'R') count++;
            else count--;
            if(count <= minSum) {
                left = i;
                minSum = count;
            }
            if(count - minSum > maxSum) {
                result[0] = left + 1;
                result[1] = i ;
                maxSum = count - minSum;
            }
        }
        return result;
    }
}
