import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class MakeOutput {
    private static ScoreDB db;

    public static void main(String[] args) {
	init();
	int num = Integer.parseInt(args[1]);
	String fn = args[0] + "_input" + num + ".txt";
	BufferedReader br;
	try {
	    br = new BufferedReader(new FileReader(fn));
	    String line = br.readLine();
	    while(line != null) {
		processCmd(line);
		line = br.readLine();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private static void init() {
	db = new ScoreDB();
    }
	
    private static void processCmd(String cmd) {
	String[] split = cmd.split(",");
	if(split[0].equals("put")) {
	    System.out.println("\n" + cmd);
	    db.put(split[1], Integer.parseInt(split[2]));
	} else if(split[0].equals("getScore")) {
	    System.out.println("\n" + cmd);
	    System.out.println(db.getScore(split[1]));
	} else if(split[0].equals("getRank")) {
	    System.out.println("\n" + cmd);
	    System.out.println(db.getRank(split[1]));
	} else if(split[0].equals("higherThan")) {
	    System.out.println("\n" + cmd);
	    printList(db.higherThan(Integer.parseInt(split[1])));
	} else if(split[0].equals("lowerThan")) {
	    System.out.println("\n" + cmd);
	    printList(db.lowerThan(Integer.parseInt(split[1])));
	} else if(split[0].equals("top")) {
	    System.out.println("\n" + cmd);
	    printList(db.top(Integer.parseInt(split[1])));
	} else if(split[0].equals("bottom")) {
	    System.out.println("\n" + cmd); 
	    printList(db.bottom(Integer.parseInt(split[1])));
	} else if(split[0].equals("numContestants")) {
	    System.out.println("\n" + cmd);
	    System.out.println(db.numContestants());
	} else if(split[0].equals("max")) {
	    System.out.println("\n" + cmd);
	    printList(db.max());
	} else if(split[0].equals("get")) {
	    System.out.println("\n" + cmd);
	    printList(db.get(Integer.parseInt(split[1])));
	}
    }

    private static void printList(ArrayList<Contestant> list) {
	if(list == null)
	    return;
	Collections.sort(list, new SortContestants());
	for(Contestant c: list) {
	    System.out.println(c);
	}
    }
}
