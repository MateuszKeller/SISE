import java.util.*;

public class State {

    private int[] board;
    private int depth;
    private int columnsNumber = 4; 
    private int rowsNumber = 4;
    //TODO add fields with board sizes;
    State parent;
    Moves move;
    
    public int getColumnsNumber() {
    	return columnsNumber;
    }
    
    public int getRowsNumber() {
    	return rowsNumber;
    }

    State(int []board, int rows, int columns)
    {
        this.board = board;
        columnsNumber = columns;
        rowsNumber = rows;
        depth = 0;
    }
    State(int rowsNumber, int columnsNumber) { 
    	this.rowsNumber = rowsNumber;
    	this.columnsNumber = columnsNumber;
    	this.board = new int[rowsNumber*columnsNumber];
    	for(int i = 0; i < board.length - 1; i++) {
    		board[i] = i + 1;
    	}
    	board[board.length-1] = 0;
    	}
    State(int []board, State parent, Moves preMove, int depth)
    {
        this.board = board;
        this.depth = depth;
        this.parent = parent;
        move = preMove;
        rowsNumber = parent.getRowsNumber();
        columnsNumber = parent.getColumnsNumber();
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

    public List<Moves> getAvailableMoves(int rowsNumber, int columnsNumber) {
        List<Moves> availableMoves = new ArrayList<>();
        int x = getIndex(0) % columnsNumber;
        int y = getIndex(0) / columnsNumber;

        if (y > 0) availableMoves.add(Moves.UP);
        if (y < rowsNumber - 1) availableMoves.add(Moves.DOWN);
        if (x > 0) availableMoves.add(Moves.LEFT);
        if (x < columnsNumber - 1) availableMoves.add(Moves.RIGHT);

        return availableMoves;
    }

    public Queue<State> getChildren(MoveOrder moveOrder)
    {
        LinkedList<State> children = new LinkedList<>();
        List<Moves> possibleDirections = getAvailableMoves(rowsNumber, columnsNumber);
        possibleDirections.sort(new MovesComparator(moveOrder));
        
        for (Moves move : possibleDirections) {
            State child = makeChild(move);
            children.add(child);
        }
        return children;
    }

    public State makeChild(Moves whereToMove) 
    {
        int zeroIndex = getIndex(0);
        int moveIndex = -1;

        switch (whereToMove) 
        {
            case DOWN:
                moveIndex = (byte) (zeroIndex + columnsNumber);
                break;
            case UP:
                moveIndex = (byte) (zeroIndex - columnsNumber);
                break;
            case LEFT:
                moveIndex = (byte) (zeroIndex - 1);
                break;
            case RIGHT:
                moveIndex = (byte) (zeroIndex + 1);
                break;
        }

        int[] boardAfterMove = makeMove(zeroIndex, moveIndex);
        return new State(boardAfterMove, this, whereToMove, depth + 1);
    }

    private int[] makeMove(int zeroIndex, int moveIndex)
    {
        int[] boardAfterMove = Arrays.copyOf(board, board.length);
        int temp = boardAfterMove[zeroIndex];
        boardAfterMove[zeroIndex] = boardAfterMove[moveIndex];
        boardAfterMove[moveIndex] = temp;

        return boardAfterMove;
    }
    
    public State getParent() {
    	return this.parent;
    }
    
    public Moves getMove() {
    	return this.move;
    }
    
}
