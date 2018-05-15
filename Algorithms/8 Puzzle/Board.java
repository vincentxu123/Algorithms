import java.util.ArrayList;
//import edu.princeton.cs.algs4.MinPQ;


public class Board {
	
	int[][] board;
	int wrongMoves = 0;
	boolean win = false;
	
    public Board(int[][] blocks) {
    	// construct a board from an n-by-n array of blocks
    	// (where blocks[i][j] = block in row i, column j)
    	
    	board = blocks;
    }
    
    public int placement(int row, int col) {
    	return (row - 1) * board.length + col;
    }
    
    public int getRow(int number) {
    	return (number - 1)/board.length;
    }
    
    public int getCol(int number) {
    	return (number - 1) % board.length;
    }
    
    public int getNumber(int row, int col) {
    	return board[row][col];
    }
    
    public void checkMoves() {
    	for (int i = 0; i < board.length; i++) {
    		for (int j = 0; j < board[0].length; j++) {
    			if (board[i][j] != placement(i + 1, j + 1)) {
    				wrongMoves++;
    			}
    		}
    	}
    	if (board[board.length - 1][board.length - 1] == 0) {
    		wrongMoves--;
    	}
    	if (wrongMoves == 0) {
    		win = true;
    	}
    }
                                          
    public int dimension() {
    	// board dimension n
    	return board.length;
    }
    public int hamming()  {
    	// number of blocks out of place
    	checkMoves();
    	return wrongMoves;
    }
    public int manhattan() {
    	// sum of Manhattan distances between blocks and goal
    	int manhattan = 0;
    	for (int i = 0; i < board.length; i++) {
    		for (int j = 0; j < board[0].length; j++) {
    			int number = board[i][j];
    			if (number != placement(i + 1, j + 1) && number != 0) {
    				int x = Math.abs(getRow(number) - i) + Math.abs(getCol(number) - j);
    				manhattan += x;
    			}
    		}
    	}
    	return manhattan;
    }
    
    public boolean isGoal()  {
    	// is this board the goal board?
    	checkMoves();
    	return win;
    }
    public Board twin()  {
    	// a board that is obtained by exchanging any pair of blocks
    	int[][] temp = new int[dimension()][dimension()];
		copyArr(board,temp);
    	int row1 = 0, col1 = 0;
    	int row2 = 1, col2 = 1;
    	if (temp[row1][col1] == 0) {
    		row1 = 1;
    	}
    	if (temp[row2][col2] == 0) {
    		col2 = 0;
    	}
    	swap(temp, row1, col1, row2, col2);
    	return new Board(temp);
    }
    public boolean equals(Object y) {
    	// does this board equal y?
    	if (board.length != ((Board) y).dimension()) {
    		return false;
    	}
    	for (int i = 0; i < board.length; i++) {
    		for (int j = 0; j < board.length; j++) {
    			if (board[i][j] != ((Board) y).getNumber(i, j)) {
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
	public Iterable<Board> neighbors() {
    	// all neighboring boards
		ArrayList<Board> neighbors = new ArrayList<Board>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == 0) {
					if (i > 0) {
						int[][] temp = new int[dimension()][dimension()];
						copyArr(board,temp);
						Board newBoard = new Board(temp);
						newBoard.swap(temp, i, j, i - 1, j);
						neighbors.add(newBoard);
					}
					if (i < board.length - 1) {	
						int[][] temp = new int[dimension()][dimension()];
						copyArr(board,temp);
						Board newBoard = new Board(temp);
						newBoard.swap(temp, i, j, i + 1, j);
						neighbors.add(newBoard);
					}
					if (j > 0) {
						int[][] temp = new int[dimension()][dimension()];
						copyArr(board,temp);
						Board newBoard = new Board(temp);
						newBoard.swap(temp, i, j, i, j - 1);
						neighbors.add(newBoard);
					}
					if (j < board.length - 1) {
						int[][] temp = new int[dimension()][dimension()];
						copyArr(board,temp);
						Board newBoard = new Board(temp);
						newBoard.swap(temp, i, j, i, j + 1);
						neighbors.add(newBoard);
					}
				}
			}
		}
    	return neighbors;
    }
	
	public void swap(int[][] board, int row1, int col1, int row2, int col2) {
		int temp = board[row1][col1];
		board[row1][col1] = board[row2][col2];
		board[row2][col2] = temp;
	}
	
	public void copyArr(int[][] original, int[][] copy) {
		for (int i = 0; i < original.length; i++) {
			for (int j = 0; j < original.length; j++) {
				copy[i][j] = original[i][j];
			}
		}
	}
    
    public String toString()  {
    	// string representation of this board (in the output format specified below)
    	for (int i = 0; i < board.length; i++) {
    		for (int j = 0; j < board[0].length; j++) {
    			System.out.print(board[i][j] + " ");
    		}
    		System.out.println();
    	}
    	System.out.println();
    	return "";
    }

    public static void main(String[] args)  {
    	// unit tests (not graded)
    	int[][] game = new int[3][3];
    	game[0][0] = 8;
    	game[0][1] = 1;
    	game[0][2] = 3;
    	game[1][0] = 4;
    	game[1][1] = 0;
    	game[1][2] = 2;
    	game[2][0] = 7;
    	game[2][1] = 6;
    	game[2][2] = 5;
    	Board board = new Board(game);
    	System.out.println(board);
    	System.out.println(board.neighbors());
    }
}
