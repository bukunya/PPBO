import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Player {
    private int x, y;
    private int width = 30;
    private int height = 50;
    private int velX = 0;
    private int velY = 0;
    private int speed = 5;
    private boolean jumping = false;
    private boolean grounded = false;
    private Color color;

    // Control keys
    private InputHandler.Key leftKey;
    private InputHandler.Key rightKey;
    private InputHandler.Key upKey;
    private InputHandler.Key downKey;

    public Player(int x, int y, Color color,
                  InputHandler.Key leftKey, InputHandler.Key rightKey,
                  InputHandler.Key upKey, InputHandler.Key downKey) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;
        this.downKey = downKey;
    }

    public void update(ArrayList<Platform> platforms, int screenWidth, int screenHeight) {
        // Apply gravity
        velY += 1;

        // Apply velocities
        x += velX;
        y += velY;

        // Apply boundary constraints
        if (x < 0) {
            x = 0;
        }
        if (x > screenWidth - width) {
            x = screenWidth - width;
        }

        // Check ground collision
        grounded = false;

        // Check platform collisions
        for (Platform platform : platforms) {
            Rectangle playerRect = new Rectangle(x, y, width, height);
            Rectangle platformRect = new Rectangle(platform.getX(), platform.getY(),
                    platform.getWidth(), platform.getHeight());

            if (playerRect.intersects(platformRect)) {
                Rectangle intersection = playerRect.intersection(platformRect);

                // Coming from above (landing on platform)
                if (intersection.width >= intersection.height && velY > 0 &&
                        y + height - intersection.height <= platform.getY()) {
                    y = platform.getY() - height;
                    velY = 0;
                    grounded = true;
                    jumping = false;
                }
                // Coming from below (hitting platform from below)
                else if (intersection.width >= intersection.height && velY < 0) {
                    y = platform.getY() + platform.getHeight();
                    velY = 0;
                }
                // Hit from left
                else if (intersection.width < intersection.height && velX > 0) {
                    x = platform.getX() - width;
                }
                // Hit from right
                else if (intersection.width < intersection.height && velX < 0) {
                    x = platform.getX() + platform.getWidth();
                }
            }
        }

        // Check bottom screen boundary
        if (y >= screenHeight - height) {
            y = screenHeight - height;
            velY = 0;
            grounded = true;
            jumping = false;
        }
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    // Getters and setters
    public int getSpeed() {
        return speed;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isGrounded() {
        return grounded;
    }

    public void setGrounded(boolean grounded) {
        this.grounded = grounded;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public InputHandler.Key getLeftKey() {
        return leftKey;
    }

    public InputHandler.Key getRightKey() {
        return rightKey;
    }

    public InputHandler.Key getUpKey() {
        return upKey;
    }

    public InputHandler.Key getDownKey() {
        return downKey;
    }
}
