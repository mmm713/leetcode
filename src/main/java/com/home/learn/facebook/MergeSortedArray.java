package com.home.learn.facebook;

public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = n + m - 1 ;
        int p1 = m - 1 , p2 = n-1;
        while(index >= 0) {
            if(p1 < 0) nums1[index--] = nums2[p2--];
            else if(p2 < 0) nums1[index--] = nums1[p1--];
            else nums1[index--] = (nums1[p1] > nums2[p2]) ? nums1[p1--] : nums2[p2--] ;
        }
    }
}
