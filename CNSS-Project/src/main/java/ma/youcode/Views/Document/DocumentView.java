package ma.youcode.Views.Document;

import ma.youcode.Controllers.DocumentController;
import ma.youcode.Controllers.DossierController;
import ma.youcode.Controllers.PatientController;
import ma.youcode.Helpers.Helpers;
import ma.youcode.Models.Document;
import ma.youcode.Views.MainView;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class DocumentView {
    Helpers helpers = new Helpers();
    DocumentController documentController = new DocumentController();
    PatientController patientController = new PatientController();
    DossierController dossierController = new DossierController();

    public void shooseType(Long DossierCode){
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                out.println("\nShoose Type of your maladie : ");
                out.println("1 -  analyses de laboratoire");
                out.println("2 -  des radios ou scanner ");
                out.println("3 -  médicament");
                out.print("Choose your option: ");

                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        String typeAnalyse = "analyses de laboratoire";
                        createDocument(DossierCode, typeAnalyse);
                        break;
                    case 2:
                        String type = "radio ou scanner";
                        createDocument(DossierCode, type);
                        break;
                    case 3:
                        String typeMedicament = "medicament";
                        TypeMedicament(DossierCode, typeMedicament);
                        break;
                    default:
                        System.out.print("\nOption not found, please select an existing option.");
                        scanner.next();
                        break;
                }
            }catch (Exception ex){
                out.print("Please enter an integer value between 1 and 3");
                scanner.next();
            }
        }
    }


    public void createDocument(Long dossierCode, String type){
        float price = helpers.validateFloatInput("Enter the value of Price : ", "Price");
        float remboursementRate = 70; // 70% from price return

        if(documentController.create(dossierCode, remboursementRate, price, type)){
            out.println("\nDocument is created successfully\n");
            subCreateDossier(dossierCode);
        }
    }
    public void TypeMedicament(Long dossierCode, String type){
        Long medicamentCodeBare = (long) helpers.validateLongInput("Enter Code Bare of medicament : ", "Medicament Code Bare");

        int price = documentController.checkAndGetMedicamentPrice(medicamentCodeBare);
        float remboursementRate = 70;

        if(documentController.create(dossierCode, remboursementRate, price, type)){
            out.println("price registered with remboursement rate");
            insertAnotherPrice(dossierCode, remboursementRate);
        }
    }

    public void insertAnotherPrice(Long dossierCode, float remboursementRate){
        while(true){
            Scanner scanner = new Scanner(System.in);
            out.println("\n 1 - Do you want to insert another medicament");
            out.println(" 2 - Return the menu");
            out.println("3 - exit");
            int option = scanner.nextInt();
            if(option == 1){
                Long OtherMedicamentCodeBare = (long) helpers.validateLongInput("Enter Code Bare of medicament : ", "Medicament Code Bare");
                float PriceOfMedicament = documentController.checkAndGetMedicamentPrice(OtherMedicamentCodeBare);
                float OldPriceOfDocument = documentController.GetDocumentPrice(dossierCode);
                float NewPrice = OldPriceOfDocument +  documentController.calculerReturnPrice(remboursementRate, PriceOfMedicament);
                if(documentController.updatePriceOfDocument(dossierCode, NewPrice)){
                    insertAnotherPrice(dossierCode, remboursementRate);
                }

            } else if (option == 2) {
                MainView mainView = new MainView();
                mainView.start();
            }else if (option == 3){
                System.exit(0);
            }
        }
    }



    public void subCreateDossier(Long dossierCode) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            out.println("1 - Create Another Document to This dossier");
            out.println("2 = return to menu");
            out.println("2 - Exit ");
            out.print("Choose your option: ");

            int option = scanner.nextInt();
            switch (option){
                case 1:
                    shooseType(dossierCode);
                    break;
                case 2:
                    MainView mainView = new MainView();
                    mainView.start();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    out.println("/n Option not found, please select an existing option.");
            }
            out.println("\noption not found, please try again\n");
            subCreateDossier(dossierCode);
        }
    }

    public void createDocumentToExistanseDossier(){

        long matriculePatient = helpers.validateIntegerInput("Entrer the value of Patient matricule", "Matricule");

        boolean check = patientController.checkMatriculePatientIfExist(matriculePatient);
        boolean checkIfPatientHasDossier = dossierController.checkIfPatientHasDossier(matriculePatient);

        if(!check){
            System.out.println("this matricule isn't exist, Try to insert an exist matricule\n");
            optionsCreateExistanseDossier();
        }
        if (!checkIfPatientHasDossier){
            System.out.println("this matricule Has no document , try to create a dossier\n");
            optionsCreateExistanseDossier();
        }

        Long DossierCode = (long) helpers.validateLongInput("Entrer the value of dossier code : ", "Dossier Code");
        boolean checkIfDossierExist = dossierController.checkIfDossierExist(DossierCode);

        if(!checkIfDossierExist){
            System.out.println("this Dossier is not exist , try to create a dossier.\n");
            optionsCreateExistanseDossier();
        }

        if(check && checkIfPatientHasDossier && checkIfDossierExist){
            shooseType(DossierCode);
        }else{
            out.println("something wrong");
            optionsCreateExistanseDossier();
        }

    }


    public void optionsCreateExistanseDossier() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            out.println("1 - try again");
            out.println("2 = return to menu");
            out.println("2 - Exit ");
            out.print("Choose your option: ");

            int option = scanner.nextInt();
            switch (option){
                case 1:
                    createDocumentToExistanseDossier();
                    break;
                case 2:
                    MainView mainView = new MainView();
                    mainView.start();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    out.println("/n Option not found, please select an existing option.");
            }
            out.println("\noption not found, please try again\n");
            optionsCreateExistanseDossier();
        }
    }

    public void subCreateDossier() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            out.println("1 - Accepter a dossier");
            out.println("2 - Refuser a dossier");
            out.println("3 - return to main menu");
            out.println("4 - Exit ");
            out.print("Choose your option: ");

            int option = scanner.nextInt();
            switch (option){
                case 1:
                    acceptDocument();
                    break;
                case 2:
                    refuseDocument();
                    break;
                case 3:
                    MainView mainView = new MainView();
                    mainView.start();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    out.println("/n Option not found, please select an existing option.");
            }
            out.println("\noption not found, please try again\n");
        }
    }

    public void acceptDocument(){

    }
    public void refuseDocument(){

    }
    public void displayDocuments(List<Document> documents) {
        System.out.println("Patient's Documents:");
        System.out.println("====================");

        for (Document document : documents) {
            System.out.println("Dossier Code: " + document.getDossier().getDossierCode());
            System.out.println("Status: " + document.getDossier().getStatus());
            System.out.println("Patient Matricule: " + document.getPatient().getMatricule());
            System.out.println("Document Type: " + document.getType());
            System.out.println("Price: " + document.getPrice());
            System.out.println("Remboursement Rate: " + document.getRemboursementRate());
            System.out.println("----------------------------");
        }
    }
}