import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

// Defining a class to represent a visual health bar for the player
public class HealthBar extends JComponent {
    // Declaring variables for the health bar's position and dimensions
    private int x, y, width, height;
    // Creating a constructor to initialize the health bar's attributes
    public HealthBar() {
        // Setting the x and y coordinates of the health bar
        x = 40;
        y = 0;
        // Setting the initial width of the health bar (full health)
        width = 300;
        // Setting the height of the health bar
        height = 30;
    }
    // Defining a method to reduce the health bar's width based on the remaining health
    public void loseHealth(int remainingHealth, int totalHealth) {
        // Calculating the new width as a proportion of the remaining health
        width = (int) (((double) remainingHealth * 300) / (double) totalHealth);
        // Redrawing the health bar to reflect the updated width
        repaint();
    }
    // Defining a method to restore the health bar to its full width
    public void restore() {
        // Resetting the width to the maximum value
        width = 300;
        // Redrawing the health bar to reflect the full health state
        repaint();
    }
    // Overriding the paintComponent method to draw the health bar
    public void paintComponent(Graphics graphicsContext) {
        // Casting the Graphics object to Graphics2D for more advanced features
        Graphics2D graphics2DContext = (Graphics2D) graphicsContext;
        // Setting the border color to black and drawing the health bar's outline
        graphics2DContext.setColor(Color.BLACK);
        graphics2DContext.setStroke(new BasicStroke(4));
        graphics2DContext.drawRect(x, y, width, height);
        // Setting the fill color based on the health bar's width
        graphics2DContext.setColor(Color.GREEN);
        if (width <= 150) {
            graphics2DContext.setColor(Color.YELLOW);
        }
        if (width <= 30) {
            graphics2DContext.setColor(Color.RED);
        }
        // Drawing the filled portion of the health bar inside the outline
        graphics2DContext.fillRect(x + 2, y + 2, width - 4, height - 4);
    }
    // Overriding the getPreferredSize method to define the health bar's dimensions
    public Dimension getPreferredSize() {
        // Returning the current width and height
        return new Dimension(width, height);
    }
}