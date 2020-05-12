import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import javax.swing.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Test;

/** 
 *  You can use this file (and others) to test your
 *  implementation.
 */

public class GameTest {

    private JLabel l = new JLabel();
    private GameCourt gc = new GameCourt(l);
	
	
    @Test
    public void playerAsteroidCollisionDetected() {
       Player player = new Player(600,600,Color.white);
       Asteroid asteroid = new Asteroid(300,
       		300,
       		(int) (Math.random() * (60 - 15)) + 15, 
       		(int) (Math.random() * (3 - 2)) + 2, 
       		(int) (Math.random() * (4 - 3)) + 2,
       		600,600 , Color.LIGHT_GRAY);
       
        assertTrue(asteroid.playerCollision(player));
       
    }
    
    @Test
    public void playerEnemyCollisionDetected() {
       Player player = new Player(600,600,Color.white);
       Enemy enemy = new Enemy(300,
       		300,
       		(int) (Math.random() * (60 - 15)) + 15, 
       		(int) (Math.random() * (3 - 2)) + 2, 
       		(int) (Math.random() * (4 - 3)) + 2,
       		600,600 , Color.white);
       
        assertTrue(enemy.playerCollision(player));
       
    }
    
    @Test
    public void laserAsteroidCollisionDetected() {
    	Player player = new Player(600,600,Color.white);
    	Asteroid asteroid = new Asteroid(300,
           		300,
           		(int) (Math.random() * (60 - 15)) + 15, 
           		(int) (Math.random() * (3 - 2)) + 2, 
           		(int) (Math.random() * (4 - 3)) + 2,
           		600,600 , Color.LIGHT_GRAY);
    	
    	Laser laser = new Laser(300,300,Math.PI, 
        player.getVx(), player.getVy(), 600, 600, Color.white);
       
        assertTrue(asteroid.laserCollision(laser));  
    }
  
    @Test
    public void loseCondition() {
    	  Player player = new Player(600,600,Color.white);
          Enemy enemy = new Enemy(600,
          		600,
          		(int) (Math.random() * (60 - 15)) + 15, 
          		(int) (Math.random() * (3 - 2)) + 2, 
          		(int) (Math.random() * (4 - 3)) + 2,
          		600,600 , Color.white);
          gc.tick();
          assertEquals(gc.playing, false);  
          
    }
    
    @Test
    public void winCondition() {
    	  gc.tick();
    	  gc.asteroids = new ArrayList<Asteroid>();
          assertEquals(gc.playing, false);  
          
    }
    
    @Test
    public void playerMove() {
    	Player player = new Player(600,600,Color.white);
    	player.setXPos(601);
    	gc.tick();
    	assertTrue(player.getXPos()==601);
    }
    
    @Test 
    public void splitAsteroid() {
    	Player player = new Player(600,600,Color.white);
    	Asteroid asteroid = new Asteroid(300,
           		300,
           		(int) (Math.random() * (60 - 15)) + 15, 
           			0, 
           		(int) (Math.random() * (4 - 3)) + 2,
           		600,600 , Color.LIGHT_GRAY);
    	 gc.asteroids = new ArrayList<Asteroid>();
    	 gc.asteroids.add(asteroid);
    	 gc.tick();
    	Laser laser = new Laser(300,300,Math.PI, 
    	        player.getVx(), player.getVy(), 600, 600, Color.white);
    	gc.lasers[0] = laser; 
    	assertTrue(gc.asteroids.size()==1);
    }
    
    @Test 
    public void reloadTime() {
    	gc.tick();
    	Player player = new Player(600,600,Color.white);
    	player.shoot();
    	Asteroid asteroid = new Asteroid(400,
           		600,
           		(int) (Math.random() * (60 - 15)) + 15, 
           			0, 
           		(int) (Math.random() * (4 - 3)) + 2,
           		600,600 , Color.LIGHT_GRAY);
    	gc.asteroids.add(asteroid);
    	assertTrue(!player.canLaser());
    	
    }
    
    @Test 
    public void reloadAgain() {
    	gc.tick();
    	Player player = new Player(600,600,Color.white);
    	player.shoot();
    	Asteroid asteroid = new Asteroid(400,
           		600,
           		(int) (Math.random() * (60 - 15)) + 15, 
           			0, 
           		(int) (Math.random() * (4 - 3)) + 2,
           		600,600 , Color.LIGHT_GRAY);
    	gc.asteroids.add(asteroid);
    	for(int i=0;i<20;i++) {
    	gc.tick();}    	
    	assertTrue(!player.canLaser());
    }
    
    @Test
    public void asteroidDelete() {
    	Asteroid asteroid = new Asteroid(400,
           		600,
           		(int) (Math.random() * (60 - 15)) + 15, 
           			0, 
           		(int) (Math.random() * (4 - 3)) + 2,
           		600,600 , Color.LIGHT_GRAY);
    	gc.asteroids.add(asteroid);
    	int currentSize = gc.asteroids.size();
    	gc.deleteAsteroid(asteroid);
    	assertEquals(gc.asteroids.size(), currentSize-1);
    }

    
  
    
    
    
    
    
    
    
    //test laser-asteroid collision
    
    //test health function (if laser-collision, health goes down by one)
    
    //test asteroid length if asteroid split into two
    
    //test if asteroid goes off screen, position is correct
    
    //test if player goes off screen, position is correct
    
    //test if score counter is correct, given collisions
    

}
