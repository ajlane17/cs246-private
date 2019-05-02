package prove02;

import java.awt.Color;

/**
 *  Zombies move left to right, damaging animals in their path. They are represented by blue squares.
 * <p>
 * @author  Adrian Lane
 * @version 1.0
 * @since   2019-05-01
 * @see Creature
 */

public class Zombie extends Creature implements Aggressor, Movable {

    Shape _shape = Shape.Square;
    Color _color = new Color(0, 0, 255);

    /**
     * Creates a zombie with 1 health point.
     */
    public Zombie() {
        _health = 1;
    }

    public Shape getShape() { return _shape; }

    public Color getColor() {
        return _color;
    }

    public Boolean isAlive() {
        return _health > 0;
    }

    /**
     * If the creature we've encountered is an animal, we'll attack it. Otherwise, we ignore it.
     * @param target The {@link Creature} we've encountered.
     */
    public void attack(Creature target) {
        if (target instanceof Animal) {
            target.takeDamage(10);
        }
    }

    /**
     * Move the zombie to the right.
     */
    public void move() {
        _location.x++;
    }
}
