package ru.shchelkin;

import java.io.Closeable;
import java.util.List;
import java.util.Scanner;

public class ConsoleInterface implements Closeable {

    private final Scanner scanner = new Scanner(System.in);

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String errorMessage) {
        System.err.println(errorMessage);
    }

    public String requestString(String message) {
        System.out.println(message);

        return scanner.nextLine();
    }

    public int requestOption(String message, List<String> responses) {
        System.out.println(message);

        for (int i = 0; i < responses.size(); i++) {
            System.out.println(" %d %s".formatted(i + 1, responses.get(i)));
        }

        System.out.print(": ");


        return Integer.parseInt(scanner.nextLine());
    }

    public void close() {
        scanner.close();
    }
}
