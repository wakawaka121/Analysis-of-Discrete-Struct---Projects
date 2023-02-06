import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class DBtest {

	public static void main(String[] args) {
		ScoreDB db = new ScoreDB();
		db.put("ryan", 12);
		db.put("kevin", 11);
		db.put("lee", 24);
		db.put("ryan", 12);
		//System.out.println(db.getRank("ryan"));
		ArrayList<Contestant> mylist = db.get(1);
//		//System.out.println(db.numContestants);
		Collections.sort(mylist, new SortContestants());
		for(int i = 0; i < mylist.size(); i++) {
			System.out.println(mylist.get(i));
		}
//		System.out.println(db.higherThan(11));

		
		
//		PriorityQueue queue = new PriorityQueue();
//		Map<Contestant,Integer> treeMap = new TreeMap<Contestant, Integer>(new SortContestants());
//		Contestant p1 = new Contestant("ryan",12);
//		Contestant p2 = new Contestant("kevin",11);
//		Contestant p3 = new Contestant("lee",24);
//		queue.add(p1);
//		queue.add(p2);
//		queue.add(p3);
//		treeMap.put(p1, 2);
//		treeMap.put(p2,3);
//		treeMap.put(p3, 1);
//		System.out.println(treeMap.get(p1));
//		System.out.println(queue.comparator());
//		System.out.println(queue.remove());
//		System.out.println(queue.remove());
//		System.out.println(queue.remove());
		
	}

}
