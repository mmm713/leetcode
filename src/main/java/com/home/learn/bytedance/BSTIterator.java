package com.home.learn.bytedance;

import com.home.learn.library.TreeNode;

import java.util.*;

public class BSTIterator {
    //O(1) for the constructor.
    //
    //O(1) for hasPrev.
    //
    //O(1) for prev.
    //
    //O(1) for hasNext.
    //
    //O(N) for next. In the worst-case of the skewed tree one has to parse the entire tree, all NN nodes.
    Stack<TreeNode> stack;
    List<Integer> arr;
    TreeNode last;
    int counter;

    public BSTIterator(TreeNode root) {
        last = root;
        stack = new Stack<>();
        arr = new ArrayList<>();
        counter = -1;
    }

    public boolean hasNext() {
        return !stack.isEmpty() || last != null || counter < arr.size() - 1;
    }

    public int next() {
        ++counter;
        if(counter == arr.size()){
            while(last != null){
                stack.push(last);
                last = last.left;
            }
            TreeNode current = stack.pop();
            last = current.right;
            arr.add(current.val);
        }
        return arr.get(counter);
    }

    public boolean hasPrev() {
        return counter > 0;
    }

    public int prev() {
        --counter;
        return arr.get(counter);
    }
}
