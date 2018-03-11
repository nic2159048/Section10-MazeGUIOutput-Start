/***
 * 
 * @author J Zelenski
 * 
 * Adapted from the article written by  J Zelenski. 
 * Output the first generated Sudoku Board
 */
public class Section7SudokuSearch {
	public static void main(String[] args) {
		// A 9x9 array of ints represents the Sudoku puzzle 
		enumerate(new int[9][9]);
	}
	
	//Covers all sudoku combinations
	static public boolean enumerate(int[][] puzzle) {
		DecisionPoint nextMove = findUnassignedLoc(puzzle); 
		
		// Find row col of unassigned cell and check if solution found
		if (nextMove == null) {
			process(puzzle); 
			return true; // return true, puzzle solved 
		}
		
		// Retrieve local decision point (unassigned row col/empty square) 
		int row = nextMove.row;
		int col = nextMove.col;
		
		// Make local decision
		for (int i = 1; i <= 9; i++) {//9 values valid in sudoku 
			// if no conflict with placing i value in local decision point 
			if (noConflicts(row, col, puzzle, i)) {
				//Set row column to possible Sudoku solution val  
				puzzle[row][col]  = i; 
				//Nested conditionals remain so enumerate call can be seen 
				if (enumerate(puzzle)) {
					return true; //if solution found, return true 
				}
				//else remove last decision and try another 
				puzzle[row][col]  = 0; 
			}
		}
		return false; // all decisions tried, no solution found 
	}
	
	private static boolean noConflicts(int row, int col, int [][] puzzle, int num) {
		return !inRow(puzzle, row, num) && !inColumn(puzzle, col, num) && !inSubGrid(puzzle, row - (row%3), col - (col%3), num);
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
	
	// Check if the number is in the column already 
	private static boolean inColumn(int [][] puzzle, int col, int num) {
		for (int row = 0; row < puzzle.length; row++) {
			if (puzzle[row][col]  == num) {
				return true; 
			}
		}

		return false;
	}

	// Check if the number is in the row already 
	private static boolean inRow(int [][] puzzle, int row, int num) {
		for (int col = 0; col < puzzle[row].length; col++) {
			if (puzzle[row][col]  == num) {
				return true; 
			}
		}

		return false; 
	}

	// Return an array that holds the row and column of the first empty square found 
	private static DecisionPoint findUnassignedLoc(int [][] puzzle) {
		DecisionPoint emptySquare = null; 
		for (int r = 0; r < puzzle.length; r++) {
			for (int c = 0; c < puzzle[0].length; c++) {
				if (puzzle[r][c]  == 0 ) {
					emptySquare = new DecisionPoint(r, c); 
					return emptySquare; 
				}
			}
		}
		return emptySquare; //Puzzle is full 
	}

	//Output the final solution of the Sudoku Puzzle  
	public static void process(int [][] puzzle) {
		for (int r = 0; r < puzzle.length; r++) {
			for (int c = 0; c < puzzle[r].length; c++) {
				System.out.print(puzzle[r][c] );
			}
			System.out.println();
		}
	}
	

}

