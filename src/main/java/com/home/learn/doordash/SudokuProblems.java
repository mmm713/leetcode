package com.home.learn.doordash;

import java.util.Arrays;

public class SudokuProblems {

    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] box = new boolean[9][9];
        int index;
        for(int i = 0; i < 9; i++) {
            int row_index = i / 3 * 3;
            for(int j = 0; j < 9; j++) {
                index = row_index + j / 3;
                if(board[i][j] == '.') continue;
                board[i][j] -= '1';
                row[i][board[i][j]] ^= true;
                col[j][board[i][j]] ^= true;
                box[index][board[i][j]] ^= true;
                if(!row[i][board[i][j]] || !col[j][board[i][j]] || !box[index][board[i][j]]) {
                    return false;
                }
            }
        }
        return true;
    }


    public boolean backtrack(int r, int c, char[][] board, int[] row_s, int[] col_s, int[] block_s) {
        if (r == 9 && c == 0) {
            return true;
        }
        int nr = c == 8 ? r + 1 : r;
        int nc = c == 8 ? 0 : c + 1;
        if (board[r][c] != '.') {
            return backtrack(nr, nc, board, row_s, col_s, block_s);
        }
        int block_id = r / 3 * 3 + c / 3;
        int s = row_s[r] | col_s[c] | block_s[block_id];
        for (int i = 1; i <= 9; i++) {
            if (((s >> i) & 1) == 0) {
                board[r][c] = (char)(i + '0');
                row_s[r] ^= (1 << i);
                col_s[c] ^= (1 << i);
                block_s[block_id] ^= (1 << i);
                if (backtrack(nr, nc, board, row_s, col_s, block_s)) {
                    return true;
                }
                board[r][c] = '.';
                row_s[r] ^= (1 << i);
                col_s[c] ^= (1 << i);
                block_s[block_id] ^= (1 << i);
            }
        }
        return false;
    }


    public void solveSudoku(char[][] board) {
        int[] row_s = new int[9];
        int[] col_s = new int[9];
        int[] block_s = new int[9];
        Arrays.fill(row_s, 0);
        Arrays.fill(col_s, 0);
        Arrays.fill(block_s, 0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int v = board[i][j] - '0';
                    row_s[i] ^= (1 << v);
                    col_s[j] ^= (1 << v);
                    int block_id = i / 3 * 3 + j / 3;
                    block_s[block_id] ^= (1 << v);
                }
            }
        }
        backtrack(0, 0, board, row_s, col_s, block_s);
    }
}
