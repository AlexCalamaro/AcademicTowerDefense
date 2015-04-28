package code.enemies;

import java.awt.Dimension;
import java.awt.Point;

/**
 * This is a subclass of the enemy class and is used to create heavy armored enemies. 
 * It holds a name, health, speed and rewarded money after being killed.
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */


public class ArmorEnemy extends Enemy{
	
	public static Dimension size = new Dimension(60,60);
	public static String name = "Test preparing Student";
	
	/**
	 * constructor that uses the enemy class' constructor
	 */
	public ArmorEnemy(Point Location){
		super(Location,size, "armorStudent", name, 100, 5, 25, 80);
	}
	
	/**
	 * override the dealDamage method of the enemy class
	 * heavy armored enemies have a special ability
	 * they take half damage when their armors exist
	 */
	public void dealDamage(int damage){
		if (armor>0){
			super.dealDamage(damage/2);
		}
		else{
			super.dealDamage(damage);
		}
	}
	
}