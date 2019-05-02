package cryptography;

/**
 * The User class represents the secrets associated with a user account.
 * @author Adrian Lane
 * @version 1.0
 * @since 2019-04-23
 */
public class User {
    private String password;
    private String hashedPassword;
    private String salt;

    // Default constructor
    public User() {

    }

    // Non-default constructor
    public User(String password) {
        this.setPassword(password);
    }

    public String getPassword() {
        return password;
    }

    // Remove!
    public static void main(String[] args) {}

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
