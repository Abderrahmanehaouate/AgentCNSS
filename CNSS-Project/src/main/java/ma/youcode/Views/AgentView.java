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
        MainView mainView = new MainView();
        mainView.start();
    }
}
