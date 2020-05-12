/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
    public void run() {
        // NOTE : recall that the 'final' keyword notes immutability even for local variables.

        // Top-level frame in which game components live
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
    	
    	
    	
        final JFrame frame = new JFrame("Asteroids!");
        frame.setLocation(300, 300);
        
        JOptionPane.showMessageDialog(frame,
        	    "																																																How to play:"
        	    + "\n \n" 
        	    + "Press Up arrow to move/accelerate forward."
        	    + "\n \n" 
        	    + "Press Left arrow to rotate counterclockwise."
        	    + "\n \n"
        	    + "Press Right arrow to rotate clockwise."
        	    + "\n \n"
        	    + "Press Space bar to shoot laser and hit asteroids."
        	    + "\n \n"
        	    + "(You can only shoot the laser every 2 seconds)"
        	    + "\n \n"
        	    + "\n \n"
        	    + "Destroy all the asteroids without getting hit"
        	    + "! Smaller asteroids = more points."
        	    + "\n \n"
        	    + "But watch out: asteroids can split into smaller asteroids "  
        	    + "and solid white asteroid"
        	    + "\n \n"
        	    + "is rogue and changes direction/speed randomly..."
        	    + "oh, and it's unshootable.", 
        	    "Asteroids Instructions!"
        	    , JOptionPane.INFORMATION_MESSAGE);
        

        // Status panel
        final JPanel status_panel = new JPanel();
       
        frame.add(status_panel, BorderLayout.SOUTH);
        
        final JLabel status = new JLabel("You are playing asteroids!");
        status_panel.add(status);

        // Main playing area
        final GameCourt court = new GameCourt(status);
        frame.add(court, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        // Note here that when we add an action listener to the reset button, we define it as an
        // anonymous inner class that is an instance of ActionListener with its actionPerformed()
        // method overridden. When the button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.reset();
                court.resetAsteroids();
            }
        });
        control_panel.add(reset);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        court.reset();
        
    }

    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it. IMPORTANT: Do NOT delete! You MUST include this in your final submission.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}