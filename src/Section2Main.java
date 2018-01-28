
import java.util.ArrayList;

public class Section2Main {

	public static void main(String[] args) {
		int N=5;
		//enumerate(N,0,new int[5]);
		bowling(); 
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
	
	private static void bowling() {
		int frameNum = 0; 
		int shotInFrame = 0; 
		int prevScore = 0; 
		
		playGame(new int[7], frameNum, shotInFrame, prevScore);
		
	}

	private static void playGame(int[] allScores, int frameNum, int shotInFrame, int prevScore) {
		if (frameNum == 3) {
			return; 
		}
		if (frameNum == 2) {
			lastFrame(allScores); //The third frame is treated differently 
		}
		
		if(shotInFrame == 2) {
			shotInFrame = 0; 
			prevScore = 0; 
			frameNum ++; 
		}
		
		for (int shotScore : possibleShots(prevScore)) {
			//update proper allscores to shotscore
			allScores[2*frameNum + shotInFrame] = shotScore; 
			playGame(allScores, frameNum, shotInFrame + 1, shotScore);
		}
		
	}
	
	private static int[] possibleShots(int prevScore) {
		int[] pinsRemaining = new int[11-prevScore];
		for (int i = 0; i < pinsRemaining.length; i++) {
			pinsRemaining[i] = i; 
		}
		return pinsRemaining; 
	}
	private static void lastFrame(int[] scores) {
		for (int roll = 0; roll <= 10; roll++) {
			scores[4] = roll; //Frame 3, roll 1 
			if (roll == 10) { //roll1 strike 
				for (int roll2 = 0; roll2 <= 10; roll2++) {
					scores[5] = roll2; //Frame 3, roll 2 
					if (roll2 == 10) { //roll2 strike 
						for (int roll3 = 0; roll3 <= 10; roll3++) {
							scores[6] = roll3; //Frame 3, roll 3 
							outputScore(scores); 
						}
					}
					else {
						for (int shotScore : possibleShots(roll2)) {
							scores[6] = shotScore; 
							outputScore(scores); 
						}
					}
				}
			}
			//first roll of third frame not a strike
			else {
				for (int roll2 : possibleShots(roll)) {
					scores[5] = roll2;
					
					//Check for spare 
					if ((roll + roll2) == 10) { 
						for (int roll3 = 0; roll3 <=10; roll3++) {
							scores[6] = roll3;
							outputScore(scores);
						}
					}
					//no spare, no third roll 
					else { 
						scores[6] = 0;
						outputScore(scores);
					}
					
				}
			}	
		}
	}
	private static void outputScore(int[] allScores) {
		for (int roll : allScores) {
			System.out.print(roll + " ");
		}
		System.out.println();
	
		for (int roll : allScores ) {
		//Total scores 
		}
	}
}

