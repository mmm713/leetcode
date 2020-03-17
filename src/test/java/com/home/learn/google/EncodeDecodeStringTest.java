package com.home.learn.google;

import org.junit.jupiter.api.Test;

class EncodeDecodeStringTest {
    @Test
    void test() {
        EncodeDecodeString encodeDecodeString = new EncodeDecodeString();
        System.out.println(encodeDecodeString.encode("abbbabbbcabbbabbbc"));
    }
}
