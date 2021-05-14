package com.home.learn.google;

import java.util.ArrayList;
import java.util.List;

public class SlidingWindow {

    static class Node {
        int val;
        int count;
        int leftCount;
        Node left;
        Node right;
        public Node(int v) {
            val = v;
        }
    }

    public void add(Node n, int x) {
        if (x == n.val) {
            ++n.count;
        } else if (x < n.val) {
            n.leftCount++;
            if (n.left == null) n.left = new Node(x);
            add(n.left, x);
        } else {
            if (n.right == null) n.right = new Node(x);
            add(n.right, x);
        }
    }

    public void remove(Node n, int x) {
        if (x == n.val) {
            --n.count;
        } else if (x < n.val) {
            --n.leftCount;
            remove(n.left, x);
        } else {
            remove(n.right, x);
        }
    }

    public int getValue(Node n, int i) {
        return i < n.leftCount ? getValue(n.left, i)
                : i >= n.leftCount + n.count ? getValue(n.right, i-n.leftCount-n.count)
                : n.val;
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - k + 1];
        Node root = new Node(5);
        for (int i = 0; i < nums.length; ++i) {
            if (i >= k) {
                remove(root, nums[i-k]);
            }
            add(root, nums[i]);
            if (i >= k-1) {
                res[i - k + 1] = ((double)getValue(root, k/2) + getValue(root, (k-1)/2)) / 2;
            }
        }
        return res;
    }

    public double[] medianSlidingWindowV2(int[] nums, int k) {

        List<Integer> window = new ArrayList<>();
        double []res = new double[nums.length-k+1];
        for(int i=0; i<nums.length; i++){
            if(i>=k) res[i-k] = (k%2==0?((double)window.get(k/2)+(double)window.get(k/2-1))/2:(double)window.get(k/2));
            window.add(find(window, nums[i]), nums[i]);
            if(i>=k) window.remove(find(window, nums[i-k]));
        }
        res[res.length-1] = (k%2==0?((double)window.get(k/2)+(double)window.get(k/2-1))/2:(double)window.get(k/2));
        return res;
    }

    public int find(List<Integer> list, int target){
        int start =0, end = list.size()-1;
        while(start<=end){
            int mid = start + (end-start)/2;
            if(list.get(mid)<target){
                start = mid +1;
            }else if (list.get(mid)>target){
                end = mid -1;
            }else{
                return mid;
            }
        }
        return start;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }
        int max = nums[0];
        for (int i = 1; i < k; i++) {
            max = Math.max(max, nums[i]);
        }
        int len = nums.length - k + 1;
        int[] maxArr = new int[len];
        maxArr[0] = max;
        for (int i = 1; i < len; i++) {
            if (max < nums[i + k -1]) {
                max = nums[i + k -1];
            } else if(max == nums[i - 1]) {
                max = nums[i];
                for (int j = i + 1; j < i + k; j++) {
                    max = Math.max(max, nums[j]);
                }
            }
            maxArr[i] = max;
        }
        return maxArr;
    }
}
