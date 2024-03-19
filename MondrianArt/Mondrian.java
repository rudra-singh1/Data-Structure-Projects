import java.awt.Color;
import java.util.Random;

public class Mondrian {
    private Random random;

    public Mondrian() {
        random = new Random();
    }

    public void paintBasicMondrian(Color[][] pixels){
        paintMondrian(pixels, 0, 0, pixels.length, pixels[0].length, false); 
    }

    public void paintComplexMondrian(Color[][] pixels) {
        paintMondrian(pixels, 0, 0, pixels.length, pixels[0].length, true); 
    }
    
    private void paintMondrian(Color[][] pixels, int x, int y, int width, int height, boolean check){
        if(width < pixels.length / 4 && height < pixels[0].length / 4) {
            // Base case: Stop splitting when region is smaller than one-fourth of the canvas size
            fillRegion(pixels, x + 1, y + 1, width - 2, height - 2, check);
        } else {
            boolean verticalSplit = width >= pixels.length / 4;
            boolean horizontalSplit = height >= pixels[0].length / 4;
            
            if (verticalSplit && horizontalSplit) {
                // Split into four smaller regions
                int splitX = random.nextInt(width - 1) + x + 1;
                int splitY = random.nextInt(height - 1) + y + 1;
                paintMondrian(pixels, x, y, splitX - x, splitY - y, check); // top left
                paintMondrian(pixels, splitX, y, width - (splitX - x), splitY - y, check); // top right
                paintMondrian(pixels, x, splitY, splitX - x, height - (splitY - y), check); // bottom left
                paintMondrian(pixels, splitX, splitY, width - (splitX - x), height - (splitY - y), check); // bottom right
            } else if (verticalSplit) {
                // Split vertically into two regions
                int splitX = random.nextInt(width - 1) + x + 1;
                paintMondrian(pixels, x, y, splitX - x, height, check); // left region
                paintMondrian(pixels, splitX, y, width - (splitX - x), height, check); // right region
            } else if (horizontalSplit) {
                // Split horizontally into two regions
                int splitY = random.nextInt(height - 1) + y + 1;
                paintMondrian(pixels, x, y, width, splitY - y, check); // top region
                paintMondrian(pixels, x, splitY, width, height - (splitY - y), check); // bottom region
            } else {
                // Region is larger than one-fourth of the canvas but cannot be split, fill with color
                fillRegion(pixels, x + 1, y + 1, width - 2, height - 2, check);
            }
        }
    }

    private void fillRegion(Color[][] pixels, int x, int y, int width, int height, boolean check) {
        if (width >= 2 && height >= 2) {
            if (check) {
                // Randomly choose a shape to fill
                int shapeType = random.nextInt(2); //two shapes: rectangle and circle
                if (shapeType == 0) {
                    // Fill as a rectangle
                    fillRectangle(pixels, x, y, width, height);
                } else {
                    // Fill as a circle
                    fillCircle(pixels, x, y, width, height);
                }
            } else {
                // Fills the region with a random color, leaving a 1-pixel border
                Color color = getRandomColor();
                for (int i = x + 1; i < x + width - 1; i++) {
                    for (int j = y + 1; j < y + height - 1; j++) {
                        pixels[i][j] = color;
                    }
                }
            }
        }
    }

    private void fillRectangle(Color[][] pixels, int x, int y, int width, int height) {
        // Fills the region with a rectangle of a random color
        Color color = getRandomColor();
        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                pixels[i][j] = color;
            }
        }
    }

    private void fillCircle(Color[][] pixels, int x, int y, int width, int height) {
        // Fills the region with a circle of a random color
        Color color = getRandomColor();
        int radius = Math.min(width, height) / 2; // Radius of the circle
        int centerX = x + width / 2;
        int centerY = y + height / 2;
    
        // Fill the circle pixels within the bounding box
        for (int i = centerX - radius; i <= centerX + radius; i++) {
            for (int j = centerY - radius; j <= centerY + radius; j++) {
                if ((i - centerX) * (i - centerX) + (j - centerY) * (j - centerY) <= radius * radius) {
                    if (i >= x && i < x + width && j >= y && j < y + height) {
                        pixels[i][j] = color;
                    }
                }
            }
        }
    }

    private Color getRandomColor() {
        // Returns a random color from red, yellow, cyan, and white
        Color[] colors = {Color.RED, Color.YELLOW, Color.CYAN, Color.WHITE};
        int randomIndex = random.nextInt(colors.length);
        return colors[randomIndex];
    }
}

// Behavior:
//   - This class generates random artwork in the style of Mondrian.
// Parameters:
//   - None
// Returns:
//   - None
// Exceptions:
//   - None
public class Mondrian {

    // Behavior:
    //   - Constructor initializes the random number generator.
    // Parameters:
    //   - None
    // Returns:
    //   - None
    // Exceptions:
    //   - None
    public Mondrian() {
        random = new Random();
    }

    // Behavior:
    //   - Generates basic Mondrian-style artwork on a given array of pixels.
    // Parameters:
    //   - pixels: a 2D array representing the canvas for the artwork
    // Returns:
    //   - None
    // Exceptions:
    //   - None
    public void paintBasicMondrian(Color[][] pixels){
        paintMondrian(pixels, 0, 0, pixels.length, pixels[0].length, false); 
    }

    // Behavior:
    //   - Generates complex Mondrian-style artwork on a given array of pixels.
    // Parameters:
    //   - pixels: a 2D array representing the canvas for the artwork
    // Returns:
    //   - None
    // Exceptions:
    //   - None
    public void paintComplexMondrian(Color[][] pixels) {
        paintMondrian(pixels, 0, 0, pixels.length, pixels[0].length, true); 
    }

    // Behavior:
    //   - Recursively generates Mondrian-style artwork on a given region of pixels.
    // Parameters:
    //   - pixels: a 2D array representing the canvas for the artwork
    //   - x, y: coordinates of the top-left corner of the region
    //   - width, height: dimensions of the region
    //   - check: a boolean indicating whether to use complex shapes
    // Returns:
    //   - None
    // Exceptions:
    //   - None
    private void paintMondrian(Color[][] pixels, int x, int y, int width, int height, boolean check) {
        // Method implementation...
    }

    // Behavior:
    //   - Fills a region of pixels with either rectangles or circles, depending on the value of check.
    // Parameters:
    //   - pixels: a 2D array representing the canvas for the artwork
    //   - x, y: coordinates of the top-left corner of the region
    //   - width, height: dimensions of the region
    //   - check: a boolean indicating whether to fill with shapes or random colors
    // Returns:
    //   - None
    // Exceptions:
    //   - None
    private void fillRegion(Color[][] pixels, int x, int y, int width, int height, boolean check) {
        // Method implementation...
    }

    // Behavior:
    //   - Fills a region of pixels with a rectangle of random color.
    // Parameters:
    //   - pixels: a 2D array representing the canvas for the artwork
    //   - x, y: coordinates of the top-left corner of the region
    //   - width, height: dimensions of the region
    // Returns:
    //   - None
    // Exceptions:
    //   - None
    private void fillRectangle(Color[][] pixels, int x, int y, int width, int height) {
        // Method implementation...
    }

    // Behavior:
    //   - Fills a region of pixels with a circle of random color.
    // Parameters:
    //   - pixels: a 2D array representing the canvas for the artwork
    //   - x, y: coordinates of the top-left corner of the region
    //   - width, height: dimensions of the region
    // Returns:
    //   - None
    // Exceptions:
    //   - None
    private void fillCircle(Color[][] pixels, int x, int y, int width, int height) {
        // Method implementation...
    }

        // Behavior:
        //   - Returns a random color from a predefined set.
        // Parameters:
        //   - None
        // Returns:
        //   - Color: a randomly chosen color
    // Exceptions:
    //   - None
    private Color getRandomColor() {
        // Method implementation...
    }
}
