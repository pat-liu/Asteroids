/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;

/**
 * A basic game object starting in the upper left corner of the game court. It is displayed as a
 * circle of a specified color.
 */
public class Asteroid extends GameObj implements Obstacle{
    public static final int SIZE = 20;
    public static final int INIT_POS_X = 170;
    public static final int INIT_POS_Y = 170;
    public static final int INIT_VEL_X = 2;
    public static final int INIT_VEL_Y = 3;
    
    double xPos, yPos;
	
    int health; 
    
	boolean turningUp, turningLeft, turningRight, accelerating;
	
	double angle = 3.14;
	
	double xVelocity, yVelocity;
	
	double radius;
	
	int numBrokeInto;
	

    private Color color;

    public Asteroid(double x, double y, double radius, int health, int numSplit,
    						int courtWidth, int courtHeight, Color color) {
        super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZE, SIZE, courtWidth, courtHeight);

        this.color = color;
        xPos = x;
		yPos = y;
		this.radius = radius;
		
		numBrokeInto = numSplit;
		
		double velocity = 2 + Math.random()* 5.5;
		double direction = 2*Math.PI*Math.random(); 
		
		this.health = health;
		
		xVelocity = velocity*Math.cos(direction);
		yVelocity = velocity*Math.sin(direction);
        
    }
    
    public Asteroid breakAsteroid() {
    	return new Asteroid(xPos, yPos, (1/Math.sqrt(numBrokeInto))*radius,
    			(int) (Math.random() * 1) +1, numBrokeInto-1, 600, 600, Color.white); //when numbrokeninto is 0, 
    	//delete asteroid
    }
    
    
    public boolean playerCollision(Player player) { 
    	if(Math.pow(radius+8,2) > Math.pow(player.getXPos()-xPos,2)+
    			 Math.pow(player.getYPos()-yPos,2)) {
    		return true;
    	}
    		return false; 
    	//distance formula
    }
    
    public boolean laserCollision (Laser laser) {
    	if(Math.pow(radius,2) > Math.pow(laser.getXPos()-xPos,2)+
    			 Math.pow(laser.getYPos()-yPos,2)) {
			 return true;
    	}
    		 return false; 
    }
    
    public int getNumBrokenInto () {
    	return this.numBrokeInto;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.drawOval((int)(xPos-radius+.5),(int)(yPos-radius+.5),
        		 (int)(2*radius),(int)(2*radius));
    }
    
    @Override
    public void move () {
    	 
    	attack();
    	 
    	boundaryInteract();
    	
    }
    
    public void attack() {
    	xPos+=xVelocity; 
    	yPos+=yVelocity;
    }
    
    public void boundaryInteract() {
    	if(xPos<0-radius)
   		 xPos+=600+2*radius;
   		 else if(xPos>600+radius)
   		 xPos-=600+2*radius;
   		 if(yPos<0-radius)
   		 yPos+=600+2*radius;
   		 else if(yPos>600+radius)
   		 yPos-=600+2*radius; 
    }
    
    public void minusHealth() {
    	health-=1;
    }
    
    public int getHealth() {
    	return health;
    }
    
    public double getRadius () {
    	return this.radius;
    }

    
}