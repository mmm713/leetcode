package com.home.learn.bytedance;

import java.util.*;

public class WordAbbreviation {
    //尝试所有人直接求缩写，一旦撞车，说明同长度。然后前缀树找到count为1的叶子节点就是答案
    static class TrieNode {
        TrieNode[] children;
        int count;
        TrieNode() {
            children = new TrieNode[26];
            count = 0;
        }
    }
    static class Word {
        String word;
        int idx;
        Word(String w, int i) {
            word = w;
            idx = i;
        }
    }
    private void add(TrieNode root, Word w) {
        TrieNode runner = root;
        for (char c : w.word.substring(1).toCharArray()) {
            if (runner.children[c - 'a'] == null) {
                runner.children[c - 'a'] = new TrieNode();
            }
            runner.count++;
            runner = runner.children[c - 'a'];
        }
    }
    private String get(TrieNode root, Word w) {
        TrieNode runner = root;
        char[] sc = w.word.toCharArray();
        for (int i = 1; i < sc.length; i++) {
            if(runner.count == 1) {
                return abbrev(w.word, i);
            } else {
                runner = runner.children[sc[i] - 'a'];
            }
        }
        return null;
    }
    public List<String> wordsAbbreviation(List<String> words) {
        Map<String, List<Word>> groups = new HashMap<>();
        String[] ans = new String[words.size()];
        for (int i = 0; i < words.size(); i++) {
            String abbrev = abbrev(words.get(i), 1);
            groups.putIfAbsent(abbrev, new ArrayList<>());
            groups.get(abbrev).add(new Word(words.get(i), i));
        }
        for (Map.Entry<String, List<Word>> g: groups.entrySet()) {
            if(g.getValue().size() == 1) {
                ans[g.getValue().get(0).idx] = g.getKey();
            } else {
                TrieNode root = new TrieNode();
                for (Word w : g.getValue()) {
                    add(root, w);
                }
                for (Word w : g.getValue()) {
                    ans[w.idx] = get(root, w);
                }
            }
        }
        return Arrays.asList(ans);
    }
    private String abbrev(String s, int k) {
        if (k >= s.length()-2) return s;
        return s.substring(0, k) +
                (s.length() - 1 - k) +
                s.charAt(s.length() - 1);
    }
}
