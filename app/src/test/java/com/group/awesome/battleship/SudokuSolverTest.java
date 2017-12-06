package com.group.awesome.battleship;

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
        Assert.assertTrue(solver.solve(board,0,0));
        board.createBoard(18);
        board.setVal(8,8,5);
        board.setVal(8,7,5);
        Assert.assertFalse(solver.solve(board,0,0));
    }
}
