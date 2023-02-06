
public class MinHeapPQ {
	private static final int FIRST_INDEX = 1;
	public Loc[] minHeap;
	public int size;
	private int maxSize;
		
	public MinHeapPQ() {
		size = 0;
		maxSize = 10;
		minHeap = new Loc[maxSize];
	}
	
	private void growHeap() {
		Loc[] newArray = new Loc[minHeap.length*2];
		for(int index = 1; index <= size; index++) {
			newArray[index] = minHeap[index];
		}
		minHeap = newArray;
	}
		
	public void enqueue(Loc element) {
		try {
			if(size == minHeap.length-1) {
				growHeap();
			}
			if(size == 0) {
				minHeap[FIRST_INDEX] = element;
				size++;
			} else {
				size++;
				minHeap[size] = element;
				bubbleUp(size);
			}
		} catch (Exception e) {
			throw new NullPointerException("out of bounds");
		}
	}
		
	public void bubbleUp(int startIndex) {
		int currentIndex = startIndex;
		while(currentIndex !=1 && minHeap[currentIndex].distance < minHeap[currentIndex/2].distance) {
			{
				Loc tempSwapHolder = minHeap[currentIndex/2];
				minHeap[currentIndex/2] = minHeap[currentIndex];
				minHeap[currentIndex] = tempSwapHolder;	
				currentIndex = currentIndex/2;
			}
		}
	}
	
	public Loc childPriorityMin(Loc childOne, Loc childTwo) {
		if (childOne.distance < childTwo.distance) {
            return childOne;
        } else if (childTwo.distance < childOne.distance) {
            return childTwo;
        } else {
            return childOne;
        }
	}
	
	public int swapLoc(Loc current, Loc toSwap, int index) {
		int indexAfter = 0;
        if(minHeap[index*2].equals(toSwap)) {
            indexAfter = index*2;
        } else {
            indexAfter = (index*2)+1;
        }
        Loc tempSwapHolder = minHeap[indexAfter];
        minHeap[indexAfter] = minHeap[index];
        minHeap[index] = tempSwapHolder;
        return indexAfter;

	}
	
	public void changeDistance(Loc element, int newDistance) {
		for(int index  = 1; index <= size; index++) {
			Loc curLoc = minHeap[index];
			if(curLoc.row == element.row && curLoc.col == element.col) {
				if(newDistance < curLoc.distance) {
					curLoc.distance = newDistance;
					bubbleUp(index);
				}
			}
		}
	}
	
	private void bubbleDown(int startIndex) {
		if (startIndex * 2 > size) {
            return;
        } else if (minHeap[startIndex * 2] != null 
        		&& minHeap[(startIndex * 2) + 1] != null) {	
            Loc toSwitch = childPriorityMin(minHeap[startIndex * 2],
                    minHeap[(startIndex * 2) + 1]);
            if(minHeap[startIndex].distance > toSwitch.distance) {
            	int indexAfter = swapLoc(minHeap[startIndex], toSwitch, startIndex);
            	bubbleDown(indexAfter);
            } 
        } else if(minHeap[startIndex * 2] != null
                && minHeap[startIndex * 2 + 1] == null) {
        	if(minHeap[startIndex].distance < minHeap[startIndex*2].distance) {
        		return;
        	} else {
        		swapLoc(minHeap[startIndex], minHeap[startIndex * 2],
                         startIndex);
        	}
        }
	}
	
	public Loc dequeue() {
		Loc minValue = minHeap[FIRST_INDEX];
		minHeap[FIRST_INDEX] = minHeap[size];
		minHeap[size] = null;
		size--;
		if(size > 0) {
			bubbleDown(FIRST_INDEX);
		}
		return minValue;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}		
}
