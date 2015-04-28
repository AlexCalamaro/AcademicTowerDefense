package code.enemies;

import java.awt.Dimension;
import java.awt.Point;

/**
 * This is a subclass of the enemy class and is used to create regenerate enemies. 
 * It holds a name, health, speed and rewarded money after being killed.
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */

public class RegenerateEnemy extends Enemy{
	
	public static Dimension size = new Dimension(50,50);
	public static String name = "Honor Student";
	
	/**
	 * constructor that uses the enemy class' constructor
	 */
	public RegenerateEnemy(Point Location){
		super(Location,size, "regenerateStudent", name, 200, 7, 10, 50);
	}
	
	/**
	 * the update method uses enemy class' update method
	 * it calls the regenerate method after each time updated
	 * regenerate enemies have a special ability
	 * it regenerate health per tick
	 */
	public void update(){
		super.update();
		regenerate();
	}
	
	/**
	 * this method add one health to the enemy's current health
	 */
	public void regenerate(){
		health += 1;
		if (health > originalHealth){
			health = originalHealth;
		}
	}
	
}
