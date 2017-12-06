package com.group.awesome.battleship;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Jacob on 12/5/2017.
 */

public class SudokuBoardTest {

    @Test
    public void testIsValidPlace(){
        SudokuBoard board = new SudokuBoard();
        Assert.assertFalse(board.isFull());
        board.grid = new int[][] {
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9}
    };
        Assert.assertTrue(board.isFull());
        //board.grid[1][4] = 0;
        board.setVal(4,1,0);
        Assert.assertTrue(board.isValidRow(1));
        //board.grid[0][0] = 0;
        board.setVal(0,0,0);
        Assert.assertFalse(board.isValidCol(0));
        board.grid = new int[][] {
                {0,0,3,4,5,6,0,8,9},
                {2,2,3,4,5,6,7,8,9},
                {3,2,3,4,5,6,7,8,9},
                {4,2,3,4,5,6,7,8,9},
                {5,2,3,4,5,6,7,8,9},
                {6,2,3,4,5,6,7,8,9},
                {7,2,3,4,5,6,7,8,9},
                {8,2,3,4,5,6,7,8,9},
                {9,2,3,4,5,6,7,8,9}
        };
        Assert.assertTrue(board.isValidCol(0));
        Assert.assertFalse(board.isValidSquare(0,0)); // no other ones in square, should be valid.
        //board.grid[0][1] = 1;
        board.setVal(1,0,1);
        Assert.assertFalse(board.isValidSquare(0,0));
        //board.grid[0][6] = 0;
        board.setVal(6,0,0);
        //board.grid[0][1] = 0;
        board.setVal(1,0,0);

        //System.out.println("val: " + board.getVal(1, 0));
        Assert.assertEquals(board.getVal(1,0),0);
        board.createBoard(12);
        Assert.assertTrue(board.isValidPlace(1,1)); // Check if this position is valid
        Assert.assertEquals(board.getCount(),12);
        //System.out.println(board);

    }
}
