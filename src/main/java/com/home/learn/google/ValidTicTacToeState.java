package com.home.learn.google;

public class ValidTicTacToeState {
    public boolean validTicTacToe(String[] board) {
        /*
            Check column if there are 3 of the samme
            Check row if there are 3 of the same
            Check diagonals
            Check if the correct number of X's and O's are placed
        */

        char[][] grid = new char[3][3];
        for(int i = 0; i < board.length; i++)
            grid[i] = board[i].toCharArray();

        int[] col = new int[3];
        int[] row = new int[3];
        int xMoves = 0;
        int oMoves = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(grid[i][j] == 'X'){
                    row[i]++;
                    xMoves++;
                }
                else if(grid[i][j] == 'O'){
                    row[i]--;
                    oMoves++;
                }

                if(grid[j][i] == 'X')
                    col[i]++;
                else if(grid[j][i] == 'O')
                    col[i]--;
            }
        }


        int numberOfWinsX = 0;
        int numberOfWinsO = 0;

        // diagonal
        int diagonal = 0;
        for(int i = 0; i < 3; i++){
            if(grid[i][i] == 'X')
                diagonal++;
            else if(grid[i][i] == 'O')
                diagonal--;
        }
        if(diagonal == 3) numberOfWinsX++;
        if(diagonal == -3) numberOfWinsO++;

        // anti diagonal
        int antiDiagonal = 0;
        for(int i = 0; i < 3; i++){
            if(grid[i][2 - i] == 'X')
                antiDiagonal++;
            else if(grid[i][2 - i] == 'O')
                antiDiagonal--;
        }

        if(antiDiagonal == 3) numberOfWinsX++;
        if(antiDiagonal == -3) numberOfWinsO++;

        for(int i = 0; i < 3; i++){
            if(row[i] == 3) numberOfWinsX++;
            if(row[i] == -3) numberOfWinsO++;

            if(col[i] == 3) numberOfWinsX++;
            if(col[i] == -3) numberOfWinsO++;
        }

        if(oMoves > xMoves) return false;
        if(xMoves == 0 && oMoves > 0) return false;

        int moves = Math.abs(xMoves - oMoves);
        if(moves != 0 && moves != 1) return false;

        if(numberOfWinsX >= 1 && numberOfWinsO >= 1) return false;
        else {
            if(numberOfWinsX >= 1 && moves == 0) return false;
            return numberOfWinsO < 1 || moves != 1;
        }
    }
}
