package com.home.learn.library;

public class UnionNode<T> {
    public T parent;
    public double ratio;
    public UnionNode(T parent, double ratio) {
        this.parent = parent;
        this.ratio = ratio;
    }
    public UnionNode(T parent) {
        this.parent = parent;
    }
}
