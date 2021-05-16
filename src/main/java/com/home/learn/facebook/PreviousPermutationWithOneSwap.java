package com.home.learn.facebook;

public class PreviousPermutationWithOneSwap {
    public int[] prevPermOpt1(int[] arr) {
        int n = arr.length;
        int i = n-1;
        while(i > 0 && arr[i-1] <= arr[i]){
            i--;
        }
        // if we have reached the first element then there is no
        // smaller number possible.
        if(i == 0) {
            return arr;
        }
        // Otherwise we start looking for element just smaller then i-1 element.
        int s = arr[i-1];
        int t = i;
        for(int j = i; j < n; j++) {
            if(s > arr[j] && arr[t] < arr[j]){
                t = j;
            }
        }
        swap(arr, i-1, t);
        return arr;
    }

    void swap(int[] arr, int s, int t)
    {
        int temp = arr[t];
        arr[t] = arr[s];
        arr[s] = temp;
    }
}
