import java.util.PriorityQueue;
import java.util.Queue;

public class AStarStrategy  extends Strategy{

	private Heuristic heuristic;
	
	public AStarStrategy(State initialState, String heuristic) {
        currentState = initialState;
        goalState = new State(currentState.getRowsNumber(),currentState.getColumnsNumber());

		if(heuristic.equals("hamm")) {
			this.heuristic = new HammingHeuristic();
		} else if (heuristic.equals("manh")) {
			this.heuristic = new ManhattanHeuristic();
		}
	}
	
	@Override
	public boolean solve() {
		long startTime = System.nanoTime();
        int visitedStates = 0;
        int processedStates = 0;
        
        Queue<PriorityState> frontier = new PriorityQueue<>();
        frontier.add(new PriorityState(currentState, 0));      
        
        while(!frontier.isEmpty()) {
        	currentState = frontier.poll().getState();
        	
        	if(currentState.getDepth() > maxDepth) {
        		maxDepth = currentState.getDepth();
        	}
        	
        	processedStates++; 
        	
        	if(isSolved(currentState)) {
        		long endTime = System.nanoTime();
                exportData = new SolutionInfo(currentState.getDepth(), getSolution(currentState), visitedStates, processedStates, maxDepth, (endTime - startTime));
                return true;
        	} else {
        		exploredStates.add(currentState);
        	}
        	
        	if(currentState.getDepth() < 20) {
        		Queue <State> children = currentState.getChildren(MoveOrder.getFromString("RDUL"));
                for (State child : children)
                {
                    if (isSolved(child)) {
                        if (child.getDepth() > maxDepth) { 
                        	maxDepth = child.getDepth();
                        }

                        long endTime = System.nanoTime();
                        exportData = new SolutionInfo(child.getDepth(), getSolution(child), visitedStates, processedStates, maxDepth, (endTime - startTime));
                        System.out.println(child.toString());
                        return true;
                    }
                    
                    if(!exploredStates.contains(child)) {
                    	exploredStates.add(child);
                        frontier.add(new PriorityState(child,calculatePriority(child)));
                    }
                    visitedStates++;
                }
            }	
        }
        long endTime = System.nanoTime();
        exportData = new SolutionInfo(-1, "", visitedStates, processedStates, maxDepth, (endTime - startTime));

		return false;
	}
		
	private int calculatePriority(State state) {
		int priority = heuristic.calculate(state) + state.getDepth();
		return priority; 
	}
}
