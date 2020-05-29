import java.io.File;
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
        try
        {
            Scanner scanner = new Scanner(new File(initialName));
             board = new int [Integer.parseInt(scanner.next())*Integer.parseInt(scanner.next())];

            for(int i=0; i<board.length; i++)
                board[i] = Integer.parseInt(scanner.next());

            scanner.close();

        }
        catch(Exception e) { e.printStackTrace(); }

        return new State(board);
    }

    public static String exportInfo(SolutionInfo infos)
    {
        StringBuilder ret = new StringBuilder();
        ret.append(infos.getLength()).append("\n")
            .append(infos.getMoves());

        return ret.toString();
    }

    public static String exportExtras(SolutionInfo infos)
    {
        StringBuilder ret = new StringBuilder();
        ret.append(infos.getLength()).append("\n")
            .append(infos.getVisitedStates()).append("\n")
            .append(infos.getProcessedStates()).append("\n")
            .append(infos.getMaxDepth()).append("\n")
            .append(String.format("%.3f", infos.getProcessingTime())); // TODO MAKE TIME GREAT AGAIN/ READABLE

        return ret.toString();
    }
}
