import java.util.Random;

public class Part1E {
	public static int count = 0;
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
	
	public static void doSomething(int[] arry) {
		int N = arry.length/2;
		count = 0;
		for(int i = N; i >= 0; i--) {
			foo(arry, i);
		}
		System.out.print(count);
	}
	
	public static void foo(int[] myArray, int i) {
		int l = 2*i+1;
		int r = 2*i+2;
		int N = myArray.length;
		if(l >= N  && r >= N) {
			return;
		}
		if(r >= N) {
			count+=2;
			if(myArray[i] < myArray[l]) {
				swap(myArray, i, l);
				count+= 4;
			}
			return;
		}
		count+=2;
		if(myArray[r] > myArray[l] && myArray[r]> myArray[i]) {
			swap(myArray,i,r);
			count += 6;
			foo(myArray,r);
			return;
		}
		count+=2;
		if(myArray[l] > myArray[i]) {
			swap(myArray,i,l);
			count += 4;
			foo(myArray,l);
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
	
	public static int[] bestArray(int N) {
		int[] newArray = new int[N];
		for(int index = 0; index < N; index++) {
			newArray[index] = N-1-index;	
		}
		return newArray;
	}
	
	public static int[] worstArray(int N) {
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
	
	public static void printArray(int[] myArray) {
		for(int i = 0; i < myArray.length;i++) {
			System.out.print(myArray[i]);
		}
	}

}
