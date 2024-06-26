import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

public class PercolationVisualizer extends JFrame {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 800;
    private static final int MAX_GRID_DIMENSION = 750;

    private final Percolation percolation;
    private final int gridSize;
    private final int cellSize;  // Dynamically calculated based on grid size

    public PercolationVisualizer(Percolation percolation, int gridSize) {
        this.percolation = percolation;
        this.gridSize = gridSize;
        this.cellSize = MAX_GRID_DIMENSION / gridSize;

        initializeWindow();
    }

    private void initializeWindow() {
        setTitle("Percolation Visualization");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(new GridPanel());
        setVisible(true);
    }

    class GridPanel extends JPanel {
        private final Random random = new Random();
        private final Map<Point, Color> colorMap = new HashMap<>();

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawGrid(g);
            displayOpenSitePercentage(g);
        }

        private void drawGrid(Graphics g) {
            int startX = (WINDOW_WIDTH - gridSize * cellSize) / 2;
            int startY = (WINDOW_HEIGHT - gridSize * cellSize) / 2;

            for (int row = 1; row <= gridSize; row++) {
                for (int col = 1; col <= gridSize; col++) {
                    int x = startX + (col - 1) * cellSize;
                    int y = startY + (row - 1) * cellSize;
                    Point point = new Point(row, col);
                    g.setColor(getCellColor(point));
                    g.fillRect(x, y, cellSize, cellSize);
                }
            }
        }

        private Color getCellColor(Point point) {
            if (!percolation.isOpen(point.x, point.y)) {
                return Color.BLACK;
            } else if (percolation.isFull(point.x, point.y)) {
                return colorMap.computeIfAbsent(point, p -> getRandomColor());
            } else {
                return Color.WHITE;
            }
        }

        private Color getRandomColor() {
            float hue = random.nextFloat();
            float saturation = 0.5f + random.nextFloat() * 0.5f;
            float brightness = 0.7f + random.nextFloat() * 0.3f;
            return Color.getHSBColor(hue, saturation, brightness);
        }

        private void displayOpenSitePercentage(Graphics g) {
            int totalSites = gridSize * gridSize;
            int openSites = percolation.numberOfOpenSites();
            double percentageOpen = ((double) openSites / totalSites) * 100;
            
            // Set font color to black and increase font size
            g.setColor(Color.BLACK);
            g.setFont(new Font("SansSerif", Font.BOLD, 20));  // Set the font to SansSerif, Bold, size 20
            
            // Prepare the text to display
            String text = String.format("Open Sites: %.2f%%", percentageOpen);
            
            // Calculate the x position to center the text at the bottom of the panel
            int textWidth = g.getFontMetrics().stringWidth(text);
            int x = (WINDOW_WIDTH - textWidth) / 2;
            int y = WINDOW_HEIGHT - 30; // Position the text 30 pixels above the bottom of the window
            
            // Draw the string
            g.drawString(text, x, y);
        }
    }

    public void draw() {
        repaint();
    }
}
