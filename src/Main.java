import java.io.File;
import java.util.Scanner;

public class Main {

    private static State initialState;

    public static void main(String[] args)
    {
	    System.out.println("Hello world!");
	    loadState("resources/4x4_01_00001.txt");

	    System.out.print(initialState.toString());
    }

    private static void loadState(String fileName)
    {
        try
        {
            Scanner scanner = new Scanner(new File(fileName));
            int [][] board = new int [Integer.parseInt(scanner.next())][Integer.parseInt(scanner.next())];

            for(int i=0; i<board.length; i++)
                for(int j=0; j<board[i].length; j++)
                    board[i][j] = Integer.parseInt(scanner.next());

            initialState = new State(board);
            scanner.close();
        }
        catch(Exception e) { e.printStackTrace(); }

    }


}
