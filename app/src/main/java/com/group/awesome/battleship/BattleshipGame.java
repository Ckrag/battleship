package com.group.awesome.battleship;

/**
 * Created by Jacob on 12/4/2017.
 */

public class BattleshipGame {

    // 0 - empty not hit
    // 1 - empty hit
    // 2 - ship not hit
    // 3 - ship hit
    public int grid1[][];
    public int grid2[][];

    public BattleshipGame(){
        //not sure if this needs anything.
    }

    /**
     * Returns the player number of the winner or 0 if no winner has been found.
     * @return player winner
     */
    public int getWinner(){
        return 0;
    }



    /**
     * Returns true if a winner has been found
     * @return winner found
     */
    public boolean hasWinner(){
        return getWinner() != 0;
    }

    /**
     * Fire at a position of a player's grid.
     * @param player being fired at
     * @param coords position on grid
     */
    public void fireAtPos(int player,XYCoordinate coords){

    }

    /**
     * Place a ship on position of a player's grid.
     * @param player placing ship
     * @param coords coordinates ship is taking
     */
    public void addShip(int player, XYCoordinate coords[]){

    }

    /**
     * Returns true if ship is valid shape and on board
     * @param coords coordinates of ship
     * @return is valid.
     */
    public boolean isShipValid(XYCoordinate coords[]){
        // is ship built in a valid way?
        return false;
    }



}