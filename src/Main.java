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
        Strategy I_HOPE_IT_WORKS = new BFS(initialState, moveOrder);
        boolean works = I_HOPE_IT_WORKS.solve();

        System.out.print(ImportExport.exportExtras(I_HOPE_IT_WORKS.getSolutionInfo()));

    }




}