import java.util.*;

public class State {

    private int[] board;
    private int depth;

    State parent;
    Moves move;

    State(int []board)
    {
        this.board = board;
    }
    State() { this.board = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0}; }
    State(int []board, State parent, Moves preMove, int depth)
    {
        this.board = board;
        this.depth = depth;
        this.parent = parent;
        move = preMove;
    }

    public int[] getBoard() {return board; }
    public int getDepth() {return depth; }

    public int getIndex(int value)
    {
        for (byte i = 0; i < board.length; i++)
            if (board[i] == value)
                return i;

        return -1;

    }

    public String toString()
    {
        StringBuilder ret = new StringBuilder();
        for(int i=0; i<board.length; i++)
        {
            if(i%4==0 && i!=0)
                ret.append("\n");
            ret.append(board[i]).append(" ");

        }

        return ret.append("\n").toString();
    }

    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Arrays.equals(board, state.board);
    }

    public List<Moves> getAvailableMoves() {
        List<Moves> availableMoves = new ArrayList<>();
        int x = getIndex(0) % 4;
        int y = getIndex(0) / 4;

        if (y > 0) availableMoves.add(Moves.UP);
        if (y < 3) availableMoves.add(Moves.DOWN);
        if (x > 0) availableMoves.add(Moves.LEFT);
        if (x < 3) availableMoves.add(Moves.RIGHT);

        return availableMoves;
    }

    public Queue<State> getChildren(MoveOrder moveOrder)
    {
        LinkedList<State> children = new LinkedList<>();
        List<Moves> possibleDirections = getAvailableMoves();
        possibleDirections.sort(new MovesComparator(moveOrder));
        
        for (Moves move : possibleDirections) {
            State child = makeChild(move);
            children.add(child);
        }
        return children;
    }

    public State makeChild(Moves whereToMove) // TODO BETTER NAME
    {
        int zeroIndex = getIndex(0);
        int moveIndex = -1;

        switch (whereToMove) 
        {
            case DOWN:
                moveIndex = (byte) (zeroIndex + 4);
//                System.out.println(whereToMove);
                break;
            case UP:
                moveIndex = (byte) (zeroIndex - 4);
//                System.out.println(whereToMove);
                break;
            case LEFT:
                moveIndex = (byte) (zeroIndex - 1);
//                System.out.println(whereToMove);
                break;
            case RIGHT:
                moveIndex = (byte) (zeroIndex + 1);
//                System.out.println(whereToMove);
                break;
        }

        int[] boardAfterMove = makeMove(zeroIndex, moveIndex);
        return new State(boardAfterMove, this, whereToMove, getDepth() + 1);
    }

    private int[] makeMove(int zeroIndex, int moveIndex)
    {
        int[] boardAfterMove = Arrays.copyOf(board, board.length);
        int temp = boardAfterMove[zeroIndex];
        boardAfterMove[zeroIndex] = boardAfterMove[moveIndex];
        boardAfterMove[moveIndex] = temp;

        return boardAfterMove;
    }


}
