import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {
	 
	private final int t;
	private final double[] arr;
	
	public PercolationStats(int n, int trials) {
		// perform trials independent experiments on an n-by-n grid
		arr = new double[trials];
		Percolation perc;
		t = trials;
		for (int i = 0; i < trials; i++) {
			double count = 0;
			perc = new Percolation(n);
			while (!perc.percolates()) {
				int row = (int) (Math.random() * n + 1);
				int col = (int) (Math.random() * n + 1);
				while (perc.isOpen(row, col)) {
					row = (int) (Math.random() * n + 1);
					col = (int) (Math.random() * n + 1);
				}
				// System.out.println(row + " " + col);
				perc.open(row, col);
				// perc.printState();
				count++;
	
			}
			
			double avg = (count/(n*n));
			arr[i] = avg;
			// System.out.println("It took " + count + " opens to get perculation, fraction is: " + avg);
		}
	}
	
	
	public double mean() {
		// sample mean of percolation threshold
		return StdStats.mean(arr);
	}
	public double stddev() {
		// sample standard deviation of percolation threshold
		return StdStats.stddev(arr);
	}
	public double confidenceLo() {
		// low  endpoint of 95% confidence interval
		return mean() - (1.96 * stddev()/Math.sqrt(t));
	}
	public double confidenceHi() {
		// high endpoint of 95% confidence interval
		return mean() + (1.96 * stddev()/Math.sqrt(t));
	}

	public static void main(String[] args) {
		// test client (described below)
		PercolationStats test = new PercolationStats(200, 100);	
		System.out.println("The mean is: " + test.mean());
		System.out.println("The standard deviation is: " + test.stddev());
		System.out.println("95% confidence intervaL: [" + test.confidenceLo() + ", " + test.confidenceHi() + "]");
	}
}
