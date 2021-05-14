package com.home.learn.google;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CloneGraph {
    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        return dfs(node, new HashMap<>());
    }

    private Node dfs(Node node, HashMap<Node, Node> visited) {
        if (node == null) {
            return null;
        }
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        Node cloneNode = new Node(node.val, new ArrayList<>());
        visited.put(node, cloneNode);
        for (Node neighbor: node.neighbors) {
            cloneNode.neighbors.add(dfs(neighbor, visited));
        }
        return cloneNode;
    }
}
