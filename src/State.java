public class State {

    private int[][] board;

    public State(int [][]board)
    {
        this.board = board;
    }

    public String toString()
    {
        StringBuilder ret = new StringBuilder();
        for(int i=0; i<board.length; i++)
        {
            for(int j=0; j<board[i].length; j++)
                ret.append(board[i][j]).append(" ");
            ret.append("\n");
        }

        return ret.toString();
    }

}
