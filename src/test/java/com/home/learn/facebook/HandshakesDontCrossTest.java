package com.home.learn.facebook;

import org.junit.jupiter.api.Test;

public class HandshakesDontCrossTest {
    @Test
    void test() {
        HandshakesDontCross cross = new HandshakesDontCross();
        cross.numberOfWays(8);
    }
}
