package com.home.learn.amazon;

import java.util.ArrayList;
import java.util.List;

public class DiameterNAryTree {
    static class Node {
        public int val;
        public List<Node> children;


        public Node() {
            children = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<>();
        }

        public Node(int _val,ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public int diameter(Node root) {
        int[] res = new int[1];
        dfs(root, res);
        return res[0];
    }

    private int dfs(Node root, int[] res) {
        int l1 = 0, l2 = 0;
        for(Node n : root.children) {
            int temp = dfs(n, res) + 1;
            if(l1 == 0) {
                l1 = temp;
            } else {
                if(temp >= l1) {
                    l2 = l1;
                    l1 = temp;
                } else if(temp > l2) {
                    l2 = temp;
                }
            }
        }
        res[0] = Math.max(res[0], l1 + l2);
        return l1;
    }
}
