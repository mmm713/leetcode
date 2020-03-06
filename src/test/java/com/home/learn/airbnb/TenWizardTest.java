package com.home.learn.airbnb;

import org.junit.jupiter.api.Test;

public class TenWizardTest {
    @Test
    void test(){
        int[][] wizards = {
                {1, 2},
                {1, 4},
                {1, 3},
                {1, 5},
                {3, 2},
                {4, 2},
                {2, 7},
                {5, 6},
                {6, 8},
                {9, 1},
                {1, 8},
                {8, 9},
        };
        TenWizard wizard = new TenWizard();
        System.out.println(wizard.findMinCost(wizards, 1, 7));
        System.out.println(wizard.findMinCost(wizards, 1, 8));
        System.out.println(wizard.findMinCost(wizards, 1, 6));
        System.out.println(wizard.findMinCost(wizards, 3, 9));
    }
}
