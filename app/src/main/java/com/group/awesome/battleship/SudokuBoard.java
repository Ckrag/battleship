package com.group.awesome.battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jacob on 12/5/2017.
 */

public class SudokuBoard {
    public int grid[][];

    public SudokuBoard(){
        this.grid = new int[9][9];
    }

    public SudokuBoard copy(){
        SudokuBoard board = new SudokuBoard();

        // Ensure we get a deep copy
        for (int i = 0; i < board.grid.length; i++) {
            board.grid[i] = grid[i].clone();
        }
        return board;
    }

    public void createBoard(int revealed){
        this.grid = new int[9][9]; // empty board
        SudokuSolver solver = new SudokuSolver();
        solver.solve(this,true,0,0);
        // remove until desired revealed amount. (note that multiple solutions will be possible.

        while (getCount() > revealed){
            int ran = new Random().nextInt(80) + 1;
            int x = ran%9;
            int y = ran/9;
            setVal(x,y,0);
        }
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

    public int getCount(){
        int n = 0;
        for (int row[] : grid){
            for (int i : row){
                if (i > 0 ){
                    n++;
                }
            }
        }
        return n;
    }

    public boolean isBoardValid(){
        for (int y = 0; y < 9; y++){
            for (int x = 0; x < 9; x++){
                if (!isValidPlace(x,y)){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidPlace(int x, int y){

        boolean col = isValidCol(x);
        boolean row = isValidRow(y);
        boolean square = isValidSquare(x,y);

        return col && row && square;
    }

    boolean isValidRow(int y){
        List<Integer> count = new ArrayList<>();
        for (int i : this.grid[y]){
            if (!count.contains(i)){
                if (i != 0){ count.add(i); }
            } else {
                return false;
            }
        }
        return true;
    }

    boolean isValidCol(int x){
        List<Integer> count = new ArrayList<>();
        for (int row[] : this.grid){
            if (!count.contains(row[x])){
                if (row[x] != 0){ count.add(row[x]); }
            } else {
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

    boolean isValidSquare(int x, int y){
        List<Integer> count = new ArrayList<>();
        int sqCol = x/3;
        int sqRow = y/3;
        // search section of grid
        for (int j = sqRow*3; j < sqRow*3 + 3; j++){
            for (int k = sqCol*3; k < sqCol*3 + 3; k++){
                int n = getVal(k,j); // k is from col and taken from x value above.
                if (!count.contains(n)){
                    if (n != 0){ count.add(n); }
                } else {
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
