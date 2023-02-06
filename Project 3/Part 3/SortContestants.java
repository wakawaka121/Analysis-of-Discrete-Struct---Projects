import java.util.Comparator;
public class SortContestants implements Comparator<Contestant> {
    public int compare(Contestant a, Contestant b) {
	int diff = a.getScore() - b.getScore();
	if(diff == 0)
	    diff = a.name.compareTo(b.name);
	return diff;
    }
}
