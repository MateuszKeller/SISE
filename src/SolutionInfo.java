
public class SolutionInfo {
    private int length;
    private String moves;

    private int visitedStates;
    private int processedStates;
    private int maxRecursionDepth;
    private double processingTime;

    SolutionInfo(int length, String moves, int visitedStates, int processedStates, int maxRecursionDepth, double processingTime)
    {
        this.length = length;
        this.moves = moves;
        this.visitedStates = visitedStates;
        this.processedStates = processedStates;
        this.maxRecursionDepth = maxRecursionDepth;
        this.processedStates = processedStates;
    }

    public int getLength() { return length; }
    public String getMoves() { return moves; }
    public int getVisitedStates() { return visitedStates; }
    public int getProcessedStates() { return processedStates; }
    public int getMaxRecursionDepth() { return maxRecursionDepth; }
    public double getProcessingTime() { return processingTime; }
}
