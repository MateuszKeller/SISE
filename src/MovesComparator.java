import java.util.Comparator;

public class MovesComparator implements Comparator<Moves> {

    private final MoveOrder moveOrder;
    MovesComparator(MoveOrder moveOrder) { this.moveOrder = moveOrder; }

    public int compare(Moves move1, Moves move2)
    {
        String moveOrder = this.moveOrder.toString();
        for(int i = 0; i < moveOrder.length(); i++)
        {
            if(move1.toString().charAt(0) == moveOrder.charAt(i)) return -1;
            if(move2.toString().charAt(0) == moveOrder.charAt(i)) return 1;
        }
        return 0;
    }
}
