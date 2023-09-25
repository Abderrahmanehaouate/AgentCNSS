package ma.youcode.Controllers;

import ma.youcode.Repositories.AgentRepo;

import ma.youcode.Models.Agent;
import ma.youcode.Views.AgentView;


import java.sql.Connection;

public class AgentController {
    private AgentRepo model;
    public static AgentView view;
    private Connection connection;

    public AgentController(Connection connection) {
        this.connection = connection;
        model = new AgentRepo();
        view = new AgentView();
    }

    public void run() {
        view.displayMessage("Welcome to the Agent Console");

        boolean loggedIn = false;
        int failedLoginAttempts = 0; // Counter for failed login attempts

        while (!loggedIn && failedLoginAttempts < 3) {
            String email = view.getInput("Enter Email: ");
            String password = view.getInput("Enter password: ");

            Agent agent = new Agent(email, password, "1"); // Create an Agent object

            if (model.isValidAgent(agent)) {
                loggedIn = true;
                view.displayMessage("Logged in as Agent.");
                view.displayAgentOptions();
            } else {
                view.displayMessage("Invalid credentials. Please try again.");
                failedLoginAttempts++;

                // Check if the agent has reached 3 failed login attempts
                if (failedLoginAttempts == 3) {
                    model.setValidationStatus(agent, 0); // Deactivate the account
                    view.displayMessage("Account deactivated due to 3 failed login attempts.");
                }
            }
        }
    }


}
