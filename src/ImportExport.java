import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ImportExport {

    static String initialName = "resources/4x4_01_00001.txt";

    public static void setFileName(String fileName) { ImportExport.initialName = fileName; }
    
    public static void setInitialName(String name) {
    	initialName = name;
    }

    public static State initialState()
    {
    	//TODO change to read in table sizes
        int [] board = null;
        int rows = 0;
        int columns = 0;
        try
        {
            Scanner scanner = new Scanner(new File(initialName));
            rows = Integer.parseInt(scanner.next());
            columns = Integer.parseInt(scanner.next());
             board = new int [rows*columns];

            for(int i=0; i<board.length; i++)
                board[i] = Integer.parseInt(scanner.next());

            scanner.close();

        }
        catch(Exception e) { e.printStackTrace(); }

        return new State(board, rows, columns);
    }
    
    public static void saveSolutionToFile(SolutionInfo infos, String fileName) {
    	try{
    		PrintWriter out = new PrintWriter (fileName);
    		out.print(exportInfo(infos));
    		out.close();

    	} catch( IOException e) {
    		e.printStackTrace();
    	}
    }

    public static String exportInfo(SolutionInfo infos)
    {
        StringBuilder ret = new StringBuilder();
//        if (infos.getLength() == -1) {
//        	 ret.append(infos.getLength());
//        } else {
        ret.append(infos.getLength()).append("\n")
            .append(infos.getMoves());
//        }
        return ret.toString();
    }
    
    public static void saveExtrasToFile(SolutionInfo infos, String fileName) {
    	try{
    		PrintWriter out = new PrintWriter (fileName);
    		out.print(exportExtras(infos));
    		out.close();
    		
    	} catch( IOException e) {
    		e.printStackTrace();
    	}
    }

    public static String exportExtras(SolutionInfo infos)
    {
        StringBuilder ret = new StringBuilder();
        ret.append(infos.getLength()).append("\n")
            .append(infos.getVisitedStates()).append("\n")
            .append(infos.getProcessedStates()).append("\n")
            .append(infos.getMaxDepth()).append("\n")
//            .append(String.format("%.3f", infos.getProcessingTime())); // TODO MAKE TIME GREAT AGAIN/ READABLE
            .append(String.format("%.3f",infos.getProcessingTime())); 
//        System.out.println("time: " + infos.getProcessingTime());

        return ret.toString();
    }
}
