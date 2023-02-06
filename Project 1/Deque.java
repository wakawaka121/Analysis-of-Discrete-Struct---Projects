import java.lang.reflect.Array;
import java.util.Random;

@SuppressWarnings("unchecked")
public class Deque<Item> {

	private static final int DEFAULT_CAPACITY = 10;
    private Item[] deque;
    private int size;
    private int accessCount;

    /***
     *constructor: create an empty Deque with initial
     *capacity of 10
     */
    public Deque() {
    	deque = (Item[]) new Object[DEFAULT_CAPACITY];
    	size = 0;
    	accessCount = 0;
    }

    /***
     *constructor: create an empty Deque with initial
     *capacity of n
     */
    public Deque(int n) {
    	deque = (Item[]) new Object[n];
    	size = 0;
    	accessCount = 0;
    }
    
    private void growArray() {
    	Item[] resize = (Item[]) new Object[2*deque.length];
    	for(int index = 0; index < size; index++) {
    		resize[index] = deque[index];
    		
    	}
    	deque = resize;
    }
    
    private void shrinkArray(int n) {
    	Item[] resize = (Item[]) new Object[n];
    	for(int index = 0; index < size; index++) {
    		resize[index] = deque[index];
    	}
    	deque = resize;
    }
    
    /***
     *add an item to the front of the Deque
     *double the array capacity if Deque is full
     */    
    public void addToFront(Item item) {
    	if(size == deque.length) {
    		growArray();
    	}
    	if(size == 0) {
    		deque[0] = item;
    		size++;
    		accessCount++;
    	}
    	else {
    		for(int index = size; index >= 1; index--) {
    			deque[index] = deque[index-1];
    			accessCount++;
    		}
    		deque[0] = item;
    		size++;
    		accessCount++;
    	}
    }

    /***
     *add an item to the back of the Deque;
     *double the array capacity if the Deque is full
     ***/
    public void addToBack(Item item) {
    	if(size == deque.length) {
    		growArray();
    	}
    	if(size == 0) {
    		deque[0] = item;
    		accessCount++;
    		size++;
    	} else {
    		deque[size] = item;
    		size++;
    		accessCount++;
    		}
    	
    }

    /***
     *remove and return the front item from the Deque;
     *throw EmptyDequeException if the Deque is empty;
     *reduce the array capacity by half if the size 
     *of the Deque size falls below floor(N/4) where
     *N is the array capacity, but do not resize it 
     *to be below the default capacity, which is 10
     ***/
    public Item getFirst() throws EmptyDequeException {
    	if(isEmpty()) {
    		throw new EmptyDequeException();
    	}
    	int arrayLen = deque.length;
    	Item first = deque[0];
    	accessCount++;
    	if(size-1 < Math.floor(arrayLen/4)) {
    		if(arrayLen/2 > 10) {
    			shrinkArray(arrayLen/2);
    		}else {
    			shrinkArray(DEFAULT_CAPACITY);
    		}
    	}
    	for(int index = 0; index < size; index++) {
    		deque[index] = deque[index +1];
    		accessCount++;
    	}
    	size--;
    	return first;
    	
    }

     /***
     *remove and return the back item from the Deque;
     *throw EmptyDequeException if the Deque is empty;
     *reduce the array capacity by half if the size 
     *of the Deque size falls below floor(N/4) where
     *N is the array capacity, but do not resize it 
     *to be below the default capacity, which is 10
     ***/
    public Item getLast() throws EmptyDequeException {
    	if(isEmpty()) {
    		throw new EmptyDequeException();
    	}
    	int arrayLen = deque.length;
    	Item last = deque[size-1];
    	accessCount++;
    	if(size -1 < Math.floor(arrayLen/4)) {
    		if(arrayLen/2 > 10) {
    			shrinkArray(arrayLen/2);
    		} else {
    			shrinkArray(DEFAULT_CAPACITY);
    		}
    	}
    	size--;
    	return last;
    }
    
    /***
     *return true if the Deque is empty;
     *return false if the Deque is not empty
     ***/
    public boolean isEmpty() {
    	return size == 0;
    }

    /***
     *return the size of the Deque (i.e. the 
     *number of elements currently in the Deque)
     ***/
    public int size() {
    	return size;
    }

    /***
     *return but do not remove the front item in the Deque;
     *throw an EmptyDequeException if the Deque is empty
     */
    public Item peekFirst() throws EmptyDequeException {
    	if(isEmpty()) {
    		throw new EmptyDequeException();
    	}
    	return deque[0];
    }

    /***
     *return but do not remove the back item in the Deque;
     *throw an EmptyDequeException if the Deque is empty
     */
    public Item peekLast() throws EmptyDequeException {
    	if(isEmpty()) {
    		throw new EmptyDequeException();
    	}
    	return deque[size-1];
    }
    
    /***
     *return the underlying array
     ***/
    public Item[] getArray() {
    	return deque;
    }

    /***
     *return the array accesses count
     ***/
    public int getAccessCount() {
    	return accessCount;
    }
    
    /***
     *reset the array access count to 0
     ***/
    public void resetAccessCount() {
    	accessCount = 0;
    }
    
    public static void main(String[] args) throws EmptyDequeException {
    	int numOps = Integer.valueOf(args[0]);
    	int capacity = Integer.valueOf(args[1]);
    	Random rand = new Random();
    	Deque<Integer> deque = new Deque<Integer>(capacity);
    	while(numOps >= 0) {
    	    int y = rand.nextInt(2);
    	    int x = rand.nextInt(1000);
    	    if(y == 0) {
    	    	deque.addToFront(x);
    	    } else {
    	    	deque.addToBack(x);
    	    }
    	    numOps--;
    	}
    	int addAccesses = deque.getAccessCount();
    	System.out.println(addAccesses);
    	deque.resetAccessCount();
    	while(deque.size > 0) {
    		deque.getLast();
    	}
    	int removeAccesses = deque.getAccessCount();
    	System.out.println(removeAccesses);
    }	
}
