
public class Section2Main {

	public static void main(String[] args) {
		int N=5;
		enumerate(N,0,new int[5]);
		bowling(); 
	}
	
	private static void bowling() {
		int totalFrames = 3; 
		
		score(totalFrames, 0, new int[3]);
		
	}

	private static void score(int totalFrames, int roll, int[] score) {
		//if ()
		
	}

	// Enumerate over all binary numbers from 0 to 2^N - 1
	// FIXME: This comment is not quite right.
	
	// k is the current bit
	static public void enumerate(int N, int k, int[] a) {
		// base case
		if (k==N) {
			process(a);
			return;
		}
		
		// set the current bit
		a[k]=0;
		// enumerate over the later bits trying all combinations
		enumerate(N,k+1,a);
		
		// set the current bit to the other option
		a[k]=1;
		// enumerate over the later bits trying all combinations
		enumerate(N,k+1,a);
		return;
	}
	
	public static void process(int[] binary_rep) {
		for (int bit : binary_rep) {
			System.out.print(bit);
		}
		System.out.println();
	}
	
	

}

