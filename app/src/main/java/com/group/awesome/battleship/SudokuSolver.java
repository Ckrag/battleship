package com.group.awesome.battleship;

/**
 * Created by Jacob on 12/4/2017.
 */

public class SudokuSolver {

    SudokuBoard board;

    public SudokuSolver(SudokuBoard board){
        this.board = board;
    }

    public boolean solve(SudokuBoard board){
        if (board.isFull()){
            return true;
        }

        for (int i = 0; i < board.grid.length; i++) {
            for (int j = 0; j < board.grid[i].length; j++) {
                for (int k = 1; k < 9; k++) {
                    if(board.isValidPlace(i, j, k)){
                        board.grid[i][j] = k;
                        if(solve(board)){
                            return true;
                        } else {
                            board.grid[i][j] = 0;
                        }
                    }
                }
            }
        }
        return false;
    }




}