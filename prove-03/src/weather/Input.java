package weather;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public final class Input {
    private static Input inputInstance = null;
    private Scanner scanner;

    private Input() {
        scanner = new Scanner(System.in); }

    public static Input getInstance() {
        if (inputInstance == null) {
            inputInstance = new Input();
        }
        return inputInstance;
    }

    public String getString(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        scanner.reset();
        return input;
    }

    public int getInt(String prompt) {
        int input;
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("\nINVALID - NOT A NUMBER");
            System.out.print(prompt);
            scanner.nextLine();
        }
        input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public List<String> getFileContentsArray(String fileName) {
        List<String> fileContents = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNext()) {
                fileContents.add(fileScanner.next().toLowerCase());
            }
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }

        return fileContents;
    }

    public String getFileContentsString(String fileName) {
        StringBuilder contents = new StringBuilder();
        try {
                Stream<String> stream = Files.lines( Paths.get(fileName), StandardCharsets.UTF_8);
                stream.forEach(s -> contents.append(s).append("\n"));
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }

        return contents.toString();
    }
}

