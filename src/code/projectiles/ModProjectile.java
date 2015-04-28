package code.projectiles;

import java.awt.Dimension;
import java.awt.Point;

import code.enemies.Enemy;
import code.game.Board;

public class ModProjectile extends Projectile{


	public static final Dimension DEFAULT_SIZE = new Dimension(20, 10); 
	
	//the amount of armor to reduce
	private int modifier;
	//duration of the armor reduction
	private int duration;
	
	/**
	 * Creates a new DOT projectile
	 * @param location: the location of the projectile when it is created
	 * @param target: the target the projectile tries to hit
	 * @param damage: the damage dealt to the enemy
	 * @param speed: the speed at which the projectile moves
	 * @param modifier: the amount of armor to be reduced
	 * @param duration: the duration of the DOT in number of ticks
	 */
	public ModProjectile(Point location, Enemy target, int damage, int speed, int modifier, int duration){
		super(location, DEFAULT_SIZE, "ModProjectile", target, damage, speed);
		this.modifier = modifier;
		this.duration = duration;
	}
	
	/**
	 * reduces armor then deals damage.
	 */
	public void collision(Enemy enemy){
		enemy.reduceArmor(modifier, duration);
		enemy.dealDamage(damage);
		Board.getBoard().removeActor(this);
	}
}
