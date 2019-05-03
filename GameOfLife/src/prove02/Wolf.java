package prove02;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

/**
 *  Wolves move randomly until they're near prey, then they hunt. They are represented by blue squares.
 * <p>
 * @author  Adrian Lane
 * @version 1.0
 * @since   2019-05-01
 * @see Creature
 */

public class Wolf extends Creature implements Aggressor, Aware, Movable, Spawner {

    Random _rand;
    int preferredDirection;
    Boolean canSpawn = false;
    Shape _shape = Shape.Square;
    Color _color = new Color(80, 80, 80);

    /**
     * Creates a wolf with 1 health point.
     */
    public Wolf() {
        _health = 5;
        _rand = new Random();
        preferredDirection = _rand.nextInt(4);
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
            target.takeDamage(5);
            if (!(target.isAlive())) {
                this.canSpawn = true;
            }
        }
    }
    /**
     * Move the wolf in the preferred direction.
     */
    public void move() {
        switch(preferredDirection) {
            case 0:
                _location.y--;
                break;
            case 1:
                _location.x++;
                break;
            case 2:
                _location.y++;
                break;
            case 3:
                _location.x--;
                break;
            default:
                break;
        }
    }

    /**
     * If we sense an animal nearby, set the preferred direction toward it.
     * @param above The {@link Creature} above.
     * @param below The {@link Creature} below.
     * @param left The {@link Creature} left.
     * @param right The {@link Creature} right.
     */
    public void senseNeighbors(Creature above, Creature below, Creature left, Creature right) {
        // I'm using an int to create a wrapping list of directions
        int directions = 4;

        // Set the starting point as the preferred direction
        int checkDirection = preferredDirection;
        Creature currCreature = null;

        // Loop through the creatures trying to find a direction to move
        // starting from the direction the wolf is already moving
        do {
            switch (checkDirection) {
                case 0:
                    currCreature = above;
                    break;
                case 1:
                    currCreature = right;
                    break;
                case 2:
                    currCreature = below;
                    break;
                case 3:
                    currCreature = left;
            }

            // If an animal is found, move toward it
            if (currCreature instanceof Animal) {
                preferredDirection = checkDirection;
            }
            else {
                // If we've reached the end of the list, wrap
                if (checkDirection == directions) {
                    checkDirection = 0;
                }
                // Check the next direction
                else {
                    checkDirection++;
                }
            }
        // If we don't find any animals, keep moving the same direction
        } while (checkDirection != preferredDirection);
    }

    /**
     * Births a new wolf if its eaten.
     */
    public Creature spawnNewCreature() {
        if (canSpawn) {
            Wolf newWolf = new Wolf();
            Point startPos = new Point((int) this.getLocation().getX() - 1, (int) this.getLocation().getY());
            newWolf.setLocation(startPos);
            this.canSpawn = false;
            return newWolf;
        }

        return null;
    }

}
