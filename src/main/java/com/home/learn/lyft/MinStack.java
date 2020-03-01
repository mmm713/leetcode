package com.home.learn.lyft;

import java.util.Stack;

public class MinStack<T extends Comparable<T>> {

    Stack<T> stack;
    T minimum;

    public MinStack() {
        this.stack = new Stack<>();
    }

    public void push(T x) {
        if(minimum == null || x.compareTo(minimum) < 0) {
            stack.push(minimum);
            minimum = x;
        }
        stack.push(x);
    }

    public void pop() {
        T temp = stack.pop();
        if(temp.equals(minimum)) {
            minimum = stack.pop();
        }
    }

    public T top() {
        return stack.peek();
    }

    public T getMin() {
        return minimum;
    }
}
