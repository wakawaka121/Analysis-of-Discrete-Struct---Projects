
public class ShortestPath {
	static final int rowDifference[] = {-1,0,1,0}; 
	static final int colDifference[] = {0,1,0,-1};
	
	public Grid graph;
	public int size;
	
	public ShortestPath(Grid grid) {
		graph = grid;
		size = graph.size();
	}
	
	public String getShortestPath(int sr, int sc, int tr, int tc) {
		MinHeapPQ pQueue = new MinHeapPQ();
		Deque<Loc> sptCloud = new Deque<Loc>();
		String path = "";
		Loc curLoc = graph.getLoc(sr, sc); // access count
		curLoc.distance = curLoc.getIntVal();
		pQueue.enqueue(curLoc);
		curLoc.inQ = true;
		while(!pQueue.isEmpty()) {
			Loc removed = pQueue.dequeue();
			int curRow = removed.row;
			int curCol = removed.col;
			sptCloud.addToBack(removed);
			if(curRow == tr && curCol == tc) {
				Loc runner = removed;
				while(runner.previous != null) {
					path = runner.toString() + path;
					runner = runner.previous;
				}
				path = curLoc.toString() + path;
				break;
			} else {
				for(int i = 0; i < rowDifference.length; i++) {
					int row = curRow + rowDifference[i];
					int col = curCol + colDifference[i];
					if(row == sr && col == sc) {
						continue;
					}
					updateNeighbors(row,col,pQueue,removed);
					if(row == tr && col == tc) {
						break;
					}
				}
			}
		}
		cleanUp(pQueue, sptCloud);
		return path;
	}
	
	public void updateNeighbors(int row, int col, MinHeapPQ pQueue, Loc removed) {
		if(row >= 0 && col >= 0 && row < size && col < size) {
			Loc curLoc = graph.getLoc(row, col);//access count
			int tempDistance = curLoc.getIntVal() + removed.distance;
			if(curLoc.inQ) {	
				if(tempDistance < curLoc.distance) {
					pQueue.changeDistance(curLoc, tempDistance);
					curLoc.previous = removed;
				}
			} else {
				curLoc.distance = tempDistance;
				curLoc.previous = removed;
				pQueue.enqueue(curLoc);
				curLoc.inQ = true;
			}
		}
	}

	public void cleanUp(MinHeapPQ pQ,Deque<Loc> sptCloud) {
		while(!pQ.isEmpty()) {
			Loc curLoc = pQ.dequeue();
			curLoc.resetLoc();
		}
		while(!sptCloud.isEmpty()) {
			Loc curLoc;
			try {
				curLoc = sptCloud.getFirst();
				curLoc.resetLoc();
			} catch (EmptyDequeException e) {
				e.printStackTrace();
			}
		}
	}
}
