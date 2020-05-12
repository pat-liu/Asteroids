/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact with one another. Take
 * time to understand how the timer interacts with the different methods and how it repaints the GUI
 * on every tick().
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

    // the state of the game logic
    private Player player; // the Black player, keyboard control
    
    private Asteroid asteroid;

    public List<Asteroid> asteroids = new ArrayList<Asteroid>();
    public Laser [] lasers = new Laser[41];
    private int laserCount = 0;
    
    public boolean playing = false; // whether the game is running 
    private JLabel status; // Current status text, i.e. "Running..."
    
    public int score = 0;
	private Enemy enemy; 
	

    // Game constants
    public static final int COURT_WIDTH = 600;
    public static final int COURT_HEIGHT = 600;
    public static double player_VELOCITY = 4;

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 35;

    public GameCourt(JLabel status) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.black);

        // The timer is an object which triggers an action periodically with the given INTERVAL. We
        // register an ActionListener with this timer, whose actionPerformed() method is called each
        // time the timer triggers. We define a helper method called tick() that actually does
        // everything that should be done in a single timestep.
        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start(); // MAKE SURE TO START THE TIMER!

        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        // This key listener allows the player to move as long as an arrow key is pressed, by
        // changing the player's velocity accordingly. (The tick method below actually moves the
        // player.)
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                	
                    //player.setVx(-player_VELOCITY);
                	
                    //player.moveLeft();
                	
                    //player.setVx(player.getVx());
                    
                	player.setTurningLeft(true);
                    //player.moveKey();
                    
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                	
                   // player.setVx(player_VELOCITY);
                	
                    //player.moveRight();
                    
                    player.setTurningRight(true);
                    //player.moveKey();
                    
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                	
                   // player.setVy(-player_VELOCITY);
                    //player.moveUp();
                    player.setAccelerating(true);
                    //player.moveKey();
                    
                }
                
                else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                	player.setShooting(true);
                }
            }

            public void keyReleased(KeyEvent e) {
                //player.setVx(0);
                //player.setVy(0);
                
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                	
                    //player.decelerate();
                	player.setTurningLeft(false);
                    
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {       
                    
                    //player.decelerate();
                	player.setTurningRight(false);
                    
                }  if (e.getKeyCode() == KeyEvent.VK_UP) {
                    
                   // player.decelerate();
                	player.setAccelerating(false);
                    
                }
                
                else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                	player.setShooting(false);
                }
                
            }
        });

        this.status = status;
    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
        player = new Player(COURT_WIDTH, COURT_HEIGHT, Color.white);
        enemy = new Enemy((int) (Math.random() * 235),
        		(int)(Math.random()*600),
        		30, 
        		(int) (Math.random() * (3 - 2)) + 2, 
        		(int) (Math.random() * (4 - 3)) + 2,
        		COURT_WIDTH, COURT_HEIGHT, Color.white);
     
        resetAsteroids();

        playing = true;
        this.score=0;
        status.setText("Score: " + this.score);

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    /**
     * This method is called every time the timer defined in the constructor triggers.
     */
    void tick() {
        if (playing) {
        	
        	if(asteroids.size()==0) {
        		playing = false;
            	status.setText("You win! Your final score was: " + this.score);
        	}
            // advance the player and snitch in their current direction.
            player.move();
            enemy.move();
            //snitch.move();
            for(Asteroid asteroid: asteroids) {
            	
                asteroid.move();
                
                if(asteroid.playerCollision(player) 
                   || enemy.playerCollision(player)) {
                	playing = false;
                	status.setText("You lose! Your final score was: " + this.score);
                }
                
                for(int i = 0; i< laserCount; i++) {
                	if(asteroid.laserCollision(lasers[i])) {
                		this.score+=(int)(150/asteroid.getRadius());
                		status.setText("Score: " + this.score);
                		
                		deleteLaser(i);
                		
                		asteroid.minusHealth();
                		
                		if(asteroid.getHealth()==0) {
                			
                		int asteroidIndex = asteroids.indexOf(asteroid); 
                		
                		
                		//deleteAsteroid
                		List<Asteroid> copy = new ArrayList<Asteroid>(asteroids);
                		copy.remove(asteroidIndex);
                		//remove asteroid
                		
                		List<Asteroid> updated = new ArrayList<Asteroid>(copy);
                		for(int j=0; j<asteroid.getNumBrokenInto(); j++) {
                			
                			updated.add(asteroid.breakAsteroid());
                			
                		}
                		asteroids=updated;
                		
                		}
                	}
                }
                
            }
           
            if(player.shooting && player.canLaser()) {
            	 lasers[laserCount]=player.shoot();
            	 laserCount++;
            }
            
            for(int i = 0; i< laserCount; i++) {
            	lasers[i].move();
            	
            	if(lasers[i].getSurvivalTime() <= 0) {
            		deleteLaser(i);
            		i--;
            	}
            }
           
            // update the display
            repaint();
        }
    }
    
    public void resetAsteroids() {
    	asteroids.clear();
    	 for(int i = 0; i<3;i++) {
    	        asteroid = new Asteroid((int) (Math.random() * 235),
    	        		(int)(Math.random()*600),
    	        		(int) (Math.random() * (60 - 15)) + 15, 
    	        		(int) (Math.random() * (3 - 2)) + 2, 
    	        		(int) (Math.random() * (4 - 3)) + 2,
    	        		COURT_WIDTH, COURT_HEIGHT, Color.LIGHT_GRAY);
    	        asteroids.add(asteroid);
    	        }
    	 for(int i = 0; i<3;i++) {
 	        asteroid = new Asteroid((int) (Math.random() * (600 - 365)) + 365,
 	        		(int)(Math.random()*600),
 	        		(int) (Math.random() * (60 - 15)) + 15, 
 	        		(int) (Math.random() * (3 - 2)) + 2, 
 	        		(int) (Math.random() * (4 - 3)) + 2,
 	        		COURT_WIDTH, COURT_HEIGHT, Color.LIGHT_GRAY);
 	        asteroids.add(asteroid);
 	        }
    }
    
    
    public void deleteLaser(int index){
    	laserCount--;
    	for(int i=index;i<laserCount;i++)
    	 lasers[i]=lasers[i+1];
    	lasers[laserCount]=null;
    	} 
    
    public void deleteAsteroid(Asteroid asteroid) {
    	int asteroidIndex = asteroids.indexOf(asteroid); 

		List<Asteroid> copy = new ArrayList<Asteroid>(asteroids);
		copy.remove(asteroidIndex);
		
		asteroids = copy;
    }
    

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);
        enemy.draw(g);
        //poison.draw(g);
        //snitch.draw(g);
        for(Asteroid asteroid: asteroids) {
        asteroid.draw(g);
        }
        
        for(int i = 0; i<laserCount; i++) {
        	lasers[i].draw(g);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}