package ma.youcode.Views;

import ma.youcode.Views.Document.DocumentView;
import ma.youcode.Views.Document.DossierView;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

    public class MainView {
        DocumentView documentView = new DocumentView();
        DossierView dossierView = new DossierView();
        public void start(){
            String[] options = getStrings();
            Scanner scanner = new Scanner(in);
            while (true){
                printMenu(options);
                try {
                    int option = scanner.nextInt();

                    switch(option){
                        case 1:
                            dossierView.createDossier();
                            break;
                        case 2:
                            break;
                        case 3:

                            break;
                        case 4:

                            break;
                        case 5:

                            break;
                        case 6:

                            break;
                        case 7:

                            break;
                        case 8:

                            break;
                        case 9: break;
                        case 10:

                            break;
                        default:
                            System.out.println("\n Option not found, please select an existing option.\n");
                            break;
                    }
                }catch (Exception ex){
                    out.println("\nPlease enter an integer value between 1 and 9\n");
                    scanner.next();
                }
            }
        }
        public String[] getStrings() {
            String[] options;

            options = new String[]{
                    "------------- Library Menu Options ------------- |",
                    "-------------------------------------------------|",
                    "1 - add a dossier                                |",
                    "-------------------------------------------------|",
                    "2 - add a document to dossier                    |",
                    "-------------------------------------------------|",
                    "3 - accepter  ou refuser a dossier               |",
                    "-------------------------------------------------|",
                    "4 - afficher un  dossier                         |",
                    "-------------------------------------------------|",
                    "5 - Exit                                         |",
                    "-------------------------------------------------|",
            };

            return options;
        }
        public void printMenu(String[] options){
            out.println("\t\t\t\t __________________________________________________");
            for (String option : options){
                out.println("\t\t\t\t| " + option);
            }
            out.print("\n\t\t\t\t Choose your option : ");
        }
    }
