package com.home.learn.facebook;

import com.home.learn.library.TreeNode;

public class LargestBSTSubtree {
    public int largestBSTSubtree(TreeNode root) {
        int[] res = new int[1];
        isBST(root, res);
        return res[0];
    }

    //0 means is bst from null
    //1 means is bst with minmax
    //2 means not bst
    private int[] isBST(TreeNode root, int[] res) {
        if(root == null) {
            return new int[]{0, 0, 0, 0};
        }
        int min = Integer.MIN_VALUE, max = Integer.MIN_VALUE;
        int[] left = isBST(root.left, res);
        int[] right = isBST(root.right, res);
        if(left[0] == 0 && right[0] == 0) {
            res[0] = Math.max(res[0], 1);
            return new int[]{1, root.val, root.val, 1};
        } else if(left[0] == 1 && right[0] == 0){
            if(root.val > left[2]) {
                res[0] = Math.max(res[0], left[3] + 1);
                return new int[]{1, left[1], root.val, left[3] + 1};
            }
        } else if(left[0] == 0 && right[0] == 1){
            if(root.val < right[1]) {
                res[0] = Math.max(res[0], right[3] + 1);
                return new int[]{1, root.val, right[2], right[3] + 1};
            }
        } else if(left[0] == 1 && right[0] == 1){
            if(root.val > left[2] && root.val < right[1]) {
                res[0] = Math.max(res[0], left[3] + right[3] + 1);
                return new int[]{1, left[1], right[2], left[3] + right[3] + 1};
            }
        }
        return new int[]{2, min, max, 0};
    }
}
