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

    //只有加减乘除
    public int calculateII(String s) {
        Stack<String> stack = new Stack<>();
        String d = "";
        char sign = '+';
        for(int i = 0 ; i < s.length() ; i++){
            char c = s.charAt(i);
            if(c >= '0') {
                d += c;
            }
            if((c < '0' && c != ' ') || i == s.length() - 1) {
                if(sign == '+') stack.add(d);
                else if(sign == '-') stack.add(sign + d);
                else if(sign == '*' || sign == '/') {
                    String temp = String.valueOf((sign == '*') ?
                            Integer.parseInt(stack.pop()) * Integer.parseInt(d) :
                            Integer.parseInt(stack.pop()) / Integer.parseInt(d));
                    stack.add(temp);
                }
                sign = c;
                d = "";
            }
        }
        return stack.stream().map(Integer::parseInt).reduce(0, Integer::sum);
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
