import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.Timer;

public class PlayArea extends JComponent {
    // instance variables
    private final int areaWidth = 600;
    private final int areaHeight = 50;
    private boolean start;
    private RedBar bar;
    private GreenBox box;
    private Timer timer;
    private int score = 0;

     /*
     * Constructor to initialize the play area, with the red bar and green box
     */
    public PlayArea() {
        // initialize instance variables
        start = false;
        bar = new RedBar();
        box = new GreenBox();

        // set initial positions relative to PlayArea dimensions
        bar.setX((areaWidth - bar.getWidth()) / 2);
        randomizeBoxPosition();

        // add components to PlayArea
        add(bar);
        add(box);

        // create a timer to move the bar (lambda expression which calls updateGame method every 1ms)
        timer = new Timer(1, e -> updateGame());
    }

    /*
     * Sets the game to start, sets the bar speed based on given speed, and initializes box and bar positions,
     * and begins timer to start updating game state.
     * 
     * @param speed the speed of the bar
     */
    public void startGame(int speed) {
        // set start to true
        start = true;

        // set bar speed based on difficulty level
        bar.setSpeed(speed);

        // reset bar position and speed
        bar.setX((areaWidth - bar.getWidth()) / 2);
        bar.reverseDirection();

        // randomize box position
        randomizeBoxPosition();

        // start timer
        timer.start();
    }

     /*
     * Randomizes the position of the green box based on the direction of the red bar,
     * also ensuring that the box is within the bounds of the play area.
     */
    public void randomizeBoxPosition() {
        // randomize box position based on bar (also ensures box spawns a little away from bar)
        if (bar.getDirection() == 1) {
            box.setX((int) (Math.random() * (areaWidth - bar.getWidth() - box.getWidth() - bar.getX() - 70)) + bar.getX() + bar.getWidth() + 50);
        } else if (bar.getDirection() == -1) {
            box.setX((int) (Math.random() * (bar.getX() - box.getWidth() - 70)) + 20);
        }

        // make sure box is within bounds
        if (box.getX() < 0) {
            box.setX(0);
        } else if (box.getX() > areaWidth - box.getWidth()) {
            box.setX(areaWidth - box.getWidth());
        }
    }

     /*
     * Checks if the red bar is touching the green box,
     * if so, increments score and reverses direction of bar,
     * otherwise resets game.
     */
    public void check() {
        // Check if the bar and box are touching
        if (bar.getX() + bar.getWidth() >= box.getX() && bar.getX() <= box.getX() + box.getWidth() && start) {
            // increase score and reverse direction of bar
            score++;
            bar.reverseDirection();
            randomizeBoxPosition();
        }
        else { // reset game if not touching
            resetGame();
        }
    }

     /*
     * Resets the game by stopping the timer and resetting score.
     */
    public void resetGame() {
        // stop timer
        timer.stop();

        // set start to false
        start = false;

        // reset score
        score = 0;
    }

     /*
     * Updates the game by updating the bar's position and checking if it is within bounds.
     * If the bar hits the bounds, it reverses direction.
     */
    public void updateGame() {
            // update bar x position
            bar.updateX();

            // check to see if bar is within bounds
            if (bar.getX() >= areaWidth - bar.getWidth()) {
                bar.setX(areaWidth - bar.getWidth());
                bar.reverseDirection();
            } else if (bar.getX() <= 0) {
                bar.setX(0);
                bar.reverseDirection();
            }
            repaint();
    }

     /*
     * Returns the width of the play area.
     * 
     * @return width the width of the play area
     */
    public int getWidth() {
        return areaWidth;
    }

    /*
     * Returns the height of the play area.
     * 
     * @return height the height of the play area
     */
    public int getHeight() {
        return areaHeight;
    }

    /*
     * Returns the game score.
     * 
     * @return score the game score
     */
    public int getScore() {
        return score;
    }

    /*
     * Paints the play area
     * 
     * @param g the graphics object used for drawing
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, areaWidth, areaHeight);
    }
}

class RedBar extends JComponent {
    // instance variables
    private final int width = 20, height = 50;
    private int x;
    private int speed;
    private int direction;

    /*
     * Constructs the bar with initial values for x position, direction, and speed.
     */
    public RedBar() {
        x = 0;
        direction = 1;
        speed = 0;
    }

    /*
     * Sets the x position of the bar and updates its bounds.
     * 
     * @param x the x position of the bar
     */
    public void setX(int x) {
        this.x = x;
        setBounds(x, 0, width, height);
    }

    /*
     * Returns the width of the bar.
     * 
     * @return width the width of the bar
     */
    public int getWidth() {
        return width;
    }

    /*
     * Returns the x position of the bar.
     * 
     * @return x the x position of the bar
     */
    public int getX() {
        return x;
    }

    /*
     * Sets the speed of the bar.
     * 
     * @param speed the speed of the bar
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /*
     * Returns the speed of the bar
     * 
     * @return speed the speed of the bar
     */
    public int getSpeed() {
        return speed;
    }

    /*
     * Returns the direction of the bar.
     * 
     * @return direction the direction of the bar (1 for right, -1 for left)
     */
    public int getDirection() {
        return direction;
    }

    /*
     * Reverses the direction of the bar.
     */
    public void reverseDirection() {
        direction = -direction;
    }

    /*
     * Updates the x position of the bar based on its speed and direction.
     */
    public void updateX() {
        this.x += speed * direction;
    }

    /*
     * Paints the bar
     * 
     * @param g the graphics object used for drawing
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(0, 0, width, height);
    }
}

class GreenBox extends JComponent {
    // instance variables
    private final int width = 70, height = 50;
    private int x;

    /*
     * Constructs the green box with initial x position.
     */
    public GreenBox() {
        x = 0;
    }

    /*
     * Sets the x position of the box and updates its bounds.
     * 
     * @param x the x position of the box
     */
    public void setX(int x) {
        this.x = x;
        setBounds(x, 0, width, height);
    }

    /*
     * Returns the x position of the bar.
     * 
     * @return x the x position of the bar
     */
    public int getX() {
        return x;
    }

    /*
     * Returns the width of the box.
     * 
     * @return width the width of the box
     */
    public int getWidth() {
        return width;
    }

    /*
     * Paints the box
     * 
     * @param g the graphics object used for drawing
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, width, height);
    }
}