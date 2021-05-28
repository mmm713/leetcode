package com.home.learn.bytedance;

import java.util.Stack;

public class DecodeString {
    public String decodeString(String s) {
        Stack<Integer> intStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder runner = new StringBuilder();
        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } else if ( ch == '[') {
                intStack.push(k);
                strStack.push(runner);
                runner = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder tmp = runner;
                runner = strStack.pop();
                for (k = intStack.pop(); k > 0; --k) runner.append(tmp);
            } else runner.append(ch);
        }
        return runner.toString();
    }
}
