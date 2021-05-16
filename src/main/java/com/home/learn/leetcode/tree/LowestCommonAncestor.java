package com.home.learn.leetcode.tree;

import com.home.learn.library.Node;
import com.home.learn.library.TreeNode;

public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null) return right;
        else if(right == null) return left;
        else return root;
    }

    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        int parentVal = root.val;
        int pVal = p.val;
        int qVal = q.val;
        if (pVal > parentVal && qVal > parentVal) {
            return lowestCommonAncestorBST(root.right, p, q);
        } else if (pVal < parentVal && qVal < parentVal) {
            return lowestCommonAncestorBST(root.left, p, q);
        } else {
            return root;
        }
    }

    public Node lowestCommonAncestor(Node p, Node q) {
        Node a = p, b = q;
        while (a != b) {
            a = a == null? q : a.parent;
            b = b == null? p : b.parent;
        }
        return a;
    }

    public TreeNode lowestCommonAncestorContainsNull(TreeNode root, TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return null;
        }
        TreeNode[] res = new TreeNode[1];
        helper(root, p, q, res);
        return res[0];
    }

    private int helper(TreeNode root, TreeNode p, TreeNode q, TreeNode[] res) {
        if (root == null) {
            return 0;
        }
        int mid = root == p || root == q ? 1 : 0;
        int left = helper(root.left, p, q, res);
        int right = helper(root.right, p, q, res);
        if (left != 2 && right != 2 && left + mid + right == 2) {
            res[0] = root;
        }
        return left + mid + right;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        if(root == null) {
            return null;
        }
        for(TreeNode node : nodes) {
            if(node == root) {
                return node;
            }
        }
        TreeNode l = lowestCommonAncestor(root.left, nodes);
        TreeNode r = lowestCommonAncestor(root.right, nodes);
        if(l != null && r != null) {
            return root;
        } else if(l != null) {
            return l;
        } else {
            return r;
        }
    }
}
