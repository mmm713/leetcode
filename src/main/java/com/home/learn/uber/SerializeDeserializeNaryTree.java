package com.home.learn.uber;

import java.util.*;

public class SerializeDeserializeNaryTree {
    static class Node {
        public int val;
        public List<Node> children;
        public Node(int val) {
            this.val = val;
        }
    }

    private static final String NULL ="X";
    private static final String SPLITTER =",";
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }
    private void buildString(Node node, StringBuilder sb){
        if(node == null){
            sb.append(NULL);
            sb.append(SPLITTER);
        }else{
            sb.append(node.val);
            sb.append(SPLITTER);
            sb.append(node.children.size());
            sb.append(SPLITTER);
            for (Node child : node.children){
                buildString(child, sb);
            }
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(SPLITTER)));
        return buildTree(queue);
    }
    private Node buildTree(Queue<String> queue){
        String s1 = queue.poll();
        if(s1.equals(NULL)) {
            return null;
        } else {
            Node root = new Node(Integer.parseInt(s1));
            int cm = Integer.parseInt(queue.poll());
            root.children = new ArrayList<>(cm);
            for (int i = 0; i < cm; i++){
                root.children.add(buildTree(queue));
            }
            return root;
        }
    }
}
