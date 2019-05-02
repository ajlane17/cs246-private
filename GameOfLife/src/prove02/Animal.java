package prove02;

import java.awt.Color;
import java.util.Random;

/**
*  Animals move around and eat plants. They are represented by red squares.
* <p>
* @author  Brother Falin
* @version 1.0
* @since   2016-12-08 
* @see Creature
*/
public class Animal extends Creature implements Movable, Aggressor {
	
	Random _rand;
	
	/**
	* Creates an animal with 1 health point.
	*/
	public Animal() {
		_rand = new Random();
		_health = 1;
	}
	
	// No javadocs are necessary for these methods because they will inherit the 
	// documentation from the superclass. We only need to add docs here if we are
	// doing something non-obvious in our overridden version.
	
	public Boolean isAlive() {
		return _health > 0;
	}
	
	public Shape getShape() {
		return Shape.Square;
	}
	
	public Color getColor() {
		return new Color(165, 33, 135);
	}
	
	/**
	* If the creature we've encountered is a plant, we'll eat it. Otherwise, we ignore it.
	* @param target The {@link Creature} we've encountered.
	*/
	public void attack(Creature target) {
		// Animals only eat plants. Give the plant 1 damage
		// and increase our health by one.
		if(target instanceof Plant) {
			target.takeDamage(1);
			_health++;
		}
	}
	
	/**
	* Move the animal in a random direction.
	*/
	public void move() {
		
		// Choose a random direction each time move() is called.
		switch(_rand.nextInt(4)) {
			case 0:
				_location.x++;
				break;
			case 1:
				_location.x--;
				break;
			case 2:
				_location.y++;
				break;
			case 3:
				_location.y--;
				break;
			default:
				break;
		}
	}
}
