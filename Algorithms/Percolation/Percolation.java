
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private final WeightedQuickUnionUF uf;
	private final boolean[] tube;
	private final int size;
	
	public Percolation(int n)    {
		// create n-by-n grid, with all sites blocked
		if (n < 1) {
			throw new IllegalArgumentException("Greater than 0");
		}
		size = n;
		tube = new boolean[size * size];
		uf = new WeightedQuickUnionUF(size * size + 2);
		// System.out.println(Arrays.toString(tube));
	}
	public  void open(int row, int col) {
		// open site (row, col) if it is not open already
		check(row, col);
		int temp = calculation(row, col);
		tube[calculation(row, col)] = true;
		if (row > 1 && isOpen(row - 1, col)) {
			uf.union(temp, calculation(row - 1, col));
			
		} 
		if (row < size && isOpen(row + 1, col)) {
			uf.union(temp, calculation(row + 1, col));
			
		} 
		if (col > 1 && isOpen(row, col - 1)) {
			uf.union(temp, calculation(row, col - 1));
			
		} 
		if (col < size && isOpen(row, col + 1)) {
			uf.union(temp, calculation(row, col + 1));
		}
		
		if (row == 1) {
			uf.union(0, temp);
		} else if (row == size) {
			uf.union(size * size + 1, temp);
		}
		
		
	}
	public boolean isOpen(int row, int col) {
		   // is site (row, col) open?
		check(row, col);
		return (tube[calculation(row, col)]);
	}
	public boolean isFull(int row, int col) {
		// is site (row, col) full?
		check(row, col);
		return (uf.connected(0, calculation(row, col)) && isOpen(row, col));
	}
	public int numberOfOpenSites() {
		// number of open sites
		int count = 0;
		for (int i = 0; i < tube.length; i++) {
			if (tube[i]) {
				count++;
			}
		}
		return count;
	}
	public boolean percolates()   {
		// does the system percolate?
		return uf.connected(0, size * size + 1);
	}
	
	private void printState() {
		for (int i = 1; i < tube.length + 1; i++) {
			System.out.print(tube[i - 1] + " ");
			if (i % size == 0) {
				System.out.println();
			}
		}
		System.out.println();
	}
	
	private void check(int row, int col) {
		if (row > size || col > size || row < 1 || col < 1) {
			throw new IllegalArgumentException("Out of range");
		}
	}
	
	private int calculation(int x, int y) {
		return (x - 1) * size + y - 1;
	}
	
	
	
	public static void main(String[] args) {
		// test client (optional)
		Percolation perc = new Percolation(3);
		perc.open(3, 3);
		perc.open(2, 1);
		perc.open(2, 2);
		perc.open(1, 2);
		perc.open(1, 3);
		perc.open(1, 1);
		perc.open(2, 3);
		perc.printState();		
		System.out.println(perc.percolates());
		
	}
}
