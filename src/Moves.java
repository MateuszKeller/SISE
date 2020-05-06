
public enum Moves {
    LEFT    ("L"),
    RIGHT   ("R"),
    UP      ("U"),
    DOWN    ("D"),
    NO      ("N");

    private final String direction;

    Moves(String direction) {
        this.direction = direction;
    }
    public String getDirection() { return direction; }
}
