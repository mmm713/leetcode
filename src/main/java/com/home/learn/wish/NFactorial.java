package com.home.learn.wish;

public class NFactorial {
    public int findTrailingZerosII(int n) {
        long result = 1;
        for(int i = 2; i <= n; i++) {
            result *= i;
        }
        int count = 0;
        while(result > 0) {
            if(result % 10 == 0) {
                count++;
                result /= 10;
            } else {
                break;
            }
        }
        return count;
    }

    public long getFactorial(int n) {
        long result = 1;
        for(int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public int findTrailingZeros(int n) {
        int count = 0;
        for (int i = 5; n / i >= 1; i *= 5)
            count += n / i;
        return count;
    }
}
