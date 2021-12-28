package com.home.learn.amazon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreOfParenthesesTest {
    @Test
    void test() {
        ScoreOfParentheses parentheses = new ScoreOfParentheses();
        assertEquals(1, parentheses.scoreOfParentheses("()"));
        assertEquals(2, parentheses.scoreOfParentheses("()()"));
        assertEquals(8, parentheses.scoreOfParentheses("(()()()())"));
        assertEquals(2, parentheses.scoreOfParentheses("(())"));
        assertEquals(6, parentheses.scoreOfParentheses("(()(()))"));
    }
}
