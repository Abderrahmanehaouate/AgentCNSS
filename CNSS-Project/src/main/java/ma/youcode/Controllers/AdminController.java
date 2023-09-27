package ma.youcode.Controllers;

import ma.youcode.Repositories.AdminRepo;
import ma.youcode.Views.AdminView;
import ma.youcode.Models.Admin;


public class AdminController {
    private static AdminRepo model;
    public static AdminView view;

    public AdminController() {
        model = new AdminRepo();
        view = new AdminView();
    }

    public void run() {
        view.displayMessage("Welcome to the Admin Console");

        boolean loggedIn = false;
        while (!loggedIn) {
            String username = view.getInput("Enter username: ");
            String password = view.getInput("Enter password: ");

            Admin admin = new Admin(username, password);

            if (model.isValidAdmin(admin)) {
                loggedIn = true;
                view.displayMessage("Logged in as admin.");
                view.displayAdminOptions();
            } else {
                showError("Invalid credentials. Please try again.");
            }
        }
    }



    public static void addAgentOption() {
        String Email = view.getInput("Enter agent's Email: ");
        String Password = view.getInput("Enter agent's Password: ");

        if (model.addAgent(Email, Password)) {
            view.displayMessage("Agent added successfully.");
        } else {
            showError("Failed to add agent. Please try again.");
        }
    }

    public static void reactivateAgentOption() {
        String Email = view.getInput("Enter agent's Email to reactivate: ");

        if (model.reactivateAgent(Email)) {
            view.displayMessage("Agent reactivated successfully.");
        } else {
            showError("Failed to reactivate agent. Please check the email and try again.");
        }
    }

    public static void showError(String errorMessage) {
        view.displayMessage("Error: " + errorMessage);
    }
}
