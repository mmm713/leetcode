package com.home.learn.facebook;

public class NumberOfOnes {
    public int hammingWeight(int n) {
        n = n - ((n >> 1) & 0x55555555);
        n = (n & 0x33333333) + ((n >> 2) & 0x33333333);
        n = (((n + (n >> 4)) & 0x0F0F0F0F) * 0x01010101) >> 24;
        return n;
    }
    public int hammingWeight1(int n) {
        int bits = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                bits++;
            }
            mask <<= 1;
        }
        return bits;
    }
    public int hammingWeight2(int n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            n &= (n - 1);
        }
        return sum;
    }

    //输出从0到n，每个数1的个数
    public int[] countBits(int num) {
        //things are happenning repeatly,
        //every step of 1/2/4/8/16/32 etc. just 1 more 1 to the previous steps
        int[] result = new int[num + 1];
        int step = 1, power = 1;
        for(int i = 0; i <= num; i++) {
            if(i == 0) {
                result[i] = 0;
            } else if(step == 0) {
                result[i] = 1;
                if(i > 1)
                    power *= 2;
                step = power;
            } else {
                result[i] = 1 + result[i - power];
            }
            step--;
        }
        return result;
    }
}
