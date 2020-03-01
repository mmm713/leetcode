package com.home.learn.lyft;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class VersionedKeyValue<K, V> {

    Map<K, TreeMap<Integer, V>> map;
    AtomicInteger version;
    public VersionedKeyValue() {
        map = new HashMap<>();
        version = new AtomicInteger(1);
    }

    public void set(K key, V value) {
        TreeMap<Integer, V> tree = map.getOrDefault(key, new TreeMap<>());
        tree.put(version.getAndIncrement(), value);
        map.put(key, tree);
    }

    public V get(K key) {
        TreeMap<Integer, V> tree = map.getOrDefault(key, new TreeMap<>());
        return tree != null ? tree.get(tree.lastKey()) : null;
    }

    public V get(K key, int version) {
        TreeMap<Integer, V> tree = map.getOrDefault(key, new TreeMap<>());
        Integer t = tree.floorKey(version);
        return t != null ? tree.get(tree.floorKey(version)) : null;
    }
}
