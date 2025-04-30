
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Text extends JLabel {
    // instance variables
    private String text;
    private Font font;
    private int x, y;
    private Color colour;

     /*
     * Constructor to initialize the drawing
     * 
     * @param text the text to be displayed
     * @param font the text font
     * @param colour the text colour
     * @param x the x value of the text
     * @param y the y value of the text
     */
    public Text(String text, Font font, Color colour, int x, int y) {
        this.text = text;
        this.font = font;
        this.colour = colour;
        this.x = x;
        this.y = y;
    }

    /*
     * Sets the text to be displayed
     * 
     * @param text the text to be displayed
     */
    public void setText(String text) {
        this.text = text;
    }

    /*
     * Returns the x value of the text
     * 
     * @return x the x value of the text
     */
    public int getX() {
        return x;
    }

    /*
     * Returns the y value of the text
     * 
     * @return y the y value of the text
     */
    public int getY() {
        return y;
    }

     /*
     * Returns the text font
     * 
     * @return font the text font
     */
    public Font getFont() {
        return font;
    }

     /*
     * Returns the text colour
     * 
     * @return colour the text colour
     */
    public Color getColour() {
        return colour;
    }

     /*
     * Draws text
     * 
     * @param g the graphics object used for drawing
     */
    public void paint(Graphics g) {
        g.setColor(this.getColour());
        g.setFont(this.getFont());
        g.drawString(text, x, y);
    }
}