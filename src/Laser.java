import java.awt.*; 

public class Laser extends GameObj{
    public static final int SIZE = 20;
    public static final int INIT_POS_X = 100;
    public static final int INIT_POS_Y = 100;
    public static final int INIT_VEL_X = 2;
    public static final int INIT_VEL_Y = 3;

	double speed;
	double survivalTime;
	
	double angle;
	
	double xPos, yPos;
	
	double xVelocity, yVelocity;
	
	public Laser(double x, double y, double angle, double playerXVelocity, 
			double playerYVelocity, int courtWidth, int courtHeight, Color color) {
		super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZE, SIZE, courtWidth, courtHeight);
		
		xPos = x; 
		yPos = y;
		
		speed=11;
		survivalTime = 45;
		
		xVelocity=speed*Math.cos(angle)+playerXVelocity;
		yVelocity=speed*Math.sin(angle)+playerYVelocity; 
		
	}
	
	public void move() {
		survivalTime--;
		
		xPos += xVelocity;
		yPos += yVelocity;
		
		if(xPos<0) //wrap the ship around to the opposite side of the screen
	       	 xPos+=600; //when it goes out of the screen's bounds
	       	else if(xPos>600)
	       	 xPos-=600;
	       	if(yPos<0)
	       	 yPos+=600;
	       	else if(yPos>600)
	       	 yPos-=600; 
	       	 
	}
	
	public double getSurvivalTime() {
		return this.survivalTime;
	}
	
	public double getXPos() {
		return xPos;
	}
	
	public double getYPos() {
		return yPos;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
		g.setColor(Color.white);
		g.fillOval((int)(xPos-.6), (int)(yPos-.6), 3, 3); 
		
	}

}
