package com.home.learn.leetcode;

import java.util.Map;
import java.util.TreeMap;

public class NumberOfAtoms {
    public String countOfAtoms(String formula) {
        StringBuilder ans = new StringBuilder();
        int[] index = new int[1];
        Map<String, Integer> count = parse(formula, index);
        for (String name: count.keySet()) {
            ans.append(name);
            int multiplicity = count.get(name);
            if (multiplicity > 1) ans.append(multiplicity);
        }
        return new String(ans);
    }

    public Map<String, Integer> parse(String formula, int[] index) {
        int N = formula.length();
        Map<String, Integer> count = new TreeMap<>();
        while (index[0] < N && formula.charAt(index[0]) != ')') {
            if (formula.charAt(index[0]) == '(') {
                index[0]++;
                for (Map.Entry<String, Integer> entry: parse(formula, index).entrySet()) {
                    count.put(entry.getKey(), count.getOrDefault(entry.getKey(), 0) + entry.getValue());
                }
            } else {
                int iStart = index[0]++;
                while (index[0] < N && Character.isLowerCase(formula.charAt(index[0]))) index[0]++;
                String name = formula.substring(iStart, index[0]);
                iStart = index[0];
                while (index[0] < N && Character.isDigit(formula.charAt(index[0]))) index[0]++;
                int multiplicity = iStart < index[0] ? Integer.parseInt(formula.substring(iStart, index[0])) : 1;
                count.put(name, count.getOrDefault(name, 0) + multiplicity);
            }
        }
        int iStart = ++index[0];
        while (index[0] < N && Character.isDigit(formula.charAt(index[0]))) index[0]++;
        if (iStart < index[0]) {
            int multiplicity = Integer.parseInt(formula.substring(iStart, index[0]));
            count.replaceAll((k, v) -> count.get(k) * multiplicity);
        }
        return count;
    }
}
