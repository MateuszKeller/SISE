
public class SolutionInfo {

    private int length;
    private String moves;

    private int visitedStates;
    private int processedStates;
    private int maxDepth;
    private double processingTime;

    SolutionInfo(int length, String moves, int visitedStates, int processedStates, int maxDepth, double processingTime)
    {
        this.length = length;
        this.moves = moves;
        this.visitedStates = visitedStates;
        this.processedStates = processedStates;
        this.maxDepth = maxDepth;
        this.processedStates = processedStates;
    }

    public int getLength() { return length; }
    public String getMoves() { return moves; }
    public int getVisitedStates() { return visitedStates; }
    public int getProcessedStates() { return processedStates; }
    public int getMaxDepth() { return maxDepth; }
    public double getProcessingTime() { return processingTime; }
}
