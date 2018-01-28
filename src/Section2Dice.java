/*
 * Section 2 - Part 2
 * 
 * Output all of the possible dice combinations
 * when rolling N six-sided dice.
 */
public class Section2Dice {
	public static void main(String[] args) {
        if (args.length < 1) {
            System.err.print("usage: java Section2Dice N");
            System.exit(1);
        }

        // Five dice game
        int maxDice = Integer.parseInt(args[0]);
        enumerate(maxDice, 0, new int[maxDice]);
	}
	
	//Covers all dice combinations 
	static public void enumerate(int maxDice, int curRoll, int[] allRolls) {
		//Last dice roll, output combinations 
		if (curRoll == maxDice) {
			process(allRolls);
			return;
		}
        for (int i = 1; i <= 6; i++) {// Six sided die
			//Set current roll 
			allRolls[curRoll] = i; 
			
			//Enumerate over the later rolls 
			enumerate(maxDice, curRoll +1, allRolls);
		}
	}
	
	//Output the final combination of rolls 
	public static void process(int[] allRolls) {
		for (int dice : allRolls) {
			System.out.print(dice);
		}
		System.out.println();
	}
}
