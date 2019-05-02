package helloworld;

import java.util.Scanner;

public class HelloWorld {

    // Singleton of the scanner object
    public static final Scanner scanner = new Scanner(System.in);

    /**
     * Displays "Hello World!" in the console
     */
    public static void sayHello() {

        System.out.println("Hello World!");
    }

    /**
     * The main entry point for the program
     * @param args a string array of runtime arguments
     */
    public static void main(String[] args) {
	    sayHello();

	    // Singleton of the CircleCalculator
	    CircleCalculator circleCalculator = CircleCalculator.getInstance();
	    circleCalculator.getRadius();
	    circleCalculator.displayCircumference();
	    circleCalculator.displayArea();
    }
}
