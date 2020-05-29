
public class HammingHeuristic extends Heuristic{

	@Override
	public int calculate(State state) {
		int result = 0; 
		State goal = new State();
		for (int i = 0; i < goal.getBoard().length; i++) {
			if(state.getBoard()[i] != goal.getBoard()[i]) {
				if(state.getBoard()[i] != 0) {//nie bierzemy bloczka 0 aby metryka byla dopuszczalna
					result ++;
				}
			}
		}
		return result;
	}

}
