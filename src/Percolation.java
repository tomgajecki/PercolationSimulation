import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int TOP = 0;
    private int n;
    private boolean[][] grid;
    private WeightedQuickUnionUF uf;
    private int openSiteCount;
    private int virtualBottom;

    // Creates an n-by-n grid with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Grid size must be greater than zero.");
        }
        this.n = n;
        grid = new boolean[n][n];
        virtualBottom = n * n + 1; 
        uf = new WeightedQuickUnionUF(n * n + 2); // includes virtual top and bottom
        openSiteCount = 0;
    }

    // Opens the site (row, col) if it is not already open
    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            int index = xyTo1D(row, col);
            grid[row - 1][col - 1] = true;
            openSiteCount++;

            // Connect to virtual top or bottom if applicable
            if (row == 1) {
                uf.union(index, TOP);
            }
            if (row == n) {
                uf.union(index, virtualBottom);
            }

            // Connect to adjacent open sites
            connectAdjacent(row, col, index);
        }
    }

    // Helper method to connect adjacent sites
    private void connectAdjacent(int row, int col, int index) {
        if (row > 1 && isOpen(row - 1, col)) uf.union(index, xyTo1D(row - 1, col));
        if (row < n && isOpen(row + 1, col)) uf.union(index, xyTo1D(row + 1, col));
        if (col > 1 && isOpen(row, col - 1)) uf.union(index, xyTo1D(row, col - 1));
        if (col < n && isOpen(row, col + 1)) uf.union(index, xyTo1D(row, col + 1));
    }

    // Checks if the site (row, col) is open
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row - 1][col - 1];
    }

    // Checks if the site (row, col) is full
    public boolean isFull(int row, int col) {
        validate(row, col);
        return isOpen(row, col) && uf.find(xyTo1D(row, col)) == uf.find(TOP);
    }

    // Returns the number of open sites
    public int numberOfOpenSites() {
        return openSiteCount;
    }

    // Determines if the system percolates
    public boolean percolates() {
        return uf.find(TOP) == uf.find(virtualBottom);
    }

    // Converts 2D pair (row, col) to 1D index
    private int xyTo1D(int row, int col) {
        return (row - 1) * n + (col - 1);
    }

    // Validates that row and col are within bounds
    private void validate(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) {
            throw new IllegalArgumentException("Row or column index out of bounds");
        }
    }
}
