package com.home.learn.airbnb;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class AlienDictionary {

    private boolean[][] edges = new boolean[26][26];
    private int[] inDegree = new int[26];

    public String alienOrder(String[] words) {
        buildGraph(words);
        return topologyPrint(edges, inDegree);
    }

    private String topologyPrint(boolean[][] edges, int[] inDegree) {
        int wordCount = 0;
        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new ArrayDeque<>(26);
        for(int i = 0; i < 26; i++) {
            if(inDegree[i] == 0) {
                q.offer(i);
                wordCount++;
            } else if(inDegree[i] > 0) {
                wordCount++;
            }
        }
        while(!q.isEmpty()) {
            int word = q.poll();
            sb.append((char) ('a' + word));
            for(int i = 0; i < 26; i++) {
                if(edges[word][i] && --inDegree[i] == 0) {
                    q.offer(i);
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

    //verify alien

    int[] map;

    public boolean isAlienSorted(String[] words, String order) {
        map = new int[order.length()];
        for(int i = 0; i < order.length(); i++){
            map[order.charAt(i) - 'a'] = i;
        }

        for(int i = 1; i < words.length; i++){
            if(!verifyOrder(words[i - 1], words[i]))
                return false;
        }
        return true;
    }

    private boolean verifyOrder(String s1, String s2){
        for(int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
            int val1 = map[s1.charAt(i) - 'a'];
            int val2 = map[s2.charAt(i) - 'a'];
            if(val1 < val2)
                return true;
            else if(val1 > val2)
                return false;
        }
        return s1.length() <= s2.length();
    }
}
