import java.util.Queue;

public class BFS extends Strategy{

    MoveOrder moveOrder;

    BFS(State initialState, MoveOrder moveOrder)
    {
        currentState = initialState;
        this.moveOrder = moveOrder;
        frontierStates.add(currentState);

    }

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
                exportData = new SolutionInfo(currentState.getDepth(), "", visitedStates, processedStates, maxDepth, (endTime - startTime));
                System.out.println(currentState.toString());
                return true;
            }

            if (currentState.getDepth() < 20)
            {
                Queue<State> children = currentState.getChildren(moveOrder);
                for (State child : children)
                {
                    if (exploredStates.contains(child)) continue;

                    if (isSolved(child))
                    {
                        if (child.getDepth() > maxDepth) maxDepth = child.getDepth();

                        long endTime = System.nanoTime();
                        exportData = new SolutionInfo(currentState.getDepth(), "", visitedStates, processedStates, maxDepth, (endTime - startTime));
                        System.out.println(child.toString());
                        return true;
                    }
                    frontierStates.add(child);
                    exploredStates.add(currentState);
                    visitedStates++;

                }
            }
        }

        return false;
    }
}
//currentState.toString()