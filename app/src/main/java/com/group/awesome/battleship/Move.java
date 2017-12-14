package com.group.awesome.battleship;

/**
 * Created by root on 12/14/17.
 */

public class Move {

    public final int x;
    public final int y;
    public final int oldVal;

    public Move(int x, int y, int oldVal){
        this.x = x;
        this.y = y;
        this.oldVal = oldVal;
    }
}
