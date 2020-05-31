
public class ManhattanHeuristic extends Heuristic{

	@Override
	public int calculate(State state) {
		int result = 0;
		int col = state.getColumnsNumber();
		int row = state.getRowsNumber();
//		System.out.println(col);
		int size = state.getBoard().length;
		State goal = new State();
		int h; 
		int v;
//		int x1;
//		int x2;
		
		for(int val = 1; val < size; val++ ) { //nie bierzemy bloczka 0 aby metryka byla dopuszczalna
			for(int x1 = 0; x1 < size; x1++) {
				if(goal.getBoard()[x1] == val) {
					for(int x2 = 0; x2 < size; x2++) {
						if(state.getBoard()[x2] == val) {
							v = Math.abs(x1/col - x2/col); //ok
							h = Math.abs(x1%col - x2%col);
//							System.out.println("val: " + val);
//							System.out.println("v:" + v);
//							System.out.println("h:" + h);
//							System.out.println("one res:" + Math.abs(x2 - x1)/col + (Math.abs(x2 - x1)/col)%(col));
//							System.out.println(Math.abs(x2 - x1)/col);
//							System.out.println((Math.abs(x2 - x1))%(col));
							result += v + h;
//							x1 = x2= size; 
						}
					}
				}
			}
		}
		return result;
	}
	
	  public static void main(String[] args) {
	    int [] board = {1,2,3,4,5,6,7,9,8,10,11,12,13,14,15,0};
	    State second =  new State (board);
	    ManhattanHeuristic m = new ManhattanHeuristic();
	    System.out.println(m.calculate(second));
	  
	  }

}
