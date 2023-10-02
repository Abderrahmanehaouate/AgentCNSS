package ma.youcode.Helpers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class Helpers {

    public String validateStringInput(String script, String prompt){
        Scanner scanner = new Scanner(in);
        boolean state = true;
        String input = "";
        while (state) {
            out.print(script);
            input = scanner.nextLine();

            if (input.isEmpty()) {
                out.println("Please enter a value that is not empty for the " + prompt + ": ");
            } else {
                state = false;
            }
        }
        return input;
    }

    public int validateIntegerInput(String script, String prompt) {
        Scanner scanner = new Scanner(System.in);
        boolean state = true;
        int input = 0;

        while (state) {
            System.out.print(script + " : ");

            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                state = false;
            } else {
                System.out.println("Please enter a valid integer for " + prompt + ": ");
                scanner.nextLine(); // Clear the input buffer
            }
        }
        return input;
    }

    public float validateFloatInput(String script, String prompt) {
        Scanner scanner = new Scanner(System.in);
        boolean state = true;
        float input = 0.0f;

        while (state) {
            System.out.print(script);

            if (scanner.hasNextFloat()) {
                input = scanner.nextFloat();
                state = false;
            } else {
                System.out.println("Please enter a valid floating-point number for " + prompt + ": ");
                scanner.nextLine(); // Clear the input buffer
            }
        }
        return input;
    }
    public float validateLongInput(String script, String prompt) {
        Scanner scanner = new Scanner(System.in);
        boolean state = true;
        Long input = 0L;

        while (state) {
            System.out.print(script);

            if (scanner.hasNextLong()) {
                input = scanner.nextLong();
                state = false;
            } else {
                System.out.println("Please enter a valid floating-point number for " + prompt + ": ");
                scanner.nextLine(); // Clear the input buffer
            }
        }
        return input;
    }

    public String generateDossierCode() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(new Date());
        Random random = new Random();

        int randomNumber = 10 + random.nextInt(90);
        String dossierCode = timestamp;

        return dossierCode;
    }

}
