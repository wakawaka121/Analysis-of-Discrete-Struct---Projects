
public class Puzzle {
	static final int rowDifference[] = {-1,0,1,0}; 
	static final int colDifference[] = {0,1,0,-1};
	
	public Grid puzzleGrid;
	public int size;
	public Deque<Loc> path;

	
	public Puzzle(Grid grid) {
		puzzleGrid = grid;
		size = grid.size();
		path = new Deque<Loc>();
	}
	
	public String find(String word, int r, int c){
		String pathString = "";
		try {
			findHelper(word,r,c);
			while(!path.isEmpty()) {
				pathString += path.getFirst().toString();
			}
		} catch (EmptyDequeException e) {
			
		}
		return pathString;
	}
	
	private boolean findDFSMatch(String word, int r, int c){
		try {
			if(word.length() == 0) {
				return true;
			}
			if(r < 0 || c < 0) {
				return false;
			}
			if(r >= size || c >= size) {
				return false;
			}
			Loc curLoc = puzzleGrid.getLoc(r, c);
			if(curLoc.visited == true) {
				return false;
			}
			if(!curLoc.getVal().equals(Character.toString(word.charAt(0)))){
				return false;
			}
			curLoc.visited= true;
			path.addToBack(curLoc);
			curLoc.visited = false;
			for(int i = 0; i < rowDifference.length; i++) {
				int row = r + rowDifference[i];
				int col = c + colDifference[i];
				if(findDFSMatch(word.substring(1),row,col)) {
					return true;
				}
			}
			path.getLast();
			curLoc.visited = false;
		} catch (EmptyDequeException e) {
			
		}
		return false;
	}

	public void findHelper(String word, int r, int c){
		try {
			boolean found = false;
			boolean[][] markBFS = new boolean[size][size];
			Deque<Loc> visitedDQ = new Deque<Loc>();
			String firstLetter = Character.toString(word.charAt(0));
			Loc originLoc = puzzleGrid.getLoc(r, c);
			markBFS[r][c] = true;
			visitedDQ.addToBack(originLoc);
			while(visitedDQ.isEmpty() == false && found == false){
				Loc curLoc = visitedDQ.getFirst();
				int checkRow = curLoc.row;
				int checkCol = curLoc.col;
				if(curLoc.getVal().equals(firstLetter)) {
					found = findDFSMatch(word, checkRow, checkCol);
				} else {
					for(int i = 0; i < rowDifference.length; i++) {
						int row = checkRow + rowDifference[i];
						int col = checkCol + colDifference[i];
						adjacentBFS(row, col, markBFS, visitedDQ);
					}
				}
			}
		} catch (EmptyDequeException e) {
			
		}
	}
	
	public void adjacentBFS(int row, int col, boolean[][] markBFS, Deque<Loc> visitedDQ) {
		if(row >= 0 && col >= 0 && row < size && col < size) {
			if(!markBFS[row][col]) {
				Loc curLoc = puzzleGrid.getLoc(row, col);
				visitedDQ.addToBack(curLoc);
				markBFS[row][col] = true;
			}
		}
	}
}
