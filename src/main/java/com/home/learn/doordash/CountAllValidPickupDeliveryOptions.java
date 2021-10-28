package com.home.learn.doordash;

public class CountAllValidPickupDeliveryOptions {
    int mod=1000000007;
    public int countOrders(int n) {
        long last=1;
        for(int i=1;i<=n;i++){
            //组合 C(2,2*i)
            int c=i*(2*i-1);
            last=(last*c) % mod;
        }
        return (int)last;
    }
}
