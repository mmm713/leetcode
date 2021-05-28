package com.home.learn.facebook;

import java.util.LinkedList;
import java.util.Queue;

public class PopulatingNextRightPointersEachNode {
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
    public Node connect(Node root) {
        if(root == null) return null;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        q.offer(null);
        while(!q.isEmpty()) {
            Node node = q.poll();
            if(node == null) {
                if(!q.isEmpty()) {
                    q.offer(null);
                }
            } else {
                node.right = q.peek();
                if(node.left != null) {
                    q.offer(node.left);
                }
                if(node.right != null) {
                    q.offer(node.right);
                }
            }
        }
        return root;
    }
}
