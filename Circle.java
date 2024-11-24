/**
 * Circle.java
 *
 * Provide a description of the program here.
 *
 * @author Rishi Saravanan
 * @version 1.0
 * @since 10/26/2022
 */

public class Circle
{
	private double x, y;
	private double radius;
	
	public Circle(double newX, double newY, double dist) { 
		x = newX; 
		y = newY;
		radius = dist;
	}
	

	public double getArea ( )
	{
		return 0.0;
		
	}
	
	public double getX() { 
		return x;
	} 
	
	public double getY() { 
		return y;
	}
	
	public double getRadius() { 
		return radius; 
	}
	public void setX(double x1) { 
		x = x1; 
	}
	public void setY(double y1) { 
		y = y1; 
	}
	public void setRadius(double radius1) { 
		radius = radius1;
	}
}