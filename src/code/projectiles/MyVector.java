package code.projectiles;
import java.awt.Point;

/**
 * This stores a vector. Used to find apropriate directions.
 * 
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */
public class MyVector {
	
	//the x and y components of the vector
	private double x, y;
	
	/**
	 * @param x: the x component
	 * @param y: the y component
	 */
	public MyVector(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Finds the appropriate vector from point a to point b
	 * @param a: the origin of the vector
	 * @param b: the destination of the vector
	 */
	public MyVector(Point a, Point b){
		this.x = b.getX() - a.getX();
		this.y = b.getY() - a.getY();
	}
	
	/**
	 * @return: the length of the vector
	 */
	public double getMagnitude(){
		return Math.hypot(x, y);
	}
	
	/**
	 * @return the x component
	 */
	public double getX(){
		return x;
	}
	
	/**
	 * @return the y component
	 */
	public double getY(){
		return y;
	}
	
	/**
	 * gets a vector of length 1, in the same direction as
	 * the vector from point a to point b
	 * @param a: the origin of the vector to scale
	 * @param b: the destination of the vector to scale
	 * @return the unit vector in appropriate direction
	 */
	public static MyVector getUnitVector(Point a, Point b){
		MyVector v = new MyVector(a, b);
		return new MyVector(v.getX()/v.getMagnitude(), v.getY()/v.getMagnitude());
	}
}
