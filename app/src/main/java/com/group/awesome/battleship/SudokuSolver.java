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



        return false;
    }




}