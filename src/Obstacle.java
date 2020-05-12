	import java.awt.Color;
	import java.awt.Graphics;
	
 public interface Obstacle {
		
	public boolean playerCollision(Player player);
	
	public void attack();
	
	public void boundaryInteract();
	
	public void draw(Graphics g);
	
	public void move();
	
    public double getRadius ();
	
	}
