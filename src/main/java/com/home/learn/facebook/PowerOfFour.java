package com.home.learn.facebook;

public class PowerOfFour {
    public boolean isPowerOfFour(int num) {
        return (num > 0 && Integer.bitCount(num) == 1 && ((num & 0xAAAAAAAA) == 0));
    }

    public boolean isPowerOfFourMath(int num) {
        return (num > 0) && (Math.log(num) / Math.log(2) % 2 == 0);
    }
}
