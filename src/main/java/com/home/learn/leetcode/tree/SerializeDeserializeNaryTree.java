package com.home.learn.leetcode.tree;

import java.util.*;

public class SerializeDeserializeNaryTree {
    static class Node {
        public int val;
        public List<Node> children;
        public Node(int val) {
            this.val = val;
        }
    }

    String NN="X";
    String spliter=",";
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }
    private void buildString(Node node, StringBuilder sb){
        if(node==null){
            sb.append(NN);
            sb.append(spliter);
        }else{
            sb.append(node.val);
            sb.append(spliter);
            sb.append(node.children.size());
            sb.append(spliter);
            for (Node child:node.children){
                buildString(child,sb);
            }
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        Deque<String> deque=new ArrayDeque<>(Arrays.asList(data.split(spliter)));
        return buildTree(deque);
    }
    private Node buildTree(Deque<String> deque){
        String s1=deque.removeFirst();
        if(s1.equals(NN)) return null;

        int rootVal=Integer.parseInt(s1);
        int childrenNumber=Integer.parseInt(deque.removeFirst());

        Node root=new Node(rootVal);
        root.children=new ArrayList<>();
        for (int i=0;i<childrenNumber;i++){
            root.children.add(buildTree(deque));
        }
        return root;
    }
}
