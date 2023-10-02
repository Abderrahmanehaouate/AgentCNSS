package ma.youcode.Views.Document;

import ma.youcode.Controllers.DossierController;
import ma.youcode.Controllers.PatientController;
import ma.youcode.Helpers.Helpers;
import ma.youcode.Models.Dossier;


import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class DossierView {
    Helpers helpers = new Helpers();
    PatientController patientController = new PatientController();
    DossierController dossierController = new DossierController();
    DocumentView documentView = new DocumentView();
    public void createDossier(){
        long matriculePatient = helpers.validateIntegerInput("Entrer the value of Patient matricule", "Matricule");
        boolean check = patientController.checkMatriculePatientIfExist(matriculePatient);
        if(!check){
            System.out.println("this matricule isn't exist, Try to insert an exist matricule\n");
            subCreateDossier();
        }
        dossierController.createDossier(matriculePatient);
        Long DossierCode = dossierController.getDossierCodeByMatriculePatient(matriculePatient);
        out.println("Shoose Type of your maladie : ");
        documentView.shooseType(DossierCode);
    }

    public void subCreateDossier() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            out.println("1 - Try To create Another Dossier");
            out.println("2 - Exit ");
            out.print("Choose your option: ");

            int option = scanner.nextInt();

            if (option == 1) {
                createDossier();
            } else if (option == 2) {
                System.exit(0);
            }else{
                out.println("\noption not found, please try again\n");
                subCreateDossier();
            }
        }
    }


    public void displayDossiers() {
        long matriculePatient = helpers.validateIntegerInput("Entrer the value of Patient matricule", "Matricule");
        boolean check = patientController.checkMatriculePatientIfExist(matriculePatient);
        if(!check){
            System.out.println("this matricule isn't exist, Try to insert an exist matricule\n");
        }
        List<Dossier> dossiers = dossierController.getAllDossiersForPatient(matriculePatient);

        System.out.println("Patient's Dossiers:");
        System.out.println("====================");

        for (Dossier dossier : dossiers) {
            System.out.println("Dossier Code: " + dossier.getDossierCode());
            System.out.println("Status: " + dossier.getStatus());
            System.out.println("Patient Matricule: " + dossier.getPatientMatricule());
            System.out.println("----------------------------");
        }
    }
}
