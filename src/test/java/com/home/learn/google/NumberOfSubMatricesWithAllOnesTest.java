package com.home.learn.google;

import org.junit.jupiter.api.Test;

public class NumberOfSubMatricesWithAllOnesTest {
    @Test
    void test(){
        NumberOfSubMatricesWithAllOnes test = new NumberOfSubMatricesWithAllOnes();
        int[][] input = {{1, 0, 1, 1},
                {0, 1, 0, 1},
                {1, 1, 1, 0},
                {1, 0, 1, 1}};
        System.out.println(test.matrixAllOne(input));
    }
}
