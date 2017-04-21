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
public class Point extends Winnable {
    private char winner;
    private boolean isWon;
    public Point() //Constructor for Point on simple board
    {
        super(1);
        this.winner='z';
        this.isWon=false;
    }
    
    @Override
    public char winner() //returns who won, or z if none
    {
        return this.winner;
    }
    @Override
    public boolean isFinished() //self explanatory
    {
        return this.isWon;
    }
    @Override
    public boolean play(boolean isX)
    {
        if(isX)
            this.winner='x';
        else
            this.winner='o';
        isWon=true;
        return super.play();
    }
    @Override
    public Winnable access(int row, int col)
    {
    return this.access();
    }
    public Winnable access()
    {
        return this;
    }
    @Override
    public String toString()
    {
        return Character.toString(winner);
    }

    public Point copy() {
        Point p = new Point();
        p.isWon = this.isWon;
        p.winner = this.winner;
        return p;
    }
    
    
}
