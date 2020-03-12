package com.home.learn.leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {
    private LinkedHashMap<Integer, Integer> map;
    private final int CAPACITY;
    public LRUCache(int capacity) {
        CAPACITY = capacity;
        map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true){
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > CAPACITY;
            }
        };
    }
    public int get(int key) {
        return map.getOrDefault(key, -1);
    }
    public void put(int key, int value) {
        map.put(key, value);
    }
}

class LRUCacheII {
    // use a doubleLinkedList + HashMap

    static class Node {
        int key;
        int value;
        Node prev;
        Node next;
        public Node(){}
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // dummy head
    private Node head;
    // dummy tail
    private Node tail;
    private Map<Integer, Node> cache;
    private int size;
    private int capacity;

    public LRUCacheII(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();

        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if(node == null) {
            return -1;
        }
        // update the position
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);

        // the certain node is already in the linkedList
        if(node != null) {
            // update value and position
            node.value = value;
            moveToHead(node);
        } else {
            node = new Node(key, value);
            cache.put(key, node);
            addNode(node);
            size++;

            // out of capacity, remove the least recently used element
            if(size > capacity) {
                cache.remove(tail.prev.key);
                removeNode(tail.prev);
                size--;
            }
        }
    }

    // add a new node right after head
    private void addNode(Node node) {
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    // remove existing node from the doubleLinkedList
    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    // move a certain node right after head
    private void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }
}