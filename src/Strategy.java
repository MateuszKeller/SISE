import java.util.*;

public abstract class Strategy {

    protected SolutionInfo exportData;

    protected State currentState = null;
    protected State goalState = new State();
    protected List<Moves> movesSoFar = new ArrayList<>();

    protected Set<State> exploredStates = new HashSet<>();
    protected List<State> frontierStates = new ArrayList<>();

    protected int maxDepth = 1;

    SolutionInfo getSolutionInfo() { return exportData;}

    protected boolean isSolved(State state) { return (Arrays.equals(state.getBoard(), goalState.getBoard())); }

    public abstract boolean solve();

}
