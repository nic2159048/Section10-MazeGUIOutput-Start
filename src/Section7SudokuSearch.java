/***
 * 
 * @author J Zelenski
 * 
 * Adapted from the article written by  J Zelenski. 
 * Outputs the first generated Sudoku Board using 
 * the exhaustive search algorithm learned in class. 
 * 
 */
public class Section7SudokuSearch {
	public static void main(String[] args) {
		// A 9x9 array of ints represents the Sudoku puzzle 
		enumerate(new int[9][9]);

	}
	static public boolean enumerate(int[][] puzzle) {
		// Hold row col of unassigned square 
		DecisionPoint nextMove = findUnassignedLoc(puzzle); 
		
		
		if (nextMove == null) {
			process(puzzle); 
			return true; // return true, puzzle solved 
		}
		
		// Make local decision
		// Avoid enumerating on values that do not work in the solution with noConflicts
		
		// While looping remember to remove last local decision 

		return false; // all decisions tried, no solution found 
	}

	
	private static boolean noConflicts(int row, int col, int [][] puzzle, int num) {
		// TODO Update to check the rows and columns in addition to the subgrid
		return !inSubGrid(puzzle, row - (row%3), col - (col%3), num);
	}

	// Check if the number is in the subgrid already, true if it is, false if not
	private static boolean inSubGrid(int [][] puzzle, int startRow, int startCol, int num) {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (puzzle[row+startRow][col+startCol]  == num) {
					return true; 
				}
			}
		}

		return false;
	}
	
	private static boolean inColumn(int[][] puzzle, int col, int num) {
		// TODO Check the number is not already in the column of the unassigned square
		return false;
	}
	
	private static boolean inRow(int[][] puzzle, int row, int num) {
		// TODO Check the number is not already in the row of the unassigned square
		return false;
	}
	
	private static void process(int[][] puzzle) {
		// TODO Print out the completed puzzle 
		
	}
	
	/*
	 * TODO: Create a method that places the row and column of an empty 
	 * square into the array rc. 
	 * 
	 * Return true if there is an unassigned square and false if the 
	 * suduko puzzle is full. 
	 */
	private static DecisionPoint findUnassignedLoc(int[][] puzzle) {
		return null;
	}
}

