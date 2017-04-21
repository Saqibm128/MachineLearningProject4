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
}
  




