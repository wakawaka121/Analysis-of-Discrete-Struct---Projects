import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class ScoreDB {
	int numContestants;
	Map<String,Contestant> nameScore;
	Map<String, Integer> contestantRank;
	Map<Integer,Integer> rankScores;
	PriorityQueue queueScores;
	
	
	public ScoreDB() {
		numContestants = 0;
		nameScore = new HashMap<String,Contestant>();
		contestantRank = new TreeMap<String, Integer>();
		rankScores = new HashMap<Integer,Integer>();
		queueScores = new PriorityQueue(new SortContestants());
		
	}
	
	public void put(String name, int score) {
		if(nameScore.containsKey(name)) {
			Contestant curContestant = nameScore.get(name);
			contestantRank.remove(name);
			queueScores.remove(curContestant);
			curContestant.addToScore(score);
			contestantRank.put(name, curContestant.getScore());
			queueScores.add(curContestant);
		} else {
			Contestant newContestant = new Contestant(name,score);
			queueScores.add(newContestant);
			contestantRank.put(name,score);
			nameScore.put(name, newContestant);
			numContestants++;
		}
	}
	
	public Integer getScore(String name) {
		if(nameScore.containsKey(name)) {
			return nameScore.get(name).getScore();
		} else {
			return -1;
		}
	}
	
	public int getRank(String name) {
		makeRankMap();
		if(nameScore.containsKey(name)) {
			Contestant searchContestant = nameScore.get(name);
			return rankScores.get(searchContestant.getScore());
		}
		return -1;
	}
	
	private void makeRankMap() {
		Object[] ranks = queueScores.toArray();
		int rank = 1;
		for(int i = ranks.length-1; i >=0; i--) {
			Contestant curContestant = (Contestant) ranks[i];
			if(!rankScores.containsKey(curContestant.getScore())) {
				rankScores.put(curContestant.getScore(), rank);
				rank++;
			}
		}
	}
	
	public ArrayList<Contestant> higherThan(int threshold){
		ArrayList<Contestant> higherThan = new ArrayList<Contestant>();
		boolean shouldContinue = true;
		Object[] contestantOrder = queueScores.toArray();
		int lastIndex = contestantOrder.length-1;
		while(lastIndex >=0 && shouldContinue) {
			Contestant curContestant = (Contestant) contestantOrder[lastIndex];
			if(curContestant.getScore() < threshold) {
				shouldContinue = false;
			} else {
				higherThan.add(curContestant);
				lastIndex--;
			}
			
		}
		return higherThan;
	}
	
	public ArrayList<Contestant> lowerThan(int threshold){
		ArrayList<Contestant> lowerThan = new ArrayList<Contestant>();
		boolean shouldContinue = true;
		Object[] contestantOrder = queueScores.toArray();
		int startIndex = 0;
		while(startIndex < contestantOrder.length && shouldContinue) {
			Contestant curContestant = (Contestant) contestantOrder[startIndex];
			if(curContestant.getScore() > threshold) {
				shouldContinue = false;
			} else {
				lowerThan.add(curContestant);
				startIndex++;
			}
		}
		return lowerThan;
	}
	
	public ArrayList<Contestant> top(int n){
		ArrayList<Contestant> topN = new ArrayList<Contestant>();
		Object[] contestantOrder = queueScores.toArray();
		int startIndex = contestantOrder.length-1;
		while(n > 0 && startIndex >= 0) {
			Contestant curContestant = (Contestant) contestantOrder[startIndex];
			topN.add(curContestant);
			startIndex--;
			n--;
		}
		return topN;
	}
	
	public ArrayList<Contestant> bottom(int n){
		ArrayList<Contestant> bottomN = new ArrayList<Contestant>();
		Object[] contestantOrder = queueScores.toArray();
		int startIndex = 0;
		while(n > 0 && startIndex < contestantOrder.length) {
			Contestant curContestant = (Contestant) contestantOrder[startIndex];
			bottomN.add(curContestant);
			startIndex++;
			n--;
		}
		return bottomN;
	}
	
	public int numContestants() {
		return numContestants;
	}
	
	public ArrayList<Contestant> max(){
		ArrayList<Contestant> maxContestants = new ArrayList<Contestant>();
		boolean shouldContinue = true;
		Object[] contestantOrder = queueScores.toArray();
		int startIndex = contestantOrder.length-1;
		Contestant someoneWithMax = (Contestant) contestantOrder[startIndex];
		int max = someoneWithMax.getScore();
		while(startIndex >=0 && shouldContinue) {
			Contestant curContestant = (Contestant) contestantOrder[startIndex];
			if(curContestant.getScore() != max) {
				shouldContinue = false;
			} else {
				maxContestants.add(curContestant);
				startIndex--;
			}
		}
		
		return maxContestants;
	}
	
	public ArrayList<Contestant> get(int rank){
		makeRankMap();
		ArrayList<Contestant> withRank = new ArrayList<Contestant>();
		if(rankScores.containsValue(rank)) {
			int searchScore = getSearchScore(rank);
			boolean shouldContinue = true;
			Object[] contestantOrder = queueScores.toArray();
			int startIndex = 0;
			while(startIndex < contestantOrder.length && shouldContinue) {
				Contestant curContestant = (Contestant) contestantOrder[startIndex];
				if(curContestant.getScore() > searchScore) {
					shouldContinue = false;
				} 
				if(curContestant.getScore() == searchScore) {
					withRank.add(curContestant);
					startIndex++;
				} else {
					startIndex++;
				}
			}
		}
		return withRank;
	}

	private int getSearchScore(int rank) {
		for(Map.Entry<Integer, Integer> entry : rankScores.entrySet()) {
			if(entry.getValue() == rank) {
				return entry.getKey();
			}
		}
		return -1;
	}
	
}
