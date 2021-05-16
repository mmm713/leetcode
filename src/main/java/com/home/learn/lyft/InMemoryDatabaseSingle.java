package com.home.learn.lyft;

import java.util.*;

public class InMemoryDatabaseSingle<K, V> {

    Map<K, V> map;
    Map<V, Integer> valCount;
    Stack<Map<K, V>> transactions;
    boolean inTransaction;

    public InMemoryDatabaseSingle() {
        map = new HashMap<>();
        valCount = new HashMap<>();
        transactions = new Stack<>();
        inTransaction = false;
    }

    public void block() {
        inTransaction = true;
        transactions.push(new HashMap<>());
    }

    public void commitSet(K key, V val) {
        if(map.containsKey(key)) {
            V temp = map.get(key);
            if(val != temp) {
                if(!valCount.containsKey(val)) {
                    valCount.put(val, 1);
                } else {
                    valCount.computeIfPresent(val, (k, v) -> v - 1);
                }
                valCount.computeIfPresent(temp, (k, v) -> v - 1);
            }
        } else {
            valCount.computeIfPresent(val, (k, v) -> v + 1);
            valCount.putIfAbsent(val, 1);
        }
        map.put(key, val);
    }

    public void set(K key, V val) {
        if(!inTransaction) {
            commitSet(key, val);
        } else {
            transactions.peek().put(key, val);
        }
    }

    public void commitUnSet(K key) {
        if(map.containsKey(key)) {
            V temp = map.get(key);
            map.remove(key);
            valCount.computeIfPresent(temp, (k, v) -> v - 1);
        }
    }

    public void unSet(K key) {
        if(!inTransaction) {
            commitUnSet(key);
        } else {
            transactions.peek().put(key, null);
        }
    }

    public void rollBack() {
        transactions.pop();
        inTransaction = transactions.size() == 0;
    }

    public void commit() {
        if(inTransaction) {
            for(Map.Entry<K, V> entry : transactions.pop().entrySet()) {
                if(entry.getValue() == null) {
                    commitUnSet(entry.getKey());
                } else {
                    commitSet(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    public V get(K key) {
        return map.get(key);
    }

    public int numWithValue(V val) {
        return valCount.getOrDefault(val, 0);
    }
}
