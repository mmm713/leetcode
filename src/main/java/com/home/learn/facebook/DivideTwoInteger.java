package com.home.learn.facebook;

public class DivideTwoInteger {
    // fastD尝试2倍速求解，搜索复杂度logn n = dividend
    // 分子：numerator；分母：denominator
    public int divide(int dividend, int divisor) {
        long result = 0;
        long n = dividend < 0 ? -(long)dividend : dividend;
        long d = divisor < 0 ? -(long)divisor : divisor;
        long fastD = d, tmp = 1;
        while(n > 0) {
            n -= fastD;
            if(n < 0) {
                if(tmp == 1) {
                    break;
                } else {
                    n += fastD;
                    tmp = 1;
                    fastD = d;
                }
            } else {
                result += tmp;
                tmp <<= 1;
                fastD <<= 1;
            }
        }
        if ((dividend > 0 && divisor < 0)||(dividend < 0 && divisor > 0)) result = -result;
        if(result > Integer.MAX_VALUE) result = Integer.MAX_VALUE;
        if(result < Integer.MIN_VALUE) result = Integer.MIN_VALUE;
        return (int) result;
    }
}
