package com.home.learn.interview;

import com.home.learn.lyft.HanoiTower;
import org.junit.jupiter.api.Test;

public class HanoiTowerTest {

    @Test
    public void testHappyCase() {
        HanoiTower.towerOfHanoi(7, 'A', 'C', 'B');
    }
}
