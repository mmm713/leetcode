package com.home.learn.uber;

import java.util.ArrayList;
import java.util.List;

public class MemoryAllocate {
    int[] mem;
    int size;
    List<Integer> idx;
    List<Integer> space;

    public MemoryAllocate(int[] mem) {
        idx = new ArrayList<>();
        space = new ArrayList<>();
        this.mem = mem;
        this.size = mem.length / 8;
    }

    public int query(int i, int j) {
        if(i == 0) {
            return allocate(j);
        } else {
            return release(j - 1);
        }
    }

    private int allocate(int j) {
        for(int i = 0; i < size; i++) {
            if(validate(i * 8, j)) {
                int k = fill(i * 8, j);
                idx.add(i * 8+ k);
                space.add(j);
                return i * 8 + k;
            }
        }
        return -1;
    }

    private boolean validate(int i, int j) {
        int count = 0;
        for(int k = 0; k < 8; k++) {
            if(mem[i + k] == 0) {
                count++;
            } else {
                break;
            }
        }
        return count >= j;
    }

    private int fill(int i, int j) {
        int k = 0;
        for(; k < 8; k++) {
            if(mem[i + k] == 1) {
                break;
            }
        }
        k--;
        for(; k >= 0; k--) {
            if(mem[i + k] == 0) {
                mem[i + k] = 1;
                if(--j == 0) {
                    break;
                }
            }
        }
        return k;
    }


    private int release(int j) {
        int start = idx.get(j);
        for(int i = 0; i < space.get(j); i++) {
            mem[start + i] = 0;
        }
        return space.get(j);
    }
}
