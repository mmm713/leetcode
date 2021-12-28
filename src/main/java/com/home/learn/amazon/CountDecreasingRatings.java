package com.home.learn.amazon;

public class CountDecreasingRatings {
    public int countDecreasingRatings(int[] ratings) {
        int res = 1;
        int counter = 1;
        for (int i = 1; i < ratings.length; i++) {
            if(ratings[i] == ratings[i - 1] - 1) {
                counter += 1;
            } else {
                counter = 1;
            }
            res += counter;
        }
        return res;
    }
}
