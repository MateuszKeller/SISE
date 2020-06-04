
public class ManhattanHeuristic extends Heuristic{

	@Override
	public int calculate(State state) {
		int result = 0;
		int col = state.getColumnsNumber();
		int row = state.getRowsNumber();
		int size = state.getBoard().length;
		State goal = new State(state.getRowsNumber(), state.getColumnsNumber());
		int h; 
		int v;
		
		for(int val = 1; val < size; val++ ) { //nie bierzemy bloczka 0 aby metryka byla dopuszczalna
			for(int x1 = 0; x1 < size; x1++) {
				if(goal.getBoard()[x1] == val) {
					for(int x2 = 0; x2 < size; x2++) {
						if(state.getBoard()[x2] == val) {
							v = Math.abs(x1/col - x2/col); //ok
							h = Math.abs(x1%col - x2%col);
							result += v + h;
						}
					}
				}
			}
		}
		return result;
	}
}
