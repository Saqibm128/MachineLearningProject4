/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.util.*;
/**
 *
 * @author saqib
 */
public class UltimateBoard <U extends Winnable> extends Winnable{
    public UltimateBoard(int num)
    {
        super(num);
        for(int row=0; row<num; row++)
            for(int col=0; col<num; col++)
            {
                this.replace(new NormalBoard(num), row, col);
            }
    }
    public UltimateBoard(int num, U[][] boards){
        super(num);
        for(int row=0; row<num; row++)
            for(int col=0; col<num; col++)
            {
                this.replace(boards[row][col], row, col);
            }
    }

    public boolean play(boolean isX, int superRow, int superCol, int row, int col)//play a move
    {
       return this.access(superRow, superCol).play(isX,row,col);
    }
    @Override
    public String toString() //uses overriden method to create an ultimate board
    {
        String temp="";
        for(int superrow=0; superrow<this.getNum(); superrow++)
        {
            
            for(int row=0; row<this.getNum(); row++)
            {
                for(int supercol=0; supercol<this.getNum(); supercol++)
                    temp+=this.access(superrow,supercol).toString(row)+"\t";
                temp+="\n";
            }
            temp+="\n\n";
            
            
        }
        return temp;
                    
    }
    
    public List<Winnable> superAccess()
    {
        List<Winnable> temp= new ArrayList<>();
        for(int superrow=0; superrow<this.getNum(); superrow++)
        {
            
            for(int row=0; row<this.getNum(); row++)
            {
                for(int supercol=0; supercol<this.getNum(); supercol++)
                    for(int col=0; col<this.getNum(); col++)
                    temp.add(this.access(superrow,supercol).access(row, col));
                
            }
            
            
            
        }
        return temp;
    }
    }

