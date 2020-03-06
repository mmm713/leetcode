package com.home.learn.airbnb;

import java.util.ArrayList;
import java.util.List;

public class BullsAndCows {

    public String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] counts = new int[10];

        for(int i = 0; i < secret.length(); i++) {
            if(secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else {
                if(counts[secret.charAt(i) - '0']++ < 0) {
                    cows++;
                }
                if(counts[guess.charAt(i) - '0']-- > 0) {
                    cows++;
                }
            }
        }
        return String.valueOf(bulls) + 'A' + cows + 'B';
    }

    public Result getHintResult(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] counts = new int[10];

        for(int i = 0; i < secret.length(); i++) {
            if(secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else {
                if(counts[secret.charAt(i) - '0']++ < 0) {
                    cows++;
                }
                if(counts[guess.charAt(i) - '0']-- > 0) {
                    cows++;
                }
            }
        }
        return new Result(bulls, cows);
    }

    static class Result {
        int A;
        int B;

        public Result(int a, int b) {
            A = a;
            B = b;
        }
    }

    public List<String> solve(String secret) {
        String guess = "1234";
        Result result = getHintResult(guess, secret);
        List<String> toReturn = new ArrayList<>();
        toReturn.add(guess);
        while (result.A < 4) {

        }
        return toReturn;
    }
}
