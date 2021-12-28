package com.home.learn.uber;

import java.util.Stack;

public class BasicCalculator {
    //只有加减括号
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int number = 0;
        int sign = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = number * 10  + (c - '0');
            } else if (c == '+') {
                result += sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-') {
                result += sign * number;
                number = 0;
                sign = -1;
            } else if (c == '(') {
                stack.push(result);
                stack.push(sign);
                sign = 1;
                result = 0;
            } else if (c == ')') {
                result += sign * number;
                number = 0;
                result *= stack.pop();
                result += stack.pop();
            }
        }
        if (number != 0) {
            result += sign * number;
        }
        return result;
    }

    //只有加减乘除，只有遇到加减号才可以合并之前结果
    public int calculateII(String s) {
        int res = 0;
        int currNum = 0;
        int lastNum = 0;
        char opt = '+';
        for (char c : s.toCharArray()) {
            if (c == ' ') continue;
            if (Character.isDigit(c)) {
                currNum = currNum * 10 + c - '0';
            } else {
                lastNum = cal(lastNum, currNum, opt);
                if (c == '+' || c == '-') {
                    res += lastNum;
                    lastNum = 0;
                }
                currNum = 0;
                opt = c;
            }
        }
        return res + cal(lastNum, currNum, opt);
    }

    private int cal(int lastNum, int currNum, char opt) {
        if (opt == '+') {
            return lastNum + currNum;
        } else if (opt == '-') {
            return lastNum - currNum;
        } else if (opt == '*') {
            return lastNum * currNum;
        } else {
            return lastNum / currNum;
        }
    }

    //加减乘除括号
    private int calculateIII(String s, int[] idx) {
        int sum = 0, pre = 0;
        char sign = '+';
        for (; idx[0] < s.length(); idx[0]++) {
            char c = s.charAt(idx[0]);
            if (c == ' ') continue;
            if (Character.isDigit(c) || c == '(')  {
                int num;
                if (c == '(') {
                    idx[0]++;
                    num = calculateIII(s, idx);
                } else {
                    num = c - '0';
                    while (idx[0] + 1 < s.length() && Character.isDigit(s.charAt(idx[0] + 1)))
                        num = num * 10 + s.charAt(++idx[0]) - '0';
                }
                if (sign == '+' || sign == '-')  {
                    sum += pre;
                    pre = sign == '+' ? num : -num;
                } else if (sign == '*') pre *= num;
                else if (sign == '/') pre /= num;
            } else if (c == ')') break;
            else sign = c;
        }
        return sum + pre;
    }
}
