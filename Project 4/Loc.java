public class Loc {
    public final int row;
    public final int col;
    private String val;
    public Integer distance;
    public Loc previous;
    public boolean visited;
    public boolean inQ;

    //constructor
    //x is row, y is column
    public Loc(int x, int y, String val) {
	this.row = x;
	this.col = y;
	this.val = val;
	distance = Integer.MAX_VALUE;
	previous = null;
	visited = false;
	inQ = false;
    }

    //returns Loc in the form (row, col)
    public String toString() {
	return "(" + row + ", " + col + ")";
    }

    //returns the String value at this location
    public String getVal() {
	return val;
    }

    //returns the integer value at this location
    //NOTE: This should only be used if the location
    //contains an integer!
    public int getIntVal() {
	return Integer.parseInt(val);
    }
    
    public void resetLoc() {
    	distance = Integer.MAX_VALUE;
    	previous = null;
    	visited = false;
    	inQ = false;
    }
    
    public boolean equals(Loc other) {
    	return this.row == other.row && this.col == other.col;
    }
}
