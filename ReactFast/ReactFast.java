import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ReactFast extends JFrame implements KeyListener, ActionListener {

    // private instance variables
    private final int width = 800, height = 600;
    private int highscore, speedValue;
    private Text scoreText, highscoreText, controlText;
    private PlayArea playArea;
    private JComboBox<String> speed;
    private JButton button;
    private boolean gameStarted = false;
    private Face canvas;

    /*
     * Constructs game window and initializes components
     */
    public ReactFast() {
        // Call super
        setTitle("React Fast!");

        // Initialize instance variables
        highscore = 0;

        // add keylistener
        addKeyListener(this);

        // initialize play area and set bounds
        playArea = new PlayArea();
        playArea.setBounds(100, 400, playArea.getWidth(), playArea.getHeight());
        
        // initialize text and set bounds
        scoreText = new Text(String.valueOf(playArea.getScore()), new Font("Arial", Font.BOLD, 50), Color.WHITE, 190, 170);
        highscoreText = new Text("Highscore: " + highscore, new Font("Arial", Font.PLAIN, 30), Color.WHITE, 30, 255);
        controlText = new Text("use Space to play", new Font("Arial", Font.PLAIN, 15), Color.WHITE, 170, 235);
        scoreText.setBounds(0, 0, width, height);
        highscoreText.setBounds(0, 0, width, height);
        controlText.setBounds(0, 0, width, height);

        // initalize JButton and set bounds
        button = new JButton("Start");
        button.setBounds(200, 310, 80, 35);
        button.addActionListener(this);
        button.setFocusable(false);
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);

        // initialize JComboBox and set bounds
        String[] difficulty = { "Easy", "Normal", "Hard" };
        speed = new JComboBox<>(difficulty);
        speed.setBounds(520, 310, 80, 35);
        speed.setFocusable(false);
        speed.setBackground(Color.LIGHT_GRAY);
        speed.setForeground(Color.BLACK);

        // initialize canvas and set bounds
        canvas = new Face();
        canvas.setBounds(200, 70, 400, 200);

        // add components
        add(canvas);
        add(scoreText);
        add(highscoreText);
        add(controlText);
        add(playArea);
        add(speed);
        add(button);

        // initialize JFrame
        setLayout(null);
        setSize(width, height);
        getContentPane().setBackground(Color.DARK_GRAY);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /*
     * Runs when a key is pressed. If the space bar is pressed and the game has started,
     * the check() method is called to check if the red bar is in range of the box.
     * The score is updated and the mood is set to sad if the score is 0.
     * The highscore is updated if the score is greater than the highscore.
     * 
     * @param e the event that triggers the method
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // if space bar pressed and game started
        if (e.getKeyCode() == KeyEvent.VK_SPACE && gameStarted) {
            // call method to see if box in range
            playArea.check();
            scoreText.setText(String.valueOf(playArea.getScore()));

            // if score is 0, set mood to sad
            if (playArea.getScore() == 0) {
                canvas.setMood(3);
            }

            // update highscore if necessary
            if (playArea.getScore() > highscore) {
                highscore = playArea.getScore();
                highscoreText.setText("Highscore: " + highscore);
            }

            // repaint to update text
            repaint();
        }
    }

    // unused methods from KeyListener interface
    @Override
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    /*
     * Runs when an action is performed. It checks if the source is the button.
     * If the button is pressed, it starts or restarts the game.
     * If starting, it changes to the reset button and sets the mood to happy,
     * starting the game with the selected speed.
     * If restarting, it resets the game and sets the mood to neutral.
     * 
     * @param e the event that triggers the method
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            if (button.getText().equals("Start")) { // if start button pressed
                // disable button and combobox, and change to restart
                speed.setEnabled(false);
                button.setText("Restart");

                // set mood to happy
                canvas.setMood(1);

                // get speed from JComboBox
                if (speed.getSelectedIndex() == 0) {
                    speedValue = 3;
                } else if (speed.getSelectedIndex() == 1) {
                    speedValue = 8;
                } else if (speed.getSelectedIndex() == 2) {
                    speedValue = 12;
                }

                // start the game
                gameStarted = true;
                playArea.startGame(speedValue);
            }
            else if (button.getText().equals("Restart")) { // if restart button pressed
                // reset game
                gameStarted = false;
                playArea.resetGame();
                scoreText.setText(String.valueOf(playArea.getScore()));

                // set mood to neutral
                canvas.setMood(2);

                // reset button and enable combobox
                button.setText("Start");
                speed.setEnabled(true);
            }
        } 

        // repaint to update text
        repaint();
    }

    public static void main(String[] args) {
        new ReactFast();
    }
}