/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;

/**
 * A basic game object starting in the upper left corner of the game court. It is displayed as a
 * square of a specified color.
 */
public class Player extends GameObj {
    public static final int SIZE = 5; //radius of player
    public static final int INIT_POS_X = 300;
    public static final int INIT_POS_Y = 300;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;
    
    final double[] origXPts={13,-11,-6,-11};
	final double [] origYPts={0,-9,0,9};
	
	int[] xPts, yPts;
	
	final double deceleration = .7; 
	
	double xPos, yPos;
	
	boolean turningUp, turningLeft, turningRight, accelerating, shooting;
	
	double angle = 3.14;
	
	double xVelocity, yVelocity;
	
	double laserSpeed;
	
	double circSpeed;
	
	double acceleration;
	
	double laserDelay;
	

    private Color color;

    /**
    * Note that, because we don't need to do anything special when constructing a Square, we simply
    * use the superclass constructor called with the correct parameters.
    */
    public Player(int courtWidth, int courtHeight, Color color) {
        super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZE, SIZE, courtWidth, courtHeight);

        this.color = color;
        xPts=new int[4]; 
		yPts=new int[4];
		
		xPos = 300;
		yPos = 300;
		
		xVelocity = 0;
		yVelocity = 0;
		
		laserDelay = 0;
		
		acceleration = 2.1;
		circSpeed = .3;
		
		turningUp = false; 
		turningLeft= false;
		turningRight = false; 
		accelerating = false;
    }
    
    //(double xPos, double yPos, double angle, double laserSpeed, double acceleration) 

    
    
    @Override
    public void draw(Graphics g) {
    	
    	for(int i=0;i<4;i++){
			 xPts[i]=(int)(origXPts[i]*Math.cos(angle)- //rotate
			 origYPts[i]*Math.sin(angle)+xPos+.5); //translate and round
			 
			 yPts[i]=(int)(origXPts[i]*Math.sin(angle)+ //rotate
			 origYPts[i]*Math.cos(angle)+
			 yPos+.5); 
			 } 
    	
        g.setColor(this.color);
		g.drawPolygon(xPts, yPts, 4);
    }
    
    
    public void setTurningLeft(boolean bool) {
    	this.turningLeft = bool;
    }
    
    public void setTurningRight(boolean bool) {
    	this.turningRight = bool;
    }
    
    public void setTurningUp(boolean bool) {
    	this.turningUp = bool;
    }
    
    public void setAccelerating(boolean bool) {
    	this.accelerating = bool;
    }
    
   public void setShooting(boolean bool) {
    	this.shooting = bool;
    }
    
    public double getXVelocity() {
    	return this.xVelocity;
    }
    
    public double getYVelocity() {
    	return this.yVelocity;
    }
   
    public void setXPos(double position) {
    	this.xPos = position;
    }
    
    public void setYPos(double position) {
    	this.yPos = position;
    }
    
    public double getXPos() {
    	return this.xPos;
    }
    
    public double getYPos() {
    	return this.yPos;
    }
    /*
    public void keepAngleBound(double angle) {
    	if(angle>(2*Math.PI))
		 this.angle-=(2*Math.PI);
		else if(angle<0)
		 this.angle+=(2*Math.PI); 
    }
    */
    
    public void moveUp() {
    	this.setVx(this.getVx()+acceleration*Math.cos(angle));
    	this.setVy(this.getVy()+acceleration*Math.sin(angle));
    }
    
    public void decelerate() {
    	this.setVx(this.getVx()*.9);
    	this.setVy(this.getVy()*.9);
    }
    
    @Override
    public void move () {
    	//call this in gameCourt when a key is pressed
    	
    	if(turningLeft) 
    		 angle-=circSpeed; 
    	if(turningRight) 
    		 angle+=circSpeed; 
    		if(angle>(2*Math.PI))
    		 angle-=(2*Math.PI);
    		else if(angle<0)
    		 angle+=(2*Math.PI); 
       if(laserDelay>0) {
    	   laserDelay--;
       }
    		
    		
    	if(accelerating){ //adds accel to velocity in direction pointed
    			 //calculates components of accel and adds them to velocity
    		//this.setVx(this.getVx()+acceleration*Math.cos(angle));		
    		xVelocity+=acceleration*Math.cos(angle);
    		 //xVelocity+=acceleration*Math.cos(angle);
    		//this.setVy(this.getVy()+acceleration*Math.sin(angle));
    		yVelocity+=acceleration*Math.sin(angle);
    		 //yVelocity+=acceleration*Math.sin(angle);
    	}
    	
    	xPos+=xVelocity;
    	yPos+=yVelocity;
    	
    	xVelocity*=.95;
    	yVelocity*=.95;
    	
        if(xPos<0) //wrap the ship around to the opposite side of the screen
       	 xPos+=600; //when it goes out of the screen's bounds
       	else if(xPos>600)
       	 xPos-=600;
       	if(yPos<0)
       	 yPos+=600;
       	else if(yPos>600)
       	 yPos-=600; 
       	 
    	
    }
    
    public Laser shoot() {
    	laserDelay = 30;
    	return new Laser(xPos, yPos, angle, xVelocity, yVelocity, 600,600, Color.white);
    }
    
    
    public boolean canLaser() {
    	if(laserDelay == 0) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
 
    
}