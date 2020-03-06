package com.home.learn.uber;

import java.util.HashMap;
import java.util.Map;

public class OffsetMap {
    int keyOffset;
    int valueOffset;
    Map<Integer, Integer> map;

    public OffsetMap() {
        this.map = new HashMap<>();
        keyOffset = 0;
        valueOffset = 0;
    }

    public void addToKey(int keyOffset) {
        this.keyOffset += keyOffset;
    }

    public void addToValue(int valueOffset) {
        this.valueOffset += valueOffset;
    }

    public int getKey(int key) {
        return map.get(key - keyOffset) + valueOffset;
    }

    public void insert(int key, int value) {
        map.put(key - keyOffset, value - valueOffset);
    }
}
