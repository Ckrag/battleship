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
    public int size;

    public BattleshipGame(int size){
        this.grid1 = new int[size][size];
        this.grid2 = new int[size][size];
        this.size = size;
        //not sure if this needs anything.
    }


    /**
     * Returns true if the player has won
     * @param player to check
     * @return won
     */
    public boolean hasWon(int player){
        int grid[][] = player == 1 ? grid1 : grid2;
        for (int row[] : grid){
            for (int pos : row){
                if (pos == 2){
                    return false;
                }
            }
        }
        return true;
    }



    /**
     * Returns true if a winner has been found
     * @return winner found
     */
    public boolean foundWinner(){
        return (hasWon(1) || hasWon(2));
    }

    /**
     * Fire at a position of a player's grid.
     * @param player being fired at
     * @param coords position on grid
     * @return new PosState
     */
    public int fireAtPos(int player,XYCoordinate coords){
        int grid[][] = player == 1 ? grid1 : grid2;
        int posState = grid[coords.x][coords.y];
        switch (posState) {
            case 0:
                grid[coords.x][coords.y] = 1;
                break;
            case 1:
                throw new Error("Already hit");
            case 2:
                grid[coords.x][coords.y] = 3;
                break;
            case 3:
                throw new Error("Already hit");
            default:
                throw new Error("PosState not valid - " + posState);
        }
        return posState;
    }

    /**
     * Place a ship on position of a player's grid.
     * @param player placing ship
     * @param coords coordinates ship is taking
     */
    public void addShip(int player, int shipsize, XYCoordinate coords[]){
        int grid[][] = player == 1 ? grid1 : grid2;
        if (!isShipValid(shipsize, coords)){
            throw new IllegalArgumentException("Ship is not valid.");
        }
        for (XYCoordinate coord : coords){
            grid[coord.x][coord.y] = 2;
        }
    }

    /**
     * Returns true if ship is valid shape and on board
     * @param coords coordinates of ship
     * @return is valid.
     */
    public boolean isShipValid(int size, XYCoordinate coords[]){
        // is ship built in a valid way?
        if (coords.length > size){
            throw new IllegalArgumentException("Ship is too large.");
        } else if (coords.length < size){
            throw new IllegalArgumentException("Ship is too small.");
        }

        // compare all coordinates with the first to check if ship is horizontal or vertical
        boolean isHoriz = true;
        boolean isVert = true;
        XYCoordinate pos = coords[0];
        for (XYCoordinate coord : coords){
            if (coord.x != pos.x){
                isVert = false;
            }
            if (coord.y != pos.y){
                isHoriz = false;
            }
        }

        // ship is invalid if neither are true.
        if (!isHoriz && !isVert){
            throw new IllegalArgumentException("Ship must be horizontal or vertical.");
        }

        //check if points are all adjacent by comparing distance between outer points.
        int low = size;
        int high = 0;
        if (isHoriz){
            for (XYCoordinate coord : coords){
                if (coord.x < low){
                    low = coord.x;
                }
                if (coord.x > high){
                    high = coord.x;
                }
            }
        } else { // vertical
            for (XYCoordinate coord : coords){
                if (coord.y < low){
                    low = coord.y;
                }
                if (coord.y > high){
                    high = coord.y;
                }
            }
        }

        if ((high-low) != (size-1)){
            throw new IllegalArgumentException("Coordinates are no adjacent.");
        }

        // ship is valid
        return true;
    }



}