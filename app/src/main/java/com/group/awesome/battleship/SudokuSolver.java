package com.group.awesome.battleship;

import java.util.Random;

/**
 * Created by Jacob on 12/4/2017.
 */

public class SudokuSolver {

    SudokuBoard board;

    public SudokuSolver(SudokuBoard board){
        this.board = board;
    }

    public boolean solve(SudokuBoard board,int x,int y){
        if (board.isFull()){
            return true;
        }

        // add randomness here for generators.
        int numbers[] = new int[] {1,2,3,4,5,6,7,8,9};
        shuffleArray(numbers);
        for (int n : numbers){
            if(board.isValidPlace(x, y, n)){
                board.setVal(x,y,n);
                int nx = x < 8 ? x + 1 : 0; // next row if value is 8
                int ny = nx == 0 ? y + 1 : y; // if we're in col 0 that means we just changed row.
                if(solve(board,nx,ny)) {
                    return true;
                }
                board.setVal(x,y,0);
            }
        }


        return false;
    }

    static void shuffleArray(int[] ar)
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