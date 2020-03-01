package com.home.learn.lyft;

import java.util.*;

public class InMemoryDatabase<K, V> {

    static class Entry<V> {
        V val;
        Map<Integer, V> transVal;
        public Entry(V val) {
            this.val = val;
            this.transVal = new HashMap<>();
        }
    }

    Map<K, Entry<V>> map;
    Map<V, Set<K>> vals;
    int transactionId;
    List<Set<K>> modified;

    public InMemoryDatabase() {
        map = new HashMap<>();
        vals = new HashMap<>();
        modified = new ArrayList<>();
        transactionId = 0;
    }

    public void block() {
        modified.add(new HashSet<>());
        transactionId++;
    }

    public void set(K key, V val) {
        if(transactionId > 0) {
            if(!map.containsKey(key)) {
                map.put(key, new Entry<>(null));
            }
            map.get(key).transVal.put(transactionId - 1, val);
            modified.get(transactionId - 1).add(key);
        } else {
            map.put(key, new Entry<>(val));
            if(vals.containsKey(val)) {
                vals.get(val).add(key);
            } else {
                vals.put(val, new HashSet<>(Collections.singletonList(key)));
            }
        }
    }

    public void unSet(K key) {
        if(map.containsKey(key)) {
            if(transactionId > 0) {
                map.get(key).transVal.put(transactionId - 1, null);
                modified.get(transactionId - 1).add(key);
            } else {
                vals.getOrDefault(map.get(key).val, new HashSet<>()).remove(key);
                map.remove(key);
            }
        }
    }

    public void rollBack() {
        if(transactionId == 0) return;
        for(K key : modified.get(--transactionId)) {
            map.get(key).transVal.remove(transactionId);
        }
    }

    public void commit() {
        if(transactionId == 0) return;
        for(K key : modified.get(--transactionId)) {
            V val = map.get(key).transVal.get(transactionId);
            map.get(key).val = val;
            if(val == null) {
                vals.getOrDefault(map.get(key).val, new HashSet<>()).remove(key);
            } else {
                if(vals.containsKey(val)) {
                    vals.get(val).add(key);
                } else {
                    vals.put(val, new HashSet<>(Collections.singletonList(key)));
                }
            }
        }
    }

    public V get(K key) {
        if(map.containsKey(key)) {
            return map.get(key).val;
        }
        return null;
    }

    public int numWithValue(V val) {
        return vals.getOrDefault(val, new HashSet<>()).size();
    }
}
