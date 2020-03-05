package com.home.learn.airbnb;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class AlienDictionary {

    private boolean[][] edges = new boolean[26][26];
    private int[] inDegree = new int[26];

    private StringBuilder res = new StringBuilder();

    public String alienOrder(String[] words) {
        buildGraph(words);
        return toplogyPrint(edges, inDegree);
    }

    private String toplogyPrint(boolean[][] edges, int[] inDegree) {
        int wordCount = 0;
        StringBuilder sb = new StringBuilder();
        Queue<Integer> topo = new ArrayDeque<Integer>(26);
        for(int i = 0; i < 26; i++) {
            if(inDegree[i] == 0) {
                topo.offer(i);
                wordCount++;
            } else if(inDegree[i] > 0) {
                wordCount++;
            }
        }
        while(!topo.isEmpty()) {
            int word = topo.poll();
            sb.append((char) ('a' + word));
            for(int i = 0; i < 26; i++) {
                if(edges[word][i] && --inDegree[i] == 0) {
                    topo.offer(i);
                }
            }
            wordCount--;
        }
        if(wordCount != 0) return "";
        return sb.toString();
    }

    private void buildGraph(String[] words){
        Arrays.fill(inDegree, -1);
        for(char c : words[0].toCharArray()){
            inDegree[c -'a'] = 0;
        }

        for(int i = 1; i < words.length; i++){
            String word1 = words[i - 1], word2 = words[i];
            int j = 0;
            for(; j < Math.min(word1.length(), word2.length()); j++){
                char a = word1.charAt(j), b = word2.charAt(j);
                if(a != b) {
                    if(inDegree[b - 'a'] == -1) {
                        inDegree[b - 'a'] = 0;
                    }
                    if(!edges[a - 'a'][b - 'a']) {
                        edges[a - 'a'][b - 'a'] = true;
                        inDegree[b -'a']++;
                    }
                    break;
                }
            }
            for(int k = j; k < word2.length(); k++) {
                char kc = word2.charAt(k);
                if(inDegree[kc - 'a'] == -1) {
                    inDegree[kc - 'a'] = 0;
                }
            }
        }
    }
}
