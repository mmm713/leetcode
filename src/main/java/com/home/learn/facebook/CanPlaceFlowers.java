package com.home.learn.facebook;

public class CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; ++i) {
            if (n == 0) return true;
            if (flowerbed[i] == 0) {
                int next = (i == flowerbed.length - 1 ? 0 : flowerbed[i + 1]);
                int pre = (i == 0 ? 0 : flowerbed[i - 1]);
                if (next + pre == 0) {
                    flowerbed[i] = 1;
                    --n;
                }
            }
        }
        return n == 0;
    }

    public int totalFlowers(int[] flowerbed) {
        int l = 1, res = 0;
        for(int f : flowerbed) {
            if(f == 1) {
                res += (l - 1) / 2;
                l = 0;
            } else {
                l++;
            }
        }
        res += l / 2;
        return res;
    }
}
