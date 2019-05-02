package cryptography;


import java.util.Scanner;

/**
 * The Test class exercises the {@link User} and {@link NSALoginController} classes.
 * <p>
 * This class is comprised of static member functions only.
 * @author Adrian Lane
 * @version 1.0
 * @since 2019-04-23
 */
public class Test {

    // Single instance of Scanner object
    private static final Scanner scanner = new Scanner(System.in);

    // An empty private constructor to disallow multiple instances
    private Test() {

    }

    // Presents the passed prompt and returns tha value entered as a string
    private static String promptForPassword(String prompt) {
        String password;

        System.out.print(prompt);
        password = scanner.nextLine();

        return password;
    }

    /**
     * Main entry point for the Java program
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        String password;
        Boolean goodPassword = false;
        User newUser = new User();

        // Loop until a valid password is provided
        do {
            password = promptForPassword("\nPlease provide a password: ");
            newUser = new User(password);
            System.out.println("Password: " + newUser.getPassword());

            // Salt and hash
            try {
                System.out.println("\nSalting and hashing...");
                NSALoginController.hashUserPassword(newUser);
                goodPassword = true;
            }
            catch (WeakPasswordException e) {
                // Just display the message for a weak password
                System.out.println(e.getMessage());
            }
            catch (Exception e){
                // Display the full exception otherwise
                System.out.println(e);
            }
        } while (goodPassword == false);

        // Confirm values are set properly after the salt and hash
        System.out.println("\nPassword: " + newUser.getPassword());
        System.out.println("Salt: " + newUser.getSalt());
        System.out.println("Hash: " + newUser.getHashedPassword());

        // Verify the password
        try {
            password = promptForPassword("\nPlease re-type the password: ");
            newUser.setPassword(password);

            if (NSALoginController.verifyPassword(newUser)) {
                System.out.println("The password PASSED validation.");
            }
            else {
                System.out.println("The password FAILED validation.");
            }
            // Remove the password again after verify
            newUser.setPassword("");
        }
        catch (Exception e){
            // Make sure password is still set to an empty string
            newUser.setPassword("");
            System.out.println(e);
        }
    }
}
