import java.security.InvalidParameterException;

public enum MoveOrder {

    RIGHT_DOWN_UP_LEFT("RDUL"),
    RIGHT_DOWN_LEFT_UP("RDLU"),
    DOWN_RIGHT_UP_LEFT("DRUL"),
    DOWN_RIGHT_LEFT_UP("DRLU"),
    LEFT_UP_DOWN_RIGHT("LUDR"),
    LEFT_UP_RIGHT_DOWN("LURD"),
    UP_LEFT_DOWN_RIGHT("ULDR"),
    UP_LEFT_RIGHT_DOWN("ULRD");

    private final String moveOrder;

    MoveOrder(String moveOrder) {
        this.moveOrder = moveOrder;
    }

    public String getMoveOrder() { return moveOrder; }
    public static MoveOrder getFromString(String par) throws InvalidParameterException {
        if(par.equals("RDUL")) return RIGHT_DOWN_UP_LEFT;
        if(par.equals("RDLU")) return RIGHT_DOWN_LEFT_UP;
        if(par.equals("DRUL")) return DOWN_RIGHT_UP_LEFT;
        if(par.equals("DRLU")) return DOWN_RIGHT_LEFT_UP;
        if(par.equals("LUDR")) return LEFT_UP_DOWN_RIGHT;
        if(par.equals("LURD")) return LEFT_UP_RIGHT_DOWN;
        if(par.equals("ULDR")) return UP_LEFT_DOWN_RIGHT;
        if(par.equals("ULRD")) return UP_LEFT_RIGHT_DOWN;

        throw new InvalidParameterException("Wrong move order parameter.");
    }

}
