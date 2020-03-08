package com.home.learn.wish;

import java.util.*;

public class NQueens {
    public int totalNQueens(int n) {
        Set<Integer> occupiedCols = new HashSet<>();
        Set<Integer> occupiedDiag1s = new HashSet<>();
        Set<Integer> occupiedDiag2s = new HashSet<>();
        int[] count = {0};
        backtrack(0, count, n, occupiedCols, occupiedDiag1s, occupiedDiag2s);
        return count[0];
    }

    private void backtrack(int row, int[] count, int n,
                           Set<Integer> occupiedCols,
                           Set<Integer> occupiedDiag1s,
                           Set<Integer> occupiedDiag2s) {
        for (int col = 0; col < n; col++) {
            int diag1 = row - col;
            int diag2 = row + col;
            if (occupiedCols.contains(col)) continue;
            if (occupiedDiag1s.contains(diag1)) continue;
            if (occupiedDiag2s.contains(diag2)) continue;
            if (row == n - 1) {
                ++count[0];
                return;
            } else {
                occupiedCols.add(col);
                occupiedDiag1s.add(diag1);
                occupiedDiag2s.add(diag2);
                backtrack(row + 1, count, n,
                        occupiedCols, occupiedDiag1s, occupiedDiag2s);
                // recover
                occupiedCols.remove(col);
                occupiedDiag1s.remove(diag1);
                occupiedDiag2s.remove(diag2);
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        boolean[] visited = new boolean[n];
        //2*n-1个斜对角线
        boolean[] dia1 = new boolean[2*n-1];
        boolean[] dia2 = new boolean[2*n-1];
        List<List<String>> result = new ArrayList<>();
        dfs(result, n, new ArrayList<String>(),visited,dia1,dia2,0);
        return result;
    }

    private void dfs(List<List<String>> result, int n,List<String> list,boolean[] visited,boolean[] dia1,boolean[] dia2,int rowIndex){
        if(rowIndex == n){
            result.add(new ArrayList<String>(list));
            return;
        }
        for(int i=0;i<n;i++){
            //这一行、正对角线、反对角线都不能再放了，如果发现是true，停止本次循环
            if(visited[i] || dia1[rowIndex+i] || dia2[rowIndex-i+n-1])
                continue;
            //init一个长度为n的一维数组，里面初始化为'.'
            char[] charArray = new char[n];
            Arrays.fill(charArray,'.');
            charArray[i] = 'Q';
            String stringArray = new String(charArray);
            list.add(stringArray);
            visited[i] = true;
            dia1[rowIndex+i] = true;
            dia2[rowIndex-i+n-1] = true;
            dfs(result, n,list,visited,dia1,dia2,rowIndex+1);
            //reset 不影响回溯的下个目标
            list.remove(list.size()-1);
            charArray[i] = '.';
            visited[i] = false;
            dia1[rowIndex+i] = false;
            dia2[rowIndex-i+n-1] = false;
        }
    }
}
