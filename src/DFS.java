import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class DFS extends Strategy{

	MoveOrder moveOrder;

    DFS(State initialState, MoveOrder moveOrder)
    {
        currentState = initialState;
        goalState = new State(currentState.getRowsNumber(),currentState.getColumnsNumber());
        this.moveOrder = moveOrder;
        frontierStates.add(currentState);
    }

	@Override
	public boolean solve()
    {
        long startTime = System.nanoTime();
        int visitedStates = 0;
        int processedStates = 0;

        while (!frontierStates.isEmpty())
        {
            currentState = frontierStates.get(0);
            frontierStates.remove(0);
            processedStates++;

            if (currentState.getDepth() > maxDepth) maxDepth = currentState.getDepth();
            if (isSolved(currentState))
            {
                long endTime = System.nanoTime();
                exportData = new SolutionInfo(currentState.getDepth(), getSolution(currentState), visitedStates, processedStates, maxDepth, (endTime - startTime));

                return true;
            }

            if (currentState.getDepth() < 	20)
            {
                Queue<State> children = currentState.getChildren(moveOrder);
                reverse(children);
                for (State child : children)
                {
                    if (exploredStates.contains(child)) continue;

                    if (isSolved(child))
                    {
                        if (child.getDepth() > maxDepth) maxDepth = child.getDepth();

                        long endTime = System.nanoTime();
                        exportData = new SolutionInfo(child.getDepth(), getSolution(child), visitedStates, processedStates, maxDepth, (endTime - startTime));

                        return true;
                    }
                    frontierStates.add(0, child);
                    exploredStates.add(currentState);
                    visitedStates++;

                }
            }
        }

        long endTime = System.nanoTime();
        exportData = new SolutionInfo(-1, "", visitedStates, processedStates, maxDepth, (endTime - startTime));

        return false;
    }
	
	public void reverse(Queue q)
	{
	    Stack s = new Stack(); 

	    while(!q.isEmpty())
	    {
	       s.push( q.poll());
	    } 

	    while(!s.isEmpty())
	    {
	      q.add(s.pop());
	    }
	}
}
