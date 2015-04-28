package code.enemies;

import java.awt.Dimension;
import java.awt.Point;

/**
 * This is a subclass of the enemy class and is used to create fast enemies. 
 * It holds a name, health, speed and rewarded money after being killed.
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */

public class FastEnemy extends Enemy{
	
	public static Dimension size = new Dimension(30,30);
	public static String name = "Class Skipper";

	/**
	 * constructor that uses the enemy class' constructor
	 */
	public FastEnemy(Point Location){
		super(Location,size, "fastStudent", name, 125, 5, 5, 30);
	}
	
	/**
	 * this method uses the dealDamage method in enemy class
	 * fast enemies have a special ability
	 * they travel in double spawning speed when they have less than half health
	 */
	public void dealDamage(int damage){
		super.dealDamage(damage);
		if (health < originalHealth/2){
			originalSpeed=originalSpeed*2;
			speed=originalSpeed;
		}
	}

}
