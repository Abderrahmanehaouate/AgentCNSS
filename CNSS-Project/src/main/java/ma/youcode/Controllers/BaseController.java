package ma.youcode.Controllers;

import ma.youcode.Database.Database;
import ma.youcode.Views.BaseView;

import ma.youcode.Views.Document.DossierView;


import java.sql.Connection;
import java.sql.SQLException;

public class BaseController {
    public static BaseView view;
    private Connection connection;

    DossierView dossierView = new DossierView();
    public BaseController() {
        view = new BaseView();
        connection = Database.getInstance();

    }

    public void run() {
        view.displayMessage("Welcome to the Login System");

        String roleChoice = BaseView.chooseUserRole();

        if ("admin".equalsIgnoreCase(roleChoice)) {
            adminLogin();
        } else if ("agent".equalsIgnoreCase(roleChoice)) {
            agentLogin();
        } else if ("patient".equalsIgnoreCase(roleChoice)) {

            dossierView.displayDossiers();

        } else {
            view.displayMessage("Invalid role. Please try again.");
        }


        closeConnection();
    }

    private void adminLogin() {
        view.displayMessage("Admin Login");
        AdminController controller = new AdminController();
        controller.run();
    }

    private void agentLogin() {
        view.displayMessage("Agent Login");
        AgentController controller = new AgentController(connection);
        controller.run();
    }

    private void patientLogin(){
    }




    private void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
