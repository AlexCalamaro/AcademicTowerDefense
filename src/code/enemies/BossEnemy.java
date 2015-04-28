package code.enemies;

import java.awt.Dimension;
import java.awt.Point;

/**
 * This is a subclass of the enemy class and is used to create boss enemies. 
 * It holds a name, health, speed and rewarded money after being killed.
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */
public class BossEnemy extends Enemy{
	
	public static Dimension size = new Dimension(75,75);
	public static String name = "Bad Student";
	
	/**
	 * constructor that uses the enemy class' constructor
	 */
	public BossEnemy(Point Location){
		super(Location,size, "bossStudent", name, 300, 5, 50, 1000);
	}
	
	/**
	 * override changeSpeed, reduceArmor, stun and dealDamageOverTime methods
	 * boss enemies have a special ability
	 * they resist to all side effects 
	 */
	public void changeSpeed(int changeFactor, int numTicks){
	}
	
	public void reduceArmor(double changeArmor, int numTicks){
	}
	
	public void stun(int numTicks){
	}
	
	public void dealDamageOverTime(int damagePerTick, int numTicks){
	}
}