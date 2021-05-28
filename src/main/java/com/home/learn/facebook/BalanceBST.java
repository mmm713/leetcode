package com.home.learn.facebook;

import com.home.learn.library.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BalanceBST {
    //过于不平衡的树极难自旋，只能遍历然后重新创建树，还不如创建LIST出结果来得快
    public TreeNode balanceBST(TreeNode root) {
        List<TreeNode> lis = new ArrayList<>();
        populateList(root, lis);
        return constructTree(lis, 0, lis.size() - 1);
    }

    public TreeNode constructTree(List<TreeNode> lis, int start, int end) {
        if (start > end)
            return null;
        int mid = start + (end - start)/2;
        TreeNode root = lis.get(mid);
        root.left = constructTree(lis, start, mid - 1);
        root.right = constructTree(lis, mid + 1, end);
        return root;
    }

    public void populateList(TreeNode root, List<TreeNode> lis) {
        if (root == null)
            return;
        populateList(root.left, lis);
        lis.add(root);
        populateList(root.right, lis);
    }

    //检查是否平衡，看最有孩子高度差即可
    public boolean isBalanced(TreeNode root) {
        return checkDepth(root) != -1;
    }

    private int checkDepth(TreeNode root) {
        if (root == null) return 0;
        int left = checkDepth(root.left);
        if (left == -1) return -1;
        int right = checkDepth(root.right);
        if (right == -1) return -1;
        if (Math.abs(left - right) > 1) return -1;
        else return 1 + Math.max(left, right);
    }
}
