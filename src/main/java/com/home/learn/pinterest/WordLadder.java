package com.home.learn.pinterest;

import java.util.*;

public class WordLadder {
    //时空都是O MN
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) {
            return 0;
        }
        HashSet<String> begin = new HashSet<>();
        HashSet<String> end = new HashSet<>();
        HashSet<String> visited = new HashSet<>();
        begin.add(beginWord);
        end.add(endWord);

        int len = 1;
        while (!begin.isEmpty() && !end.isEmpty()) {
            // Calculate smaller set
            // 这步是优化
            if (begin.size() > end.size()) {
                HashSet<String> tmp = end;
                end = begin;
                begin = tmp;
            }

            HashSet<String> newBegin = new HashSet<>();
            for (String word : begin) {
                char[] word_arr = word.toCharArray();
                for (int i = 0; i < word_arr.length; i++) {
                    char old = word_arr[i];
                    for (char j = 'a'; j <= 'z'; j++) {
                        word_arr[i] = j;
                        String newWord = new String(word_arr);
                        if (end.contains(newWord)) {
                            return len + 1;
                        }
                        if (words.contains(newWord) && !visited.contains(newWord)) {
                            newBegin.add(newWord);
                            visited.add(newWord);
                        }
                    }
                    word_arr[i] = old;
                }
            }
            begin = newBegin;
            len++;
        }
        return 0;
    }

    public int wordLadder(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) {
            return 0;
        }
        Queue<String> begin = new ArrayDeque<>();
        HashSet<String> visited = new HashSet<>();
        begin.add(beginWord);

        int result = 1;
        int counter = 1;
        while (!begin.isEmpty()) {
            String word = begin.poll();
            char[] wc = word.toCharArray();
            for (int i = 0; i < wc.length; i++) {
                char old = wc[i];
                for (char j = 'a'; j <= 'z'; j++) {
                    wc[i] = j;
                    String newWord = new String(wc);
                    if (newWord.equals(endWord)) {
                        return ++result;
                    }
                    if (words.contains(newWord) && !visited.contains(newWord)) {
                        begin.add(newWord);
                        visited.add(newWord);
                    }
                }
                wc[i] = old;
            }
            if(--counter == 0) {
                counter = begin.size();
                result++;
            }
        }
        return 0;
    }

    //word ladder 2
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> words = new HashSet<>(wordList);
        HashSet<String> used = new HashSet<>(Collections.singletonList(beginWord));
        //queue contains all temp results
        Queue<LinkedList<String>> queue = new LinkedList<>();
        List<List<String>> result = new ArrayList<>();
        boolean found = false;
        queue.offer(new LinkedList<>(Collections.singletonList(beginWord)));

        while(!queue.isEmpty() && !found) {
            int size = queue.size();
            HashSet<String> localUsed = new HashSet<>();
            while(size > 0) {
                LinkedList<String> curr = queue.poll();
                char[] word = curr.getLast().toCharArray();
                for(int i = 0; i < word.length; i++){
                    char wc = word[i];
                    for(int j = 'a'; j <= 'z';j++){
                        word[i] = (char) j;
                        String s = String.valueOf(word);
                        if(!used.contains(s) && words.contains(s)){
                            LinkedList<String> list = new LinkedList<>(curr);
                            list.add(s);
                            if(s.equals(endWord)){
                                found = true;
                                result.add(list);
                                continue;
                            }
                            localUsed.add(s);
                            queue.offer(list);
                        }
                    }
                    word[i] = wc;
                }
                size--;
            }
            used.addAll(localUsed);
        }
        return result;
    }
}
