package com.home.learn.facebook;

import java.util.Stack;

public class SimplifyPath {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        for (String dir : path.split("/")) {
            if (dir.equals("..") && !stack.isEmpty()) {
                stack.pop();
            }
            else if (!dir.equals("..") && !dir.equals(".") && !dir.equals("")) {
                stack.push(dir);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.insert(0, "/" + stack.pop());
        }
        return res.length() == 0 ? "/" : res.toString();
    }

    public String modifyString(String s) {
        char[] sc = s.toCharArray();
        char[] res = new char[sc.length];
        int j = 0;
        for (int i = 0; i < sc.length; i++) {
            if(sc[i] == '?') {

            }
            if(j == 27) j = 0;
            res[i] = sc[i] == '?' ? (char) (j++ + 'a') : sc[i];
        }
        return new String(res);
    }

    private int getNext(int prev, int next) {
        if(prev == -1 && next == -1) {
            return 0;
        } else if(next == -1) {
            return prev == 25 ? 0 : prev + 1;
        } else if(prev == -1) {
            return next == 25 ? 0 : next + 1;
        } else {
            return 0;
        }
    }
}
