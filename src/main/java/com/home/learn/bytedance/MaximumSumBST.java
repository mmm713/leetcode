package com.home.learn.bytedance;

import com.home.learn.library.TreeNode;

public class MaximumSumBST {

    static class Data {
        int sum;
        boolean isBST;
        Integer min;
        Integer max;

        public Data(int sum, boolean isBST, Integer min, Integer max) {
            this.sum = sum;
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }

        @Override
        public String toString() {
            return "Data{" +
                "sum=" + sum +
                ", isBST=" + isBST +
                ", min=" + min +
                ", max=" + max +
                '}';
        }
    }
    public int maxSumBST(TreeNode root) {
        return helper(root).sum;
    }

    private Data helper(TreeNode root) {
        if(root == null) {
            return new Data(0, true, null, null);
        }
        Data l = helper(root.left);
        Data r = helper(root.right);
        if((l.isBST && r.isBST) && (l.max == null || root.val > l.max) && (r.min == null || root.val < r.min)) {
            int sum = l.sum + r.sum + root.val;
            if(sum > 0) {
                return new Data(sum, true, l.min == null ? root.val : l.min, r.max == null ? root.val : r.max );
            } else {
                return new Data(0, false, null, null );
            }
        } else {
            return new Data(Math.max(l.sum, r.sum), false, null, null);
        }
    }
}
