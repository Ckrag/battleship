package com.group.awesome.battleship;

import java.util.Arrays;

/**
 * Created by Jacob on 12/5/2017.
 */

public class SudokuBoard {
    public int grid[][];

    public SudokuBoard(){
        this.grid = new int[9][9];
    }

    public boolean isFull(){
        for (int row[] : grid){
            for (int i : row){
                if (i == 0 ){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidPlace(int x, int y,int num){
        return isValidCol(x,num) && isValidRow(y,num) && isValidSquare(x,y,num);
    }

    boolean isValidRow(int y, int num){
        for (int i : this.grid[y]){
            if (i == num){
                System.out.println(i);
                return false;
            }
        }
        return true;
    }

    boolean isValidCol(int x, int num){
        for (int row[] : this.grid){
            if (row[x] == num){
                return false;
            }
        }
        return true;
    }

    boolean isValidSquare(int x, int y, int num){
        int sqRow = x/3;
        int sqCol = y/3;
        // search section of grid
        for (int j = sqRow*3; j < sqRow+3; j++){
            for (int k = sqCol*3; k < sqCol+3; k++){
                if (num == this.grid[j][k]){
                    return false;
                }
            }
        }
        return true;
    }
}
