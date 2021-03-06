package com.home.learn.uber;

public class Minesweeper {
    private static final int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 },
            { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 } };

    public char[][] updateBoard(char[][] board, int[] click) {
        int i = click[0];
        int j = click[1];
        if (board[i][j] == 'M') {
            board[i][j] = 'X';
        } else {
            revealEmptySpace(board, i, j);
        }
        return board;
    }

    private void revealEmptySpace(char[][] board, int i, int j) {
        if (!isValidCell(board, i, j) || (board[i][j] != 'E')) {
            return;
        }
        board[i][j] = getResult(board, i, j);
        if(board[i][j] == 'B') {
            for (int[] dir : dirs) {
                revealEmptySpace(board, i + dir[0], j + dir[1]);
            }
        }
    }

    private char getResult(char[][] board, int i, int j) {
        int mines = 0;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (isValidCell(board, x, y) && board[x][y] == 'M') {
                mines++;
            }
        }
        return mines == 0 ? 'B' : (char) ('0' + mines);
    }

    private boolean isValidCell(char[][] board, int i, int j) {
        return i >= 0 && i < board.length && j >= 0 && j < board[i].length;
    }
}
