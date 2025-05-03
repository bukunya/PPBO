import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {
    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 500;
    private static final int DELAY = 16; // Approximately 60 FPS

    private Timer timer;
    private Player player1;
    private Player player2;
    private ArrayList<Platform> platforms;
    private InputHandler inputHandler;

    public GamePanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(new Color(135, 206, 235)); // Sky blue
        setFocusable(true);

        // Initialize input handler
        inputHandler = new InputHandler();
        addKeyListener(inputHandler);

        // Initialize players
        player1 = new Player(100, 100, Color.RED,
                InputHandler.Key.A, InputHandler.Key.D, InputHandler.Key.W, InputHandler.Key.S);
        player2 = new Player(200, 100, Color.BLUE,
                InputHandler.Key.LEFT, InputHandler.Key.RIGHT, InputHandler.Key.UP, InputHandler.Key.DOWN);

        // Initialize platforms
        platforms = new ArrayList<>();
        createPlatforms();

        // Initialize timer
        timer = new Timer(DELAY, this);
    }

    private void createPlatforms() {
        // Main ground
        platforms.add(new Platform(0, PANEL_HEIGHT - 30, PANEL_WIDTH, 30));

        // Platforms at different elevations
        platforms.add(new Platform(100, 400, 200, 20));
        platforms.add(new Platform(400, 350, 150, 20));
        platforms.add(new Platform(600, 300, 150, 20));
        platforms.add(new Platform(200, 250, 100, 20));
        platforms.add(new Platform(350, 200, 80, 20));
        platforms.add(new Platform(500, 150, 200, 20));
        platforms.add(new Platform(100, 100, 150, 20));
    }

    public void startGameLoop() {
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    private void update() {
        // Update player 1 movement based on input
        player1.setVelX(0);
        if (inputHandler.isKeyPressed(player1.getLeftKey())) {
            player1.setVelX(-player1.getSpeed());
        }
        if (inputHandler.isKeyPressed(player1.getRightKey())) {
            player1.setVelX(player1.getSpeed());
        }
        if (inputHandler.isKeyPressed(player1.getUpKey()) && !player1.isJumping() && player1.isGrounded()) {
            player1.setJumping(true);
            player1.setGrounded(false);
            player1.setVelY(-15);
        }

        // Update player 2 movement based on input
        player2.setVelX(0);
        if (inputHandler.isKeyPressed(player2.getLeftKey())) {
            player2.setVelX(-player2.getSpeed());
        }
        if (inputHandler.isKeyPressed(player2.getRightKey())) {
            player2.setVelX(player2.getSpeed());
        }
        if (inputHandler.isKeyPressed(player2.getUpKey()) && !player2.isJumping() && player2.isGrounded()) {
            player2.setJumping(true);
            player2.setGrounded(false);
            player2.setVelY(-15);
        }

        // Update both players
        player1.update(platforms, PANEL_WIDTH, PANEL_HEIGHT);
        player2.update(platforms, PANEL_WIDTH, PANEL_HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw platforms
        for (Platform platform : platforms) {
            platform.draw(g);
        }

        // Draw players
        player1.draw(g);
        player2.draw(g);
    }
}
