
public class test {

	public static void main(String[] args) {
		MinHeapPQ q = new MinHeapPQ();
		Loc l1 = new Loc(0,0,"5");
		l1.distance = 5;
		Loc l2 = new Loc(0,1,"5");
		l2.distance = 5;
		Loc l3 = new Loc(0,2,"5");
		l3.distance = 5;
		q.enqueue(l1);
		q.enqueue(l2);
		q.enqueue(l3);
		while(!q.isEmpty()) {
			System.out.println(q.dequeue().toString());
		}
	}	

}


//Loc l1 = new Loc(0,0,"l1");
//l1.distance = 11;
//Loc l2 = new Loc(0,0,"l2");
//l2.distance = 10;
//Loc l3 = new Loc(0,0,"l3");
//l3.distance = 14;	
//Loc l4 = new Loc(0,0,"l4");
//l4.distance = 9;
//Loc l5 = new Loc(0,0,"l5");
//l5.distance = 5;
//
//
//Loc l6 = new Loc(0,0,"s");
//l6.distance = 11;
//Loc l7 = l6;
////Loc l7 = new Loc(0,0,"s");
////l7.distance = 11;
//
////Loc l8 = new Loc(0,0,"s");
////l8.distance = 11;
////Loc l9 = new Loc(0,0,"s");
////l9.distance = 11;
////Loc l10 = new Loc(0,0,"s");
////l10.distance = 11;
////Loc l11 = new Loc(0,0,"s");
////l11.distance = 11;
//System.out.println(l6 == l7);
//q.enqueue(l1);
//for(int i = 1; i <= q.size; i++) {
//	System.out.println(q.minHeap[i].getVal());
//}
//System.out.println();
//q.enqueue(l2);
//for(int i = 1; i <= q.size; i++) {
//	System.out.println(q.minHeap[i].getVal());
//}
//System.out.println();
//q.enqueue(l3);
//for(int i = 1; i <= q.size; i++) {
//	System.out.println(q.minHeap[i].getVal());
//}
//System.out.println();
//q.enqueue(l4);
//for(int i = 1; i <= q.size; i++) {
//	System.out.println(q.minHeap[i].getVal());
//}
//System.out.println();
//q.enqueue(l5);
//for(int i = 1; i <= q.size; i++) {
//	System.out.println(q.minHeap[i].getVal());
//}
//System.out.println();
//while(!q.isEmpty()) {
//	System.out.println(q.dequeue().getVal());
//	System.out.println("above was dequeued");
//	for(int i = 1; i <= q.size; i++) {
//		System.out.println(q.minHeap[i].getVal());
//	}
//	System.out.println();
//}
//}