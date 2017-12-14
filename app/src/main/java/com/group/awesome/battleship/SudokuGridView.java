package com.group.awesome.battleship;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;

/**
 * Created by root on 12/6/17.
 */

public class SudokuGridView extends GridLayout implements ISudokuBoard {

    public static final int SIZE = 9;

    public SudokuGridView(Context context, AttributeSet attrs) {
        super(context, attrs);

        addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                view.removeOnLayoutChangeListener(this);

                SudokuGridView.this.setColumnCount(SIZE);
                SudokuGridView.this.setRowCount(SIZE);
            }
        });
    }

    private SudokuBoard board = new SudokuBoard();

    /**
     * We keep a copy of the initial version to check if the user is allowed to change a number,
     * since we don't allow changing the initial numbers
     */
    private SudokuBoard originalBoard;

    @Override
    public void createBoard(int revealed) {
        board.createBoard(revealed);
        originalBoard = board.copy();

        for (int i = 0; i < board.grid.length; i++) {
            for (int j = 0; j < board.grid[i].length; j++) {
                int val = board.getVal(i, j);
                setVal(i, j, val);
                if(val > 0) getViewAtPos(i, j).setBackground(ContextCompat.getDrawable(getContext(), R.drawable.btn_background_locked));
            }
        }
    }

    /**
     *
     * @param x
     * @param y
     * @return true if the initial board doesn't have a value
     */
    public boolean isChangable(int x, int y) {
        return originalBoard != null && originalBoard.getVal(x, y) == 0;
    }

    public void setSelected(int x, int y){
        getViewAtPos(x, y).setBackground(ContextCompat.getDrawable(getContext(), R.drawable.btn_background_select));
    }

    public void setNotSelected(int x, int y){
        getViewAtPos(x, y).setBackground(ContextCompat.getDrawable(getContext(), R.drawable.btn_background));
    }

    @Override
    public boolean isFull() {
        return board.isFull();
    }

    @Override
    public int getCount() {
        return board.getCount();
    }

    @Override
    public boolean isBoardValid() {
        return board.isBoardValid();
    }

    @Override
    public boolean isValidPlace() {
        return false;
    }

    @Override
    public void setVal(int x, int y, int val){
        board.setVal(x, y, val);

        if(val == 0){
            setViewValue(x, y, "");
        } else {
            setViewValue(x, y, val);
        }

    }

    private void setViewValue(int x, int y, int val) {
        setViewValue(x, y, String.valueOf(val));
    }

    private void setViewValue(int x, int y, String val){
        ((Button) getViewAtPos(x, y)).setText(val);
    }

    private View getViewAtPos(int x, int y){
        return getChildAt((y*9) + x);
    }

    public void refreshBoard(){
        for (int i = 0; i < board.grid.length; i++) {
            for (int j = 0; j < board.grid[0].length; j++) {
                setVal(i,j, board.getVal(i,j));
            }
        }
    }

    @Override
    public int getVal(int x, int y) {
        return board.getVal(x,y);
    }

    @Override
    public SudokuBoard getBoard() {
        return board;
    }
}
