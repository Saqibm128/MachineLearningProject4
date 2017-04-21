/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * class is abstract so that it can never be instantiated alone
 * @author saqib
 */
public class Winnable {
    int number;
    Winnable[][] cells;

    /**
     *
     * @param num represents how big a Winnable should be (recursively defined)
     */
    public Winnable(int num) //default constructor for winnable
    {
        number=num;
        cells= new Winnable[num][num];
    }

    /**
     * Tells who the winner is for this winnable
     * @return x or o for winner or z if there is none
     */
    public char winner()
    {
       for(int row=0; row<number; row++) //checks rows to see if possible winner
       {
           char possible=cells[row][0].winner();
           boolean isWinner=true;//will turn false if one row fails test
           for(int col=0; col<number; col++)
           {
               if (possible!=cells[row][col].winner())
                   isWinner=false;
           }
           if(isWinner && possible != 'z')
               return possible;
       }
       
       for(int col=0; col<number; col++) //checks cols to see if possible winner
       {
           if (this instanceof UltimateBoard)
               ;
           char possible=cells[0][col].winner();
           boolean isWinner=true;//will turn false if one cols fails test
           for(int row=0; row<number; row++)
           {
               int a;
               if (this instanceof UltimateBoard)
                   a = 6;
               if (possible!=cells[row][col].winner())
                   isWinner=false;
           }
           if(possible!='z' && isWinner)
               return possible;
       }
       char possible=cells[0][0].winner();
       boolean isWinner=true;
       for (int row=0; row<number; row++)
       {
           if (possible!= cells[row][row].winner())
               isWinner=false;
           
       }
       if (possible!='z' && isWinner)
           return possible;
       
       possible=cells[number-1][0].winner();
       isWinner=true;
       
       for (int row=0; row<number; row++)
       {
           if (possible!= cells[number-row-1][row].winner())
               isWinner=false;
       }
       if (possible!='z' && isWinner)
           return possible;
       
        
                
       return 'z';      
    }

    /**Is the game over
     *
     * @return true if it is over
     */
    public boolean isFinished() // to ensure that winnable object can still be used
    {
        return winner()!='z';
    }
    
    public boolean play(boolean a)
    {
        return this.play();
    }
    public boolean play(boolean a, int row, int col)
    {
        return this.play();
    }
    public boolean play() //to call up to see if play is possible in child classes
    {
        if (isFinished())
            return false;
        else
            return true;
    }
    public Winnable access(int row, int col) //to access a winnable object
    {
     return cells[row][col];
    }
    
    public void replace(Winnable newPiece, int row, int col)
    {
       cells[row][col]=newPiece;
    }
    
    public int getNum()
    {
        return number;
    }
    public String toString(int num)
    {
     return this.toString();
    }
    public String toString()//presents graphic of board
    {
        String temp="";
        for (int row=0; row<number; row++)
        {
            for (int col=0; col<number; col++)
            {
                temp +=(cells[row][col].toString());
            }
        temp+="\n";
        }
        return temp;
    }
    
    }
    
    

