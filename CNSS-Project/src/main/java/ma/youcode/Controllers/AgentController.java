package ma.youcode.Controllers;

import ma.youcode.Models.Agent;
import ma.youcode.Repositories.AgentRepo;
import ma.youcode.Views.AgentView;
import ma.youcode.Helpers.EmailSender;

import java.sql.Connection;

public class AgentController {
    private AgentRepo model;
    public static AgentView view;
    private Connection connection;
    private final String verificationCode = "161199";

    public AgentController(Connection connection) {
        this.connection = connection;
        model = new AgentRepo();
        view = new AgentView();
    }

    public void run() {
        view.displayMessage("Welcome to the Agent Console");

        boolean loggedIn = false;
        int failedLoginAttempts = 0;

        while (!loggedIn && failedLoginAttempts < 3) {
            String email = view.getInput("Enter Email: ");
            String password = view.getInput("Enter password: ");

            Agent agent = new Agent(email, password, "1");

            if (model.isValidAgent(agent)) {

                if (failedLoginAttempts == 0) {
                    sendVerificationCodeEmail(agent.getEmail());
                    view.displayMessage("Verification code sent to your email. Please check your inbox.");
                }

                if (verifyEmailCode(agent.getEmail())) {
                    loggedIn = true;
                    view.displayMessage("Logged in as Agent.");
                    view.displayAgentOptions();
                } else {
                    view.displayMessage("Invalid verification code. Please try again.");
                }
            } else {
                view.displayMessage("Invalid credentials. Please try again.");
                failedLoginAttempts++;
            }
        }
    }

    private boolean verifyEmailCode(String email) {
        String enteredCode = view.getInput("Enter Verification Code: ");
        return enteredCode.equals(verificationCode);
    }

    private void sendVerificationCodeEmail(String email) {
        String subject = "Verification Code";
        String body = "Your verification code is: " + verificationCode;
        boolean sent = EmailSender.sendMail(body, subject, email);
        if (!sent) {
            view.displayMessage("Failed to send the verification code email. Please try again.");
        }
    }
}
