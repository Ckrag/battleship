package com.group.awesome.sudoku;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Jacob on 05-12-17.
 */

public class SudokuSolverTest {

    @Test
    public void testSolver(){
        SudokuBoard board = new SudokuBoard();
        SudokuSolver solver = new SudokuSolver();
        board.createBoard(17);
        Assert.assertTrue(solver.solve(board,false,0,0));
        board.createBoard(80);
        Assert.assertTrue(solver.hasUniqueSolution(board));

        board.createBoard(32);
        board.setVal(8,8,5);
        board.setVal(8,7,5);
        Assert.assertFalse(solver.solve(board,false,0,0));
        //Assert.assertFalse(solver.solve(board,false,0,0));
        board.createBoard(32);
        solver.solve(board,false,0,0);
        Assert.assertTrue(board.isFull());
        Assert.assertTrue(board.isBoardValid());
    }
}
