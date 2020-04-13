package com.home.learn.pinterest;

public class FindTheCelebrity {
    public int findCelebrity(int n) {
        if(n == 2 && !knows(0, 1) && !knows(1, 0)) return -1;

        int celebrity = 0;
        for(int i = 1; i < n; i++) {
            if(knows(celebrity, i)) celebrity = i;
        }

        for(int i = 0; i < celebrity; i++) {
            if(knows(celebrity, i)) return -1;
        }
        return celebrity;
    }

    private boolean knows(int a, int b) {
        return true;
    }
}
