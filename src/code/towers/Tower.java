package code.towers;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import code.enemies.Enemy;
import code.game.Actor;
import code.game.Board;
import code.game.ImageActor;
import code.projectiles.Projectile;

/**
 * Tower doesn't do much right now. But it will eventually shoot projectiles, as well as hold a name, description, and cost.
 * It's get summary method formats the user-relevant information for easy display.
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */
public abstract class Tower extends ImageActor 
{
	//the name of the tower
	protected String name;
	
	//the description of the tower (seen in the menu when selected)
	protected String description;
	
	//the cost of the tower
	protected int cost;
	
	//number of ticks between each time the tower fires
	protected int cooldown;
	
	//the range of the tower
	protected int range;
	
	//Tacks the number of ticks the tower has been alive.
	//used to know when it should shoot next
	protected int tickCount;
	
	//The amount of damage the tower does
	protected int damage;
	
	//the speed of the projectile it fires
	protected int projectileSpeed;
	
	/**
	 * Initializes the variables. Pretty straightforward constructor.
	 * @param location The (x,y) location on the board where the Tower is located.
	 * @param path The path to the image that represent the tower.
	 * @param scaleX The X dimension of the image that represents the tower.
	 * @param scaleY The Y dimension of the image that represents the tower.
	 * @param name  The name of the tower.
	 * @param description A short description of the tower.
	 * @param cost How much the tower costs to purchase.
	 * @param attackSpeed The speed at which the tower attacks (in ticks per attack)
	 * @param range How far the tower can shoot
	 */
	protected Tower(Point location, Dimension size, String path,
			String name, String description,
			int cost, int cooldown, int range, int damage, int projectileSpeed) 
	{
		super(location, size, path);
		this.name=name;
		this.description=description;
		this.cost=cost;
		this.cooldown = cooldown;
		this.range = range;
		this.tickCount = 0;
		this.damage = damage;
		this.projectileSpeed = projectileSpeed;
	} 
	
	/**
	 * @return An array of strings that should be displayed one on each line.
	 *  The string is a description of the tower ready to display to the player.
	 */
	public String[] getSummary()
	{
		String[] toReturn=new String[3];
		toReturn[0]="Name: "+name+" Cost: "+cost + 
				" Cooldown: " +cooldown+ " Range: " +range;
		toReturn[1]=description;
		return toReturn;
	}
	
	/**
	 * the method that is called every tick. Checks if the tower is allowed
	 * to fire, then fires appropriately. Also updates the tick count.
	 */
	public void update()
	{
		if(canFire()){
			this.fire();
		}
		tickCount++;
	}
	
	
	/**
	 * @return true if the tower is allowed to fire else false
	 */
	private boolean canFire(){
		return(tickCount % cooldown == 0);
	}
	
	/**
	 * fires a projectile at the first enemy it sees
	 */
	protected void fire(){
		ArrayList<Enemy> enemies = getTargets();
		if(enemies.size()>0)
			createProjectile(enemies.get(0));
	}
	
	/**
	 * Creates a projectile aimed at enemy e
	 * @param e: the enemy the tower is aiming at
	 */
	protected void createProjectile(Enemy e) {
		Board.getBoard().addActor(new Projectile(location, e, damage, projectileSpeed));
	}
	
	/**
	 * returns a list of enemies within the range of this tower
	 * @return: the ArrayList of potential targets
	 */
	protected ArrayList<Enemy> getTargets(){
		
		ArrayList<Actor> list = Board.getBoard().getActorsWithinDistance(location, range);
		ArrayList<Enemy> toReturn = new ArrayList<Enemy>();
		
		for(Actor actor : list){
			if(actor instanceof Enemy){
				toReturn.add((Enemy) actor);
			}
		}
		
		return toReturn;
	}
	
	/**
	 * @return the image for the tower
	 */
	public Image getImage()
	{
		return image;
	}
	
	/**
	 * @return the cost of the tower
	 */
	public int getCost(){
		return cost;
	}
	
	/**
	 * @return the cooldown of the tower
	 */
	public int getCooldown(){
		return cooldown;
	}
	
	/**
	 * @return the range of the tower
	 */
	public int getRange(){
		return range;
	}
	
}
