import java.util.Random;

public class Part1D {

	public static void main(String[] args) {
		for(int arg = 0; arg < args.length; arg++) {
			int N = Integer.valueOf(args[arg]);
			int[] bestArray = bestArray(N);
			int[] worstArray = worstArray(N);
			int[] randomArray = randomArray(N);
			doSomething(bestArray);
			System.out.println(": for Best Case N = " + N);
			doSomething(worstArray);
			System.out.println(": for Worst Case N = " + N);
			doSomething(randomArray);
			System.out.println(": for Random Case N = " + N);
		}
	}
	
	public static int[] randomArray(int N) {
		int[] newArray = new int[N];
		Random rand = new Random();
		for(int index = 0; index < N; index++) {
			newArray[index] = rand.nextInt(N*10);	
		}
		return newArray;
	}
	
	public static int[] worstArray(int N) {
		int[] newArray = new int[N];
		for(int index = 0; index < N; index++) {
			newArray[index] = N-1-index;	
		}
		return newArray;
	}
	
	public static int[] bestArray(int N) {
		int[] newArray = new int[N];
		for(int index = 0; index < N; index++) {
			newArray[index] = index;	
		}
		return newArray;
	}
	
	public static void swap(int[] myArray,int first, int second) {
		int value1 = myArray[first];
		int value2 = myArray[second];
		myArray[first] = value2;
		myArray[second] = value1;
	}
		
	public static void doSomething(int[] arry) {
		int count = 0;
		for(int i = 1; i <= arry.length-1; i++) {
			int j = i;
			int k = j-1;
			if(k >=0) {
				//add to accesses if while will not short circuit
				count+=2;
			}
			while(k >= 0 && arry[j]<arry[k]) {
				swap(arry,j,k);
				j--;
				k--;
				count+= 4;
			}
		}
		System.out.print(count);
	}

}
