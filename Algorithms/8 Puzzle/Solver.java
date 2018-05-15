
import java.util.ArrayList;
import java.util.Iterator;


import edu.princeton.cs.algs4.MinPQ;


public class Solver {
	Node searchNode;
	int moves = 0;
	int priority = 0;
	MinPQ<Node> pq = new MinPQ<Node>();
	MinPQ<Node> pq2 = new MinPQ<Node>();
	ArrayList<Board> solutionSet = new ArrayList<Board>();
	boolean solvable = true;;
	
    public Solver(Board initial) {
    	// find a solution to the initial board (using the A* algorithm)
    	pq.insert(new Node(initial, moves, null));
    	searchNode = pq.min();
    	while (!pq.min().board.isGoal()) {
    	//for (int j = 0; j < 7; j++) {
    		//solutionSet.add(pq.min().board);
    		ArrayList<Board> neighbor = (ArrayList<Board>) pq.min().board.neighbors();
    		pq.delMin();
    		moves++;
    		for (int i = 0; i < neighbor.size(); i++) {
    			Node temp = new Node(neighbor.get(i), moves, searchNode);
    			if (moves == 1) {
    				pq.insert(temp);
    			} else {
    				if (!temp.equals(searchNode.before)) {
    					pq.insert(temp);
    				}
    			}
    		}
    		//System.out.println(pq.min());
    		//printQueue();
    		//System.out.println("NEXT MOVE");
    		//searchNode = new Node(pq.min().board, moves, searchNode);
    		searchNode.before = searchNode;
    		
    	//}
    	}
    	//solutionSet.add(pq.min().board);
    	System.out.println("\nGoal: ");
    	//System.out.println(pq.size());
   
    }
    
    public void addSolutions() {
    	ArrayList<Board> sol = new ArrayList<Board>();
    	while (searchNode != null) {
    		System.out.println(searchNode);
    		sol.add(0, searchNode.board);
    		searchNode = searchNode.before;
    	}
    	
    	for (int i = 0; i < sol.size(); i++) {
    		System.out.println(sol.get(i));
    	}
    	System.out.println("moves: " + (sol.size() - 1));
    }
    
    private class Node implements Comparable<Node> {
    	Board board;
    	Node before;
    	int moves = 0;
    	public Node(Board input, int move, Node previous) {
    		board = input;
    		before = previous;
    		moves = move;
    	}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			if (board.manhattan() + moves == o.board.manhattan() + o.moves) {
				return 0;
			} else if (board.manhattan() + moves < o.board.manhattan() + o.moves) {
				return -1;
			} else {
				return 1;
			}
		}
		
		public boolean equals(Node o) {
			if (board.equals(o.board)) {
				return true;
			}
			return false;
		}
		
		public String toString() {
			System.out.println("Manhattan: " + board.manhattan() + "   moves: " + moves);
			System.out.println(board);
			return "";
		}
		
    }
    
    
    public boolean isSolvable() {
    	// is the initial board solvable?
    	return solvable;
    }
    public int moves()  {
    	// min number of moves to solve initial board; -1 if unsolvable
    	return moves;
    }
    public Iterable<Board> solution() {
    	// sequence of boards in a shortest solution; null if unsolvable
    	//addSolutions();
    	return solutionSet;
    }
    
    public void printQueue() {
    	Iterator itr = pq.iterator();
    	while (itr.hasNext()) {
 		   System.out.println(itr.next());
 	   }
    }
    
    public static void main(String[] args) {
    	// solve a slider puzzle (given below)
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
    	Solver solve = new Solver(board);
    	System.out.println(solve.solution());
    }
}