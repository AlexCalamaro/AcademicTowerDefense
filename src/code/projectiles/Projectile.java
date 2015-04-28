package code.projectiles;

import java.awt.Dimension;
import java.awt.Point;

import code.enemies.Enemy;
import code.game.Actor;
import code.game.Board;
import code.game.ImageActor;
import code.game.Observer;

/**
 * A basic projectile. Does nothing special. Simply does damage when it hits an enemy
 * 
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */
public class Projectile extends ImageActor implements Observer 
{
	
	//defualt dimension
	public static final Dimension DEFAULT_SIZE = new Dimension(20, 10); 
	
	
	protected float speed;
	protected int damage;
	protected Enemy target;
	
	//stores the direction the projectile is moving
	private MyVector lastKnownDirection;
	
	/**
	 * Creates a projectile
	 * @param location: the location of the projectile when it is created
	 * @param size: the size of the projectile (used to scale the image)
	 * @param path: the key to the image manager used to retrieve the image
	 * @param target: the target the projectile tries to hit
	 * @param damage: the damage dealt to the enemy
	 * @param speed: the speed at which the projectile moves
	 */
	protected Projectile(Point location, Dimension size, String path, Enemy target, int damage, int speed){
		super(location, size, path);
		this.speed = speed;
		this.damage = damage;
		this.target = target;
		
		lastKnownDirection = MyVector.getUnitVector(location, target.getLocation());
	}
	
	/**
	 * Creates a projectile
	 * @param location: the location of the projectile when it is created
	 * @param target: the target the projectile tries to hit
	 * @param damage: the damage dealt to the enemy
	 * @param speed: the speed at which the projectile moves
	 */
	public Projectile(Point location, Enemy target, int damage, int speed){
		super(location, DEFAULT_SIZE, "plainRuler");
		this.speed = speed;
		this.damage = damage;
		this.target = target;
		
		lastKnownDirection = MyVector.getUnitVector(location, target.getLocation());
	}
	
	/**
	 * called every tick
	 * this method must move the enemy in the correct direction at the correct speed
	 */
	public void update()
	{
		//updates the direction if the enemy is still on the board
		if(Board.getBoard().contains(target)){
			lastKnownDirection = MyVector.getUnitVector(location, target.getLocation());
		}
		
		//updates the location of the projectile
		int newX = (int) (location.getX() + (speed * lastKnownDirection.getX()));
		int newY = (int) (location.getY() + (speed * lastKnownDirection.getY()));
		location = new Point((int) newX, (int) newY);
		
		//if the enemy has left the board, it removes itself
		if(!Board.getBoard().isInBounds(location)){
			Board.getBoard().removeActor(this);
		}
		
	}
	
	/**
	 * called by Board whenever a projectile hits an enemy
	 * the projectile deals damage and then removes itself
	 */
	public void collision(Enemy enemy){
		enemy.dealDamage(damage);
		Board.getBoard().removeActor(this);
	}

}
