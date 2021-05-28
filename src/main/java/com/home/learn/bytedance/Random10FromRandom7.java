package com.home.learn.bytedance;

import com.home.learn.library.Random;

public class Random10FromRandom7 {
    public int rand10(Random rand) {
        int row, col, idx;
        do {
            row = rand.rand7();
            col = rand.rand7();
            idx = col + (row - 1) * 7;
        } while (idx > 40);
        return 1 + (idx - 1) % 10;
    }

    int i = 0;
    public int rand10v2(Random rand) {
        i = (i + rand.rand7()) % 10;
        return i + 1;
    }
}
