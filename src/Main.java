import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {
		ImportExport.setFileName(args[2]);

        State initialState = ImportExport.initialState();

        Strategy strategy = null; 
        if(args[0].equals("bfs")) {
        	MoveOrder moveOrder = MoveOrder.getFromString(args[1]);
        	strategy = new BFS(initialState, moveOrder );
            System.out.println(strategy.getSolutionInfo());

        } else if (args[0].equals("dfs")) {
        	MoveOrder moveOrder = MoveOrder.getFromString(args[1]);
        	strategy = new DFS(initialState, moveOrder );
        } else if (args[0].equals("astr")) {
        	strategy = new AStarStrategy(initialState, args[1]);
        }
        
        if(strategy != null) {
        	strategy.solve();
            ImportExport.saveSolutionToFile(strategy.getSolutionInfo(), args[3]);
            ImportExport.saveExtrasToFile(strategy.getSolutionInfo(), args[4]);

        }
    }
}