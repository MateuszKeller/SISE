import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {
	    System.out.println("Hello world!");
        State initialState = ImportExport.initialState();

	    System.out.print(initialState.toString());
        System.out.println(initialState.getAvailableMoves());
        System.out.println("-------------------------------------------------------------------\n");

        MoveOrder moveOrder = MoveOrder.getFromString("RDUL");
        Strategy I_HOPE_IT_WORKS = new AStarStrategy(initialState, "manh");
        boolean works = I_HOPE_IT_WORKS.solve();
        
        System.out.println(works);

        System.out.print(ImportExport.exportExtras(I_HOPE_IT_WORKS.getSolutionInfo()));
        System.out.print(ImportExport.exportInfo(I_HOPE_IT_WORKS.getSolutionInfo()));


    }




}