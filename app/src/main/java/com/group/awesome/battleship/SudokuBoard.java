package com.group.awesome.battleship;

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

        boolean col = isValidCol(x,num);
        boolean row = isValidRow(y,num);
        boolean square = isValidSquare(x,y,num);
        boolean freeSpot = getVal(x,y) == 0;

        return col && row && square && freeSpot;
    }

    boolean isValidRow(int y, int num){
        for (int i : this.grid[y]){
            if (i == num){
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

    public void setVal(int x, int y, int val){
        grid[y][x] = val;
    }

    public int getVal(int x, int y){
        return grid[y][x];
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

    @Override
    public String toString() {
        String result = "";

        for (int[] aGrid : grid) {
            for (int i = 0; i < aGrid.length; i++) {
                result += aGrid[i] + ",";
            }
            result += "\n";
        }

        return result;
    }
}
