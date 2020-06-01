import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {
//        State initialState = ImportExport.initialState();
//
//	    System.out.print(initialState.toString());
////        System.out.println(initialState.getAvailableMoves(initialState.getRowsNumber(), initialState.getColumnsNumber()));
//        System.out.println("-------------------------------------------------------------------\n");
//
//        MoveOrder moveOrder = MoveOrder.getFromString("RDUL");
////        Strategy I_HOPE_IT_WORKS = new AStarStrategy(initialState, "hamm");
//        Strategy I_HOPE_IT_WORKS = new BFS(initialState, moveOrder);
//
//        boolean works = I_HOPE_IT_WORKS.solve();
//        
////        System.out.println(works);
//
//        System.out.print(ImportExport.exportExtras(I_HOPE_IT_WORKS.getSolutionInfo()));
//        System.out.print(ImportExport.exportInfo(I_HOPE_IT_WORKS.getSolutionInfo()));
//        ImportExport.saveExtrasToFile(I_HOPE_IT_WORKS.getSolutionInfo(), "extras.txt");
//        ImportExport.saveSolutionToFile(I_HOPE_IT_WORKS.getSolutionInfo(), "solution.txt");

		ImportExport.setFileName(args[2]);

        State initialState = ImportExport.initialState();

        Strategy strategy = null; 
        if(args[0].equals("bfs")) {
        	MoveOrder moveOrder = MoveOrder.getFromString(args[1]);
        	strategy = new BFS(initialState, moveOrder );
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