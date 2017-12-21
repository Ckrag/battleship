package com.group.awesome.sudoku;

/**
 * Created by root on 12/6/17.
 */

public interface ISudokuBoard {

    public void createBoard(int revealed);

    public boolean isFull();

    public int getCount();

    public boolean isBoardValid();

    public boolean isValidPlace();

    public void setVal(int x, int y, int val);

    public int getVal(int x, int y);

    public SudokuBoard getBoard();
}
