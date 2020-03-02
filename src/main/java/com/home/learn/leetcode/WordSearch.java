package com.home.learn.leetcode;

import com.home.learn.library.TrieNode;

import java.util.ArrayList;
import java.util.List;

public class WordSearch {
    private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public boolean exist(char[][] board, String word) {
        if (word.length() == 0) return true;
        if (board.length == 0 || board[0].length == 0) return false;
        boolean[][] visited =  new boolean[board.length][board[0].length];
        char[] words = word.toCharArray();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(dfsExist(board, words, i, j, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean dfsExist(char[][] board, char[] words, int i, int j, int idx, boolean[][] visited){
        if(idx == words.length) return true;
        if(i < 0 || j < 0 || i >= board.length || j>= board[0].length || visited[i][j] || board[i][j] != words[idx]) return false;
        visited[i][j] = true;
        for (int[] direction : directions) {
            int x = i + direction[0];
            int y = j + direction[1];
            if(dfsExist(board, words, x, y, idx + 1, visited)) {
                return true;
            }
        }
        visited[i][j] = false;
        return false;
    }


    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for(String word : words) {
            TrieNode node = root;
            for(char c : word.toCharArray()) {
                if(node.children[c -'a'] == null) {
                    node.children[c-'a'] = new TrieNode();
                }
                node = node.children[c-'a'];
            }
            node.word = word;
        }
        return root;
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        TrieNode root = buildTrie(words);

        for(int i = 0; i< board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                dfs(board, result, root, i, j);
            }
        }
        return result;
    }

    public void dfs(char[][] board, List<String> result, TrieNode node, int i, int j) {
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '#') {
            return;
        }
        if(node.children[board[i][j] -'a'] == null) {
            return;
        }
        node = node.children[board[i][j] -'a'];

        if(node.word != null) {
            result.add(node.word);
            node.word = null;
        }
        char c = board[i][j];
        board[i][j] = '#';
        dfs(board, result, node, i+1, j);
        dfs(board, result, node, i-1, j);
        dfs(board, result, node, i, j+1);
        dfs(board, result, node, i, j-1);
        board[i][j] = c;
    }
}
