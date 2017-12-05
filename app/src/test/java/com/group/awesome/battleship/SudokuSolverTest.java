package com.group.awesome.battleship;

import org.junit.Test;

/**
 * Created by Jacob on 05-12-17.
 */

public class SudokuSolverTest {
    @Test
    public void testSolver(){
        SudokuBoard board = new SudokuBoard();
        SudokuSolver solver = new SudokuSolver(board);
        solver.solve(board,0,0);
        System.out.println(board);
    }
}
