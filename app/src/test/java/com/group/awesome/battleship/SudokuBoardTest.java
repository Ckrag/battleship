package com.group.awesome.battleship;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Jacob on 12/5/2017.
 */

public class SudokuBoardTest {

    @Test
    public void testBoardClass() {
        SudokuBoard board = new SudokuBoard();
        Assert.assertFalse(board.isFull());
        board.createBoard(81);
        Assert.assertTrue(board.isFull());
        Assert.assertTrue(board.isBoardValid());
    }

    @Test
    public void testIsValidChecks() {
        SudokuBoard board = new SudokuBoard();
        board.createBoard(81);
        Assert.assertTrue(board.isFull());
        Assert.assertTrue(board.isValidRow(5));
        Assert.assertTrue(board.isValidCol(5));
        Assert.assertTrue(board.isValidSquare(5,5));
        Assert.assertTrue(board.isValidPlace(5,5));
        board.grid = new int[][]{
                {1, 0, 3, 4, 5, 6, 0, 8, 9},
                {2, 2, 3, 4, 5, 6, 7, 8, 9},
                {3, 2, 3, 4, 5, 6, 7, 8, 9},
                {4, 2, 3, 4, 5, 6, 7, 8, 9},
                {5, 2, 3, 4, 5, 6, 7, 8, 9},
                {6, 2, 3, 4, 5, 6, 7, 8, 9},
                {7, 2, 3, 4, 5, 6, 7, 8, 9},
                {8, 2, 3, 4, 5, 6, 7, 8, 9},
                {9, 2, 3, 4, 5, 6, 7, 8, 9}
        };
        Assert.assertTrue(board.isValidCol(0));
        Assert.assertFalse(board.isValidSquare(0, 0));
        board.setVal(1, 0, 1);
        Assert.assertFalse(board.isValidSquare(0, 0));
        board.setVal(6, 0, 0);
        board.setVal(1, 0, 0);

        Assert.assertEquals(board.getVal(1, 0), 0);
        board.createBoard(17);
        Assert.assertTrue(board.isValidPlace(1, 1)); // Check if this position is valid
    }

    @Test
    public void testIsBoardValid() {
        SudokuBoard board = new SudokuBoard();
        board.createBoard(17);
        Assert.assertEquals(board.getCount(),17);
        Assert.assertTrue(board.isBoardValid());
        // make invalid moves.
        board.setVal(8,8,1);
        board.setVal(8,7,1);
        Assert.assertFalse(board.isBoardValid());
        SudokuSolver sol = new SudokuSolver();
        Assert.assertFalse(sol.solve(board,false,0,0));
    }

    @Test
    public void testUniqueSudokus(){
        SudokuBoard board = new SudokuBoard();
        SudokuSolver solver = new SudokuSolver();
        int results[] = new int[81];
        for (int n=1;n<6;n++) {
            for (int i = 17; i < 81; i++) {
                board.createBoard(i);
                if (solver.hasUniqueSolution(board)){
                    results[i]++;
                }

            }
            if (n%5 == 0){
                for (int i = 0; i<results.length; i++){
                    System.out.println("" + i + " - " + results[i]);
                }
            }
        }
    }

    @Test
    public void testUniqueAtNum(){
        int num = 37;
        SudokuBoard board = new SudokuBoard();
        SudokuSolver solver = new SudokuSolver();
        int tries = 0;
        while (true){
            System.out.println(++tries);
            board.createBoard(num);
            if (solver.hasUniqueSolution(board)){
                break;
            }
        }
        System.out.println("DONE");
        System.out.println(board);
    }

}
