package com.home.learn.pinterest;

import java.util.*;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);

        if (!wordSet.contains(endWord)) {
            return 0;
        }

        // Two-way BFS
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Set<String> tempSet;

        beginSet.add(beginWord);
        endSet.add(endWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        visited.add(endWord);

        int dist = 2;

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            tempSet = new HashSet<>();
            for (String word : beginSet) {
                List<String> neighbors = getNeighbors(wordSet, word);
                for (String nei : neighbors) {
                    if (endSet.contains(nei)) {
                        return dist;
                    }
                    if (!visited.contains(nei)) {
                        visited.add(nei);
                        tempSet.add(nei);
                    }
                }
            }
            beginSet = tempSet;
            if (beginSet.size() > endSet.size()) {
                Set<String> t = beginSet;
                beginSet = endSet;
                endSet = t;
            }
            dist++;
        }

        return 0;
    }

    private List<String> getNeighbors(Set<String> wordSet, String word) {
        List<String> res = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            char[] A = word.toCharArray();

            for (char ch = 'a'; ch <= 'z'; ch++) {
                A[i] = ch;
                String s = String.valueOf(A);
                if (!s.equals(word) && wordSet.contains(s)) {
                    res.add(s);
                }
            }
        }

        return res;
    }

    //word ladder 2
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> used = new HashSet<>();
        Queue<LinkedList<String>> q = new LinkedList<>();
        List<List<String>> result = new ArrayList<>();
        boolean found = false;

        HashSet<String> words = new HashSet<>(wordList);

        LinkedList<String> first = new LinkedList<>();
        first.add(beginWord);
        q.offer(first);
        used.add(beginWord);

        while(!q.isEmpty()){
            int size = q.size();
            HashSet<String> localUsed = new HashSet<>();
            while(size>0){
                LinkedList<String> curr = q.poll();
                char[] word = curr.getLast().toCharArray();
                for(int i=0;i<word.length;i++){
                    char temp = word[i];
                    for(int j='a';j<='z';j++){
                        word[i]=(char) j;
                        String s = String.valueOf(word);
                        if(!used.contains(s) && words.contains(s)){
                            LinkedList<String> list = new LinkedList<>(curr);
                            list.add(s);
                            if(s.equals(endWord)){
                                found=true;
                                result.add(list);
                                continue;
                            }
                            localUsed.add(s);
                            q.offer(list);
                        }
                    }
                    word[i]=temp;
                }
                size--;
            }
            used.addAll(localUsed);
            if(found) break;
        }
        return result;
    }
}
