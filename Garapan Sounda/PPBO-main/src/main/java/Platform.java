import java.awt.Color;
import java.awt.Graphics;

public class Platform {
    private int x, y;
    private int width, height;

    public Platform(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        // Draw main platform (dirt)
        g.setColor(new Color(101, 67, 33)); // Brown color
        g.fillRect(x, y, width, height);

        // Draw grass on top
        g.setColor(new Color(124, 252, 0)); // Bright green
        g.fillRect(x, y, width, 5);
    }

    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
