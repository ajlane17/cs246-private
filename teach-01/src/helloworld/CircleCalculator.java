package helloworld;

import java.text.DecimalFormat;

public class CircleCalculator {

    private static CircleCalculator single_instance = null;

    private double radius;

    private CircleCalculator() {
        radius = -1;
    }

    /**
     * Returns the current instance of the CircleCalculator object
     * @return returns the instantiated object
     */
    public static CircleCalculator getInstance() {
        if (single_instance == null) {
            single_instance = new CircleCalculator();
        }

        return single_instance;
    }

    /**
     * Prompts the user to enter a value for the radius
     */
    public void getRadius() {

        System.out.print("Please enter a radius: ");
        String input = HelloWorld.scanner.nextLine();

        try {
            this.radius = Double.parseDouble(input);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Calculates and displays the circumference of the circle
     */
    public void displayCircumference() {
        if (radius >= 0) {
            double circumference = radius * Math.PI;
            System.out.println("Circumference: " + formatForDisplay(circumference));
        }
        else {
            System.out.println("Radius cannot be less than 0!");
        }
    }

    /**
     * Calculates and displays the area of the circle
     */
    public void displayArea() {
        if (radius >= 0) {
            double area = Math.PI * Math.pow(radius, 2);
            System.out.println("Area: " + formatForDisplay(area));
        }
        else {
            System.out.println("Radius cannot be less than 0!");
        }
    }

    /**
     * Formats a double value to a string up to two decimal places
     * @param value a double value to convert to a formatted string
     * @return the formatted value as a string
     */
    private String formatForDisplay(double value) {
        DecimalFormat twoDecimalFormat = new DecimalFormat("###.00");
        String formattedValue = twoDecimalFormat.format(value);
        return formattedValue;
    }
}
