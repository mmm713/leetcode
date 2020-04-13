package com.home.learn.pinterest;

import java.util.ArrayDeque;
import java.util.Queue;

public class Read4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */

    private Queue<Character> cache = new ArrayDeque<>(4);

    public int read(char[] buf, int n) {
        int res = 0;
        int size = cache.size();
        for(; res < Math.min(size, n); res++) {
            buf[res] = cache.poll();
        }
        return read4(buf, res, n - res);
    }

    private int read4(char[] buf, int start, int n) {
        if(n <= 0) return start;
        int res = start;
        int read = 0;
        char[] buffer = new char[4];
        for(int i = 0; i <= (n - 1) / 4; i++) {
            int temp = read4(buffer);
            if(temp == 0) break;
            for(int j = 0; j < temp; j++) {
                if(read++ < n) {
                    buf[res++] = buffer[j];
                } else {
                    cache.offer(buffer[j]);
                }
            }
        }
        return res;
    }

    private int read4(char[] buf) {
        return 0;
    }
}
