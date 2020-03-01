package com.home.learn.lyft;

import java.util.Stack;

public class MaxStack {
    Stack<Integer> stack;
    int max = Integer.MIN_VALUE;

    public MaxStack() {
        stack = new Stack();
    }

    public void push(int x) {
        if(x >= max) {
            stack.push(max);
            max = x;
        }
        stack.push(x);
    }

    public int pop() {
        int temp = stack.pop();
        if(temp == max) {
            max = stack.pop();
        }
        return temp;
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return max;
    }

    public int popMax() {
        int toReturn = max;
        Stack<Integer> buffer = new Stack<>();
        while (top() != max) buffer.push(pop());
        pop();
        while (!buffer.isEmpty()) push(buffer.pop());
        return toReturn;
    }
}
