package com.home.learn.bytedance;

public class SlidingWindowMedian {
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

    public void add(Node root, int x) {
        if (x == root.val) {
            ++root.count;
        } else if (x < root.val) {
            root.leftCount++;
            if (root.left == null) root.left = new Node(x);
            add(root.left, x);
        } else {
            if (root.right == null) root.right = new Node(x);
            add(root.right, x);
        }
    }

    public void remove(Node root, int x) {
        if (x == root.val) {
            --root.count;
        } else if (x < root.val) {
            --root.leftCount;
            remove(root.left, x);
        } else {
            remove(root.right, x);
        }
    }

    public int getValue(Node n, int i) {
        return i < n.leftCount ? getValue(n.left, i)
                : i >= n.leftCount + n.count ? getValue(n.right, i - n.leftCount-n.count)
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
            if (i >= k - 1) {
                res[i - k + 1] = ((double)getValue(root, k / 2) + getValue(root, (k - 1) / 2)) / 2;
            }
        }
        return res;
    }
}
