
public class Main {

    private static State initialState;

    public static void main(String[] args)
    {
	    System.out.println("Hello world!");
        initialState = ImportExport.initialState();

	    System.out.print(initialState.toString());
    }




}
