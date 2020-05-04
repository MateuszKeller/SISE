import java.io.File;
import java.util.Scanner;

public class ImportExport {

    static String initialName = "resources/4x4_01_00001.txt";

    public static void setFileName(String fileName) { ImportExport.initialName = fileName; }

    public static State initialState()
    {
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

    public static String exportInfo(SolutionInfo informations)
    {
        StringBuilder ret = new StringBuilder();
        ret.append(informations.getLength()).append("\n")
            .append(informations.getMoves());

        return ret.toString();
    }

    public static String exportExtras(SolutionInfo informations)
    {
        StringBuilder ret = new StringBuilder();
        ret.append(informations.getLength()).append("\n")
            .append(informations.getVisitedStates()).append("\n")
            .append(informations.getProcessedStates()).append("\n")
            .append(informations.getMaxRecursionDepth()).append("\n")
            .append(informations.getProcessingTime());

        return ret.toString();
    }
}
