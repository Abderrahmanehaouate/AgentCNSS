package ma.youcode.Views;

import ma.youcode.Controllers.AdminController;

import java.util.Scanner;

import static ma.youcode.Controllers.AdminController.view;

public class AdminView {


    private Scanner scanner;

    public AdminView() {
        scanner = new Scanner(System.in);
    }

    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayAdminOptions() {
        while (true) {
            view.displayMessage("Options:");
            view.displayMessage("1. Add Agent");
            view.displayMessage("2. Reactivate Agent Account");
            view.displayMessage("3. Exit");

            String choice = view.getInput("Enter your choice: ");
            switch (choice) {
                case "1":
                    AdminController.addAgentOption();
                    break;
                case "2":
                    AdminController.reactivateAgentOption();
                    break;
                case "3":
                    view.displayMessage("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    AdminController.showError("Invalid choice.");
            }
        }
    }
}

