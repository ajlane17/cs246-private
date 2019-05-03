package prove02;

/**
 *  Aggressors react to creatures they encounter.
 * <p>
 * @author  Adrian Lane
 * @version 1.0
 * @since   2019-05-02
 * @see Creature
 */
public interface Spawner {

    /**
     * If capable, Spawns a {@link Creature}, otherwise returns null
     */
    Creature spawnNewCreature();
}
