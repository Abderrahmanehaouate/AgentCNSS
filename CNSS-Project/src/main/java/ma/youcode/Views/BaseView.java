package ma.youcode.Views;

import java.util.Scanner;

import static ma.youcode.Controllers.BaseController.view;

public class BaseView {
    private Scanner scanner;

    public BaseView() {
        scanner = new Scanner(System.in);
    }

    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public static String chooseUserRole() {
        while (true) {
            view.displayMessage("Choose your role:");
            view.displayMessage("1. Admin");
            view.displayMessage("2. Agent");
            view.displayMessage("3. Patient");

            String choice = view.getInput("Enter your choice: ");
            switch (choice) {
                case "1":
                    return "admin";
                case "2":
                    return "agent";
                case "3":
                    return "patient";
                default:
                    view.displayMessage("Invalid choice. Please try again.");
            }
        }
    }
}
