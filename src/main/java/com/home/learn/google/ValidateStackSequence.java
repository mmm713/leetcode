package com.home.learn.google;

public class ValidateStackSequence {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length < 3) {
            return true;
        }
        int i = 0, j = 0;
        for (int newElement : pushed) {
            pushed[i++] = newElement;
            while (i > 0 && pushed[i - 1] == popped[j]) {
                i--;
                j++;
            }
        }
        return i == 0;
    }
}
