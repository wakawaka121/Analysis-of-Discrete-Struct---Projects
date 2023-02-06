
public class Part1A {

	public static void main(String[] args) {
		for(int arg = 0; arg < args.length; arg++) {
			int N = Integer.valueOf(args[arg]);
			int count = 0;
			for(int i =1; i <= N; i++) {
				int j = i;
				while (j >= 1) {
					int k = 1;
					while(k <= N) {
						k = k*2;
						count++;
					}
					j--;
				}
			}
			System.out.println(count);
		}
	
	}

}
