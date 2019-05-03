package prove02;

import java.util.List;

/**
 *  Aggressors react to creatures they encounter.
 * <p>
 * @author  Adrian Lane
 * @version 1.0
 * @since   2019-05-02
 * @see Creature
 */
public interface Magical {

    /**
     * casts a spell that impact all animals
     */
    void cast(List<Creature> creatures);
}
