package com.home.learn.leetcode.tree;

import com.home.learn.library.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class IncreasingBST {
    public TreeNode increasingBST(TreeNode root) {
        List<Integer> vals = new ArrayList<>();
        inorder(root, vals);
        TreeNode ans = new TreeNode(0), cur = ans;
        for (int v: vals) {
            cur.right = new TreeNode(v);
            cur = cur.right;
        }
        return ans.right;
    }

    public void inorder(TreeNode node, List<Integer> vals) {
        if (node == null) return;
        inorder(node.left, vals);
        vals.add(node.val);
        inorder(node.right, vals);
    }


    public TreeNode increasingBSTv1(TreeNode root) {
        if (root == null) return null;
        TreeNode l = increasingBST(root.left);
        root.left = null;
        root.right = increasingBST(root.right);
        if (l == null) {
            return root;
        } else {
            TreeNode curr = l;
            //左子树已经是只有右孩子的单向链表，思维类似Morris遍历，反而更快
            while (curr.right != null) {
                curr = curr.right;
            }
            curr.right = root;
            return l;
        }
    }
}
