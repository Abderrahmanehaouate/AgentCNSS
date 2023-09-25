package ma.youcode.Views;


import java.util.Scanner;


import static ma.youcode.Controllers.AgentController.view;

public class AgentView {
    private Scanner scanner;

    public AgentView() {
        scanner = new Scanner(System.in);
    }

    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayAgentOptions() {
        while (true) {
            view.displayMessage("Options:");
            view.displayMessage("1. Add Dossier");
            view.displayMessage("2. Exit");

            String choice = view.getInput("Enter your choice: ");
            switch (choice) {
                case "1":
                    //Ajouti dossier hna
                    break;
                case "2":
                    view.displayMessage("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    view.displayMessage("Invalid choice.");
            }
        }
    }
}
