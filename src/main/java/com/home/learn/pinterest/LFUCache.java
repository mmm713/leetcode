package com.home.learn.pinterest;

import java.util.*;

public class LFUCache {
    static class Node {
        int key, val, cnt;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.cnt = 1;
        }
    }

    int capacity, size, min;
    Map<Integer, Node> nodeMap;
    Map<Integer, Set<Node>> countMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        nodeMap = new HashMap<>();
        countMap = new HashMap<>();
    }

    public int get(int key) {
        Node node = nodeMap.get(key);
        if (node == null) return -1;
        update(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;
        if (nodeMap.containsKey(key)) {
            Node node = nodeMap.get(key);
            node.val = value;
            update(node);
        } else {
            Node node = new Node(key, value);
            nodeMap.put(key, node);
            if (size == capacity) {
                Set<Node> set = countMap.get(min);
                Iterator<Node> it = set.iterator();
                nodeMap.remove(it.next().key);
                it.remove();
            } else {
                size++;
            }
            min = 1;
            Set<Node> newList = countMap.getOrDefault(node.cnt, new HashSet<>());
            newList.add(node);
            countMap.put(node.cnt, newList);
        }
    }

    private void update(Node node) {
        Set<Node> set = countMap.get(node.cnt);
        set.remove(node);
        if (node.cnt == min && set.size() == 0) min++;
        node.cnt++;
        Set<Node> newSet = countMap.getOrDefault(node.cnt, new HashSet<>());
        newSet.add(node);
        countMap.put(node.cnt, newSet);
    }
}
