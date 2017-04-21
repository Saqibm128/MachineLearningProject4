/**
 * Created by Mohammed on 4/17/2017.
 */
import Model.UltimateBoard;

import java.util.Scanner;
public class CommandPromptGame {
    private static int keyNum=1;
    static boolean isX;
    static UltimateBoard board;
    static Scanner scan;
/**
 * @param args the command line arguments
 */

    public static void main(String[] args) {
        scan = new Scanner(System.in);
        int num,superRow, superCol, row, col;
        int[] temp;
        System.out.println("ULTIMATE TIC TAC TOE!!");
        System.out.println("Choose the size of board");
        num=scan.nextInt();
        keyNum = num;
        board = new UltimateBoard(keyNum);
        System.out.println(board);

        System.out.println("Player X: Choose the first superrow and first supercolumn to play");
        System.out.println("Enter the superrow");
        superRow=scan.nextInt()-1;
        System.out.println("Enter the supercolumn");
        superCol=scan.nextInt()-1;
        do
        {

            isX = !isX;
            temp=play(superRow, superCol);
            System.out.println(board);
            superRow=temp[0];
            superCol=temp[1];
        }
        while(!board.isFinished());
        if(board.winner()=='x')
            System.out.println("Player X has won.");
        else if(board.winner()=='o')
            System.out.println("Player O has won.");
        else
            System.out.println("Catscratch!");


    }
    public static int[] play(int superRow, int superCol)
    {

        int row=0;
        int col=0;
        if(isX)
            System.out.println("Player X's turn:");
        else
            System.out.println("Player O's turn:");


        while(board.access(superRow,superCol).isFinished())
        {
        System.out.println("This board is already finished. Enter in another superrow and supercolumn");
        System.out.println("Enter the superrow");
        superRow=scan.nextInt()-1;
        System.out.println("Enter the supercolumn");
        superCol=scan.nextInt()-1;
        }

        while((superRow>=keyNum)||(superCol>=keyNum)||(superRow<0)||(superCol<0))
        {
        System.out.println("These are invalid numbers. Enter in another superrow and supercolumn");
        System.out.println("Enter the superrow");
        superRow=scan.nextInt()-1;
        System.out.println("Enter the supercolumn");
        superCol=scan.nextInt()-1;
        }
        do
        {
            System.out.println("Please enter a row to play in.");
            row=scan.nextInt()-1;
            System.out.println("\nPlease enter a column to play in.");
            col=scan.nextInt()-1;
            if(row>=keyNum||col>=keyNum||row<0||col<0)
            {
            System.out.println("Invalid Numbers");
            }
            else if(board.access(superRow,superCol).access(row, col).isFinished())
            {
                System.out.println("This cell is already played.");
            }

        }
        while(((row>=keyNum||col>=keyNum||row<0||col<0)||(board.access(superRow,superCol).access(row, col).isFinished())));
        board.play(isX,superRow,superCol,row,col);
        int[] temp= { row, col};
        return temp;

    }
}
