package com.group.awesome.sudoku;

import java.util.Random;

/**
 * Created by Jacob on 12/4/2017.
 */

public class SudokuSolver {

    public int solutions;

    public boolean hasUniqueSolution(SudokuBoard board){
        this.solutions = 0;
        _hasUniqueSolution(board,0,0);
        return this.solutions == 1;
    }

    private boolean _hasUniqueSolution(SudokuBoard board,int x,int y){
        if (this.solutions > 1){
            return false;
        }

        if (board.isFull()){
            ++this.solutions;
            return false;
        }

        int nx = x < 8 ? x + 1 : 0; // next row if value is 8
        int ny = nx == 0 ? y + 1 : y; // if we're in col 0 that means we just changed row.

        if (board.getVal(x,y) != 0){
            return _hasUniqueSolution(board,nx,ny);
        }

        for (int n = 1; n < 10; n++){
            board.setVal(x,y,n);
            if(board.isValidPlace(x, y)){ // was the move valid?
                board.setVal(x,y,n);
                if(_hasUniqueSolution(board,nx,ny)) {
                    return true;
                }
                board.setVal(x,y,0);
            } else { // move was not valid.
                board.setVal(x,y,0);
            }
        }


        return false;
    }


    public boolean solve(SudokuBoard board,boolean random, int x,int y){

        if (board.isFull()){
            return true;
        }

        int nx = x < 8 ? x + 1 : 0; // next row if value is 8
        int ny = nx == 0 ? y + 1 : y; // if we're in col 0 that means we just changed row.

        if (board.getVal(x,y) != 0){
            return solve(board,random,nx,ny);
        }

        // add randomness here for generators.
        int numbers[] = new int[] {1,2,3,4,5,6,7,8,9};
        if (random){ shuffleArray(numbers); }
        for (int n : numbers){
            board.setVal(x,y,n);
            if(board.isValidPlace(x, y)){ // was the move valid?
                if(solve(board,true,nx,ny)) {
                    return true;
                }
                board.setVal(x,y,0);
            } else { // move was not valid.
                board.setVal(x,y,0);
            }
        }


        return false;
    }

    private static void shuffleArray(int[] ar)
    {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }


}