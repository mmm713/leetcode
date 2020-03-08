package com.home.learn.wish;

import java.util.*;

public class MinStack {    // One stack of length N -> 2*N integer storage

    Stack<int[]> stack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
    }

    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(new int[]{x,x});
        } else {
            stack.push(new int[]{x, Math.min(x, this.getMin())});
        }
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek()[0];
    }

    public int getMin() {
        return stack.peek()[1];
    }

    public static void main(String[] args) {
        MinStack stk = new MinStack();
        stk.push(1);
        stk.push(2);
        stk.push(-1);
        stk.push(2);
        System.out.println(stk.top());
        System.out.println(stk.getMin());
        stk.pop();
        System.out.println(stk.top());
        System.out.println(stk.getMin());
        stk.pop();
        System.out.println(stk.top());
        System.out.println(stk.getMin());
    }
}


class MinStackUsingTwoStacks {

    Stack<Integer> stack1;   // store every number
    Stack<Integer> stack2;   // store the min value

    public MinStackUsingTwoStacks() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        stack1.push(x);
        if (stack2.isEmpty() || x <= getMin()) {
            stack2.push(x);
        }
    }

    public void pop() {
        int x = stack1.pop();
        if (x == getMin())
            stack2.pop();
    }

    public int top() {
        return stack1.peek();
    }

    public int getMin() {
        return stack2.peek();
    }

    public static void main(String[] args) {
        MinStack stk = new MinStack();
        stk.push(1);
        stk.push(2);
        stk.push(-1);
        stk.push(2);
        System.out.println(stk.top());  // 2
        System.out.println(stk.getMin());   // -1
        stk.pop();
        System.out.println(stk.top());  // -1
        System.out.println(stk.getMin());   // -1
        stk.pop();
        System.out.println(stk.top());  // 2
        System.out.println(stk.getMin());  // 1
    }

}


class MinStackUsingSelfImplementedList {

    static class Node {
        int val;
        int min;
        Node next;
        public Node(int v, int m, Node nxt) {
            val = v;
            min = m;
            next = nxt;
        }
    }

    Node head;

    public MinStackUsingSelfImplementedList() {
        head = null;
    }

    public void push(int x) {
        if (head == null) {
            head = new Node(x, x, null);
        } else {
            head = new Node(x, Math.min(x, head.min), head);
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }

    public static void main(String[] args) {
        MinStack stk = new MinStack();
        stk.push(1);
        stk.push(2);
        stk.push(-1);
        stk.push(2);
        System.out.println(stk.top());  // 2
        System.out.println(stk.getMin());   // -1
        stk.pop();
        System.out.println(stk.top());  // -1
        System.out.println(stk.getMin());   // -1
        stk.pop();
        System.out.println(stk.top());  // 2
        System.out.println(stk.getMin());  // 1
    }

}