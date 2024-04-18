import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final double[] thresholds;
    private final int trials;
    private final double mean;
    private final double stddev;

    public PercolationStats(int n, int trials) {
        validate(n, trials);
        this.trials = trials;
        this.thresholds = new double[trials];
        performTrials(n);
        this.mean = StdStats.mean(thresholds);
        this.stddev = StdStats.stddev(thresholds);
    }

    private void validate(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Both 'n' and 'trials' must be greater than 0.");
        }
    }

    private void performTrials(int n) {
        for (int trial = 0; trial < trials; trial++) {
            thresholds[trial] = performTrial(n);
        }
    }

    private double performTrial(int n) {
        Percolation percolation = new Percolation(n);
        int openSites = 0;
        while (!percolation.percolates()) {
            if (openSites == n * n) {
                break; // All sites are already open
            }
            openRandomSite(percolation, n);
            openSites++;
        }
        return (double) percolation.numberOfOpenSites() / (n * n);
    }

    private void openRandomSite(Percolation percolation, int n) {
        int row, col;
        do {
            row = StdRandom.uniformInt(1, n + 1);
            col = StdRandom.uniformInt(1, n + 1);
        } while (percolation.isOpen(row, col));
        percolation.open(row, col);
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return mean - (CONFIDENCE_95 * stddev / Math.sqrt(trials));
    }

    public double confidenceHi() {
        return mean + (CONFIDENCE_95 * stddev / Math.sqrt(trials));
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Please provide two integer arguments: n (grid size) and trials (number of trials).");
        }
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);

        System.out.println("Mean                    = " + stats.mean());
        System.out.println("Stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
}
