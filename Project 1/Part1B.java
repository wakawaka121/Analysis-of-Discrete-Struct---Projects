
public class Part1B {

	public static void main(String[] args) {
		for(int arg = 0; arg < args.length; arg++) {
			int N = Integer.valueOf(args[arg]);
			int count = 0;
			int sum = 0;
			while(N > 0) {
				for(int j = 1; j <= N; j++) {
					for(int k = 1; k <= j; k++) {
						sum += j+k;
						count++;
					}
				}
				N = N/3;
			}
			System.out.println(count);
		}
	}

}
