package com.home.learn.inheritance;

public class Son extends Father {
    public int x;

    public Son() {
        this.x = 10;
    }

    public void print() {
        System.out.println("I'm son");
    }

    public void printMe(String a) {
        System.out.println("I'm my son");
    }
}
