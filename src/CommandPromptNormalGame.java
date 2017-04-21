import Model.NormalBoard;
import Model.UltimateBoard;

import java.util.Scanner;

/**
 * Created by Mohammed on 4/19/2017.
 */
public class CommandPromptNormalGame {
    private static int keyNum=1;
    static boolean isX;
    static NormalBoard board;
    static Scanner scan;
    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        scan = new Scanner(System.in);
        int num,superRow, superCol, row, col;
        int[] temp;
        System.out.println("TIC TAC TOE!!");
        System.out.println("Choose the size of board");
        num=scan.nextInt();
        keyNum = num;
        board = new NormalBoard(keyNum);
        System.out.println(board);

        do
        {

            isX = !isX;
            temp=play();
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
    public static int[] play()
    {

        int row=0;
        int col=0;
        if(isX)
            System.out.println("Player X's turn:");
        else
            System.out.println("Player O's turn:");
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
            else if(board.access(row, col).isFinished())
            {
                System.out.println("This cell is already played.");
            }

        }
        while(((row>=keyNum||col>=keyNum||row<0||col<0)||(board.access(row, col).isFinished())));
        board.play(isX,row,col);
        int[] temp= { row, col};
        return temp;

    }
}
