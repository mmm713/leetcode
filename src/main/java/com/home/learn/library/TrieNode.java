package com.home.learn.library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrieNode implements Comparable<TrieNode> {
    public TrieNode[] children;
    public int freq;
    public String word;
    public List<TrieNode> tops;

    public TrieNode() {
        this.children = new TrieNode[128];
        this.freq = 0;
        this.word = null;
        tops = new ArrayList<>();
    }

    @Override
    public int compareTo(TrieNode o) {
        return (this.freq == o.freq) ? (this.word.compareTo(o.word)) : (o.freq - this.freq);
    }

    public String getWord() {
        return word;
    }

    public void top(TrieNode node) {
        if(!this.tops.contains(node)){
            this.tops.add(node);
        }
        Collections.sort(tops);
        if(tops.size() > 3){
            tops.remove(tops.size() - 1);
        }
    }
}
