package com.home.learn.lyft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*
AutocompleteSystem() takes O(k*l)O(k∗l) time. We need to iterate over ll sentences each of average length kk,
to create the trie for the given set of sentencessentences.

input() takes O\big(p+q+m \log m\big)O(p+q+mlogm) time.
Here, pp refers to the length of the sentence formed till now,
\text{cur\_sent}cur_sent. qq refers to the number of nodes in the trie considering the sentence f
ormed till now as the root node. Again, we need to sort the listlist
of length mm indicating the options available for the hot sentences, which takes O\big(m \log m\big)O(mlogm) time.
 */
public class AutocompleteSystem {
    static class TrieNode implements Comparable<TrieNode> {
        TrieNode[] children;
        int freq;
        String word;
        List<TrieNode> tops;

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

    TrieNode root;
    TrieNode cur;
    StringBuilder sb;

    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        cur = root;
        sb = new StringBuilder();
        for(int i = 0; i < sentences.length; i++){
            add(sentences[i], times[i]);
        }
    }

    public void add(String input, int freq) {
        TrieNode runner = root;
        for(char c : input.toCharArray()){
            if(runner.children[c] == null){
                runner.children[c] = new TrieNode();
            }
            runner = runner.children[c];
        }
        runner.word = input;
        TrieNode node = runner;
        node.freq += freq;
        runner = root;
        for(char c : input.toCharArray()){
            runner = runner.children[c];
            runner.top(node);
        }
    }

    public List<String> input(char c) {
        if(c == '#'){
            add(sb.toString(), 1);
            sb = new StringBuilder();
            cur = root;
            return new ArrayList<>();
        }
        sb.append(c);
        if(cur != null) {
            cur = cur.children[c];
        }
        return (cur == null) ? new ArrayList<>() :
                cur.tops.stream().map(TrieNode::getWord).collect(Collectors.toList());
    }
}
