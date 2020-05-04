public class State {

    private int[] board;

    public State(int []board)
    {
        this.board = board;
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

}
