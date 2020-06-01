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
//                System.out.println(currentState.toString());
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
//                        System.out.println(child.toString());
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
	    Stack s = new Stack();  //create a stack

	    //while the queue is not empty
	    while(!q.isEmpty())
	    {  //add the elements of the queue onto a stack
	       s.push( q.poll());
	    } 

	    //while the stack is not empty
	    while(!s.isEmpty())
	    { //add the elements in the stack back to the queue
	      q.add(s.pop());
	    }

	}
	
//	public static void main(String[] args)
//    {
//		LinkedList<Integer> list = new LinkedList();
//		list.add(1);
//		list.add(2);
//		list.add(3);
//		Queue<Integer> toInvert = (Queue<Integer>) list;
//		System.out.println(toInvert);
//		reverse(toInvert);
//		System.out.println(toInvert);
//
//		
//    }

	
	
}
