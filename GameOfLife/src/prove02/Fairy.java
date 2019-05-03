package prove02;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;
import java.util.List;

/**
 *  Fairies move randomly depositing plants and slaying Zombies and wolves. They are
 *  represented by gold squares. They are also susceptible to damage from Zombies,
 *  the unclean things. An additional bonus to the fairy is diagonal movement.
 * <p>
 * @author  Adrian Lane
 * @version 1.0
 * @since   2019-05-01
 * @see Creature
 */

public class Fairy extends Creature implements Aggressor, Magical, Movable, Spawner {
    Random _rand;
    Shape _shape = Shape.Arc;
    Color _color = new Color(255,215,0);

    /**
     * Creates a Fairy with 10 health point.
     */
    public Fairy() {
        _health = 10;
        _rand = new Random();
    }

    public Shape getShape() { return _shape; }

    public Color getColor() {
        return _color;
    }

    public Boolean isAlive() {
        return _health > 0;
    }

    /**
     * If the creature we've encountered is a zombie, we'll attack it. Otherwise, we ignore it.
     * @param target The {@link Creature} we've encountered.
     */
    public void attack(Creature target) {
        if (target instanceof Zombie || target instanceof Wolf) {
            target.takeDamage(20);
        }
    }
    /**
     * Move the fairy in a random 8-way direction.
     */
    public void move() {

        // Choose a random direction each time move() is called.
        switch(_rand.nextInt(8)) {
            case 0:
                // Up
                _location.y++;
                break;
            case 1:
                // Up Right
                _location.x++;
                _location.y++;
                break;
            case 2:
                // Right
                _location.x++;
                break;
            case 3:
                // Down Right
                _location.x++;
                _location.y--;
                break;
            case 4:
                // Down
                _location.y--;
                break;
            case 5:
                // Down Left
                _location.x--;
                _location.y--;
                break;
            case 6:
                // Left
                _location.x--;
                break;
            case 7:
                // Up Left
                _location.y--;
                break;
            default:
                break;
        }
    }
    /**
     * Chance to drop a new plant where the fairy goes
     */
    public Creature spawnNewCreature() {
        int diceRoll = _rand.nextInt(10);
        // Chance of dropping a new plant wherever a fairy goes
        if (diceRoll < 1) {
            Plant newPlant = new Plant();
            Point pos = new Point((int) this.getLocation().getX(), (int) this.getLocation().getY());
            newPlant.setLocation(pos);
            return newPlant;
        }

        return null;
    }

    /**
     * The fairy casts a spell that increases life of all friendlies in
     * an attempt to keep the balance.
     */
    public void cast(List<Creature> creatures) {
        int diceRoll = _rand.nextInt(5);
        // Chance of casting a spell wherever the unicorn moves
        if (diceRoll < 1) {
            for (Creature c : creatures) {
                if (c instanceof Animal) {
                    c.takeDamage(-2);
                }
            }
        }
    }
}
