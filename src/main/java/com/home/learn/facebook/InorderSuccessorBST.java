package com.home.learn.facebook;

import com.home.learn.library.TreeNode;

public class InorderSuccessorBST {
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }


    public Node inorderSuccessor(Node node) {
        if (node == null) return null;
        Node res;
        if (node.right != null) {
            res = node.right;
            while(res.left != null) res = res.left;
            return res;
        } else {
            res = node.parent;
            while(res != null && res.val < node.val) res = res.parent;
        }
        return res;
    }

    public Node inorderSuccessorV2(Node node) {
        if (node == null) return null;
        Node res;
        if (node.right != null) {
            res = node.right;
            while(res.left != null) res = res.left;
            return res;
        }
        while(node.parent != null) {
            if(node == node.parent.left) return node.parent;
            node = node.parent;
        }
        return null;
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        while (root != null) {
            if (p.val >= root.val) {
                root = root.right;
            } else {
                successor = root;
                root = root.left;
            }
        }
        return successor;
    }
}
