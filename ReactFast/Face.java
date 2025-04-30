import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Face extends JPanel {
    // instance variables
    private int mood;

     /*
     * Constructor to initialize the drawing
     */
    public Face() {
        // Set the background color of the canvas
        setPreferredSize(new Dimension(400, 200));

        // initalize mood to neutral
        mood = 2;
    }

     /*
     * Sets the mood which will determine the face drawn
     * 
     * @param mood the mood of the face (1 - happy, 2 - neutral, 3 - sad)
     */
    public void setMood(int mood) {
        this.mood = mood;
        repaint(); // Repaint the canvas to reflect the change
    }

     /*
     * Paints the face based on the mood, also changing background
     * 
     * @param g the graphics object used for drawing
     */
    public void paint(Graphics g) {
        super.paint(g);

        // draw a face based on mood
        if (mood == 1) {
            setBackground(Color.GREEN);
        } 
        else if (mood == 2) {
            setBackground(Color.ORANGE);
        }
        else {
            setBackground(Color.RED);
        }

        // draw base of face
        g.setColor(Color.YELLOW);
        g.fillOval(150, 50, 100, 100); 

        // draw eyes
        g.setColor(Color.BLACK);
        g.fillOval(170, 80, 20, 20);
        g.fillOval(210, 80, 20, 20);

        // draw mouth based on mood
        if (mood == 1) {
            g.drawArc(170, 90, 60, 40, -40, -100);
        } 
        else if (mood == 2) {
            g.fillRect(175, 120, 50, 1);
        }
        else {
            g.drawArc(170, 120, 60, 40, 40, 100);
        }
    }
}