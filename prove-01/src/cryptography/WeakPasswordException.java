package cryptography;

/**
 * The User class defines a custom WeakPasswordException.
 * @author Adrian Lane
 * @version 1.0
 * @since 2019-04-23
 */
public class WeakPasswordException extends Exception {
    public WeakPasswordException() {

    }

    public WeakPasswordException(String message) {
        super(message);
    }
}
