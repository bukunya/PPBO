import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ExitDoor {
    private int x, y, width, height;
    private String playerType; // "fireboy" atau "watergirl"

    public ExitDoor(int x, int y, int width, int height, String playerType) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.playerType = playerType;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public String getPlayerType() {
        return playerType;
    }

    public void draw(Graphics g) {
        if ("fireboy".equals(playerType)) {
            g.setColor(Color.RED);
        } else if ("watergirl".equals(playerType)) {
            g.setColor(Color.BLUE);
        } else {
            g.setColor(Color.GRAY); // Default / fallback
        }
        g.fillRect(x, y, width, height);
    }
}
