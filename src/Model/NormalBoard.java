/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


/**
 *
 * @author saqib
 */
public class NormalBoard<U extends Winnable> extends Winnable {
    
    public NormalBoard(int num)//initialize a board with pieces
    {
        super(num);
        for(int row=0; row<num; row++)
            for(int col=0; col<num; col++)
            {
                this.replace(new Point(), row, col);
            }
    }
    public NormalBoard(int num, U[][] replacement) {
        super(num);
        for(int row=0; row<num; row++)
            for(int col=0; col<num; col++)
            {
                this.replace(replacement[row][col], row, col);
            }
    }
    @Override
    public boolean play(boolean isX, int row, int col)//play a move
    {
        if (this.access(row, col).winner() != 'z') {
            return false;
        }
        this.access(row, col).play(isX);
        return true;
    }
   
    public String toString(int row) //this is necessary to properly format ultimate board
    {
        String temp="";
        for(int col=0; col<this.getNum(); col++)
            temp+=this.access(row, col).toString();
        return temp;
    }

    public NormalBoard<U> copy() {
        NormalBoard<U> board = new NormalBoard<U>(this.getNum());
        for(int row = 0; row < this.getNum(); row++)
            for (int col = 0; col < this.getNum(); col++) {
                Point p = (Point) this.cells[row][col];
                board.cells[row][col]  = p.copy();
            }
        return board;
    }
    public boolean isFinished() {
        if (super.isFinished()) {
            return true;
        }
        for (int row = 0; row < this.getNum(); row++) {
            for (int col = 0; col < this.getNum(); col++) {
                if (!access(row, col).isFinished()) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] availableSpots() {
        int numAvailableSpots = 0;
        if (isFinished()) return new int[0][2];
        for (int row = 0 ; row < this.getNum(); row ++) {
            for (int col = 0; col < this.getNum(); col ++) {
                if (!this.cells[row][col].isFinished())
                    numAvailableSpots++;
            }
        }
        int toReturn[][] = new int[numAvailableSpots][2];
        int iter = 0;
        for (int row = 0 ; row < this.getNum(); row ++) {
            for (int col = 0; col < this.getNum(); col ++) {
                if (!this.cells[row][col].isFinished()) {
                    toReturn[iter][0] = row;
                    toReturn[iter][1] = col;
                    iter++;
                }
            }
        }
        return  toReturn;
    }


    @Override
    public int hashCode() {
        int hash = 29;
        if (this.isFinished()) {
            if (winner() == 'x') {
                return 1;
            } else if (winner() == 'o') {
                return -1;
            } else {
                return 0;
            }
        }
        for (int row = 0; row < getNum(); row ++) {
            for (int col = 0; col < getNum(); col ++) {
                if (this.cells[row][col].winner() == 'x') {
                    hash *= 17;
                    hash = hash << 1;
                    hash = hash ^ (hash >> 1);
                } else if (this.cells[row][col].winner() == 'o') {
                    hash *= 11;
                    hash = hash << 1;
                    hash = hash ^ (hash >> 1);
                } else {
                    hash = hash << 1;
                }
            }
        }
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof NormalBoard))
            return false;
        NormalBoard otherBoard = (NormalBoard) o;
        if (otherBoard.getNum() != this.getNum()) {
            return false;
        }
        if (this.isFinished() && otherBoard.isFinished() && this.winner() == otherBoard.winner()) {
            return true;
        } else if (this.isFinished() && otherBoard.isFinished()) {
            return false;
        }
        for (int row = 0; row < getNum(); row ++) {
            for (int col = 0; col < getNum(); col ++) {
                if (otherBoard.cells[row][col].winner()!= this.cells[row][col].winner()) {
                    return false;
                }
            }
        }
        return true;
    }
}
  




