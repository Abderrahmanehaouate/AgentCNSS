package ma.youcode.Views.Document;

import ma.youcode.Controllers.DocumentController;
import ma.youcode.Controllers.PatientController;
import ma.youcode.Helpers.Helpers;
import ma.youcode.Models.Document;
import ma.youcode.Views.MainView;

import javax.print.attribute.standard.MediaSize;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class DocumentView {
    Helpers helpers = new Helpers();
    DocumentController documentController = new DocumentController();
    PatientController patientController = new PatientController();

    public void shooseType(Long DossierCode){
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
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

    public void displayDocuments(){
        long matriculePatient = helpers.validateIntegerInput("Entrer the value of Patient matricule", "Matricule");
        boolean check = patientController.checkMatriculePatientIfExist(matriculePatient);
        if(!check){
            System.out.println("this matricule isn't exist, Try to insert an exist matricule\n");
        }else{
            out.println("donned");
            List<Document> documents = documentController.displayDocuments(matriculePatient);
            displayDocuments(documents);
        }
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