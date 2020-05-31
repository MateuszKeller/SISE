import java.util.PriorityQueue;
import java.util.Queue;

public class AStarStrategy  extends Strategy{

	private Heuristic heuristic;
	
	public AStarStrategy(State initialState, String heuristic) {
        currentState = initialState;
//        frontierStates.add(currentState);

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
        Queue <State> children = null;
        
        
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
        	
        	if(currentState.getDepth()<20) {
        		children = currentState.getChildren(MoveOrder.getFromString("RDUL"));
                for (State child : children)
                {
//                    if (exploredStates.contains(child)) continue;

                    if (isSolved(child))
                    {
                        if (child.getDepth() > maxDepth) maxDepth = child.getDepth();

                        long endTime = System.nanoTime();
                        exportData = new SolutionInfo(currentState.getDepth(), getSolution(child), visitedStates, processedStates, maxDepth, (endTime - startTime));
                        System.out.println(child.toString());
                        return true;
                    }
                    
                    if(!exploredStates.contains(child)) {
                        frontier.add(new PriorityState(currentState,calculatePriority(currentState)));
                    }
//                    exploredStates.add(currentState);
                    visitedStates++;

                }
            }	
        }

		return false;
	}
	
	
	private int calculatePriority(State state) {
		int priority = heuristic.calculate(state) + state.getDepth();
		return priority; 
	}
	
	
	private int getStateDepth (State state){
		int depth = 0;
		while(state.getParent() != null) {
			depth ++;
			state = state.getParent();
		}
		return depth;
	}

}
