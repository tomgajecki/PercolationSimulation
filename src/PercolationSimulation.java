import edu.princeton.cs.algs4.StdRandom;

public class PercolationSimulation {
    private static final int GRID_SIZE = 600;  // Size of the grid

    public static void main(String[] args) {
        Percolation percolation = new Percolation(GRID_SIZE);
        PercolationVisualizer visualizer = new PercolationVisualizer(percolation, GRID_SIZE);

        simulatePercolation(percolation, visualizer);
    }

    private static void simulatePercolation(Percolation percolation, PercolationVisualizer visualizer) {
        int openSiteCount = 0;
        int totalSites = GRID_SIZE * GRID_SIZE;

        while (!percolation.percolates()) {
            if (openSiteCount == totalSites) {
                break;  // Exit if all sites are open
            }

            // Open a random blocked site
            openRandomSite(percolation);
            openSiteCount++;

            // Update visualization after each site opening
            visualizer.draw();
            sleep(1);  // Short delay to allow observation of the visualization
        }

        System.out.println("Percolates!");
    }

    private static void openRandomSite(Percolation percolation) {
        int row, col;
        do {
            row = StdRandom.uniformInt(1, GRID_SIZE + 1);
            col = StdRandom.uniformInt(1, GRID_SIZE + 1);
        } while (percolation.isOpen(row, col));  // Find a blocked site

        percolation.open(row, col);
    }

    private static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
