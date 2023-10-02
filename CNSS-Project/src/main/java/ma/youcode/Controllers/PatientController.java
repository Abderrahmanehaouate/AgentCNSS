package ma.youcode.Controllers;


import ma.youcode.Database.Database;
import ma.youcode.Models.Dossier;
import ma.youcode.Models.Patient;
import ma.youcode.Repositories.PatientRepository;

import java.sql.Connection;

public class PatientController {
    private final PatientRepository repository;
    private Dossier dossier;
    private Patient patient;

    public PatientController() {
        Connection connection = Database.getInstance();
        this.repository = new PatientRepository(connection);
    }

    public boolean checkMatriculePatientIfExist(long matriculePatient){
        if(repository.checkMatriculePatientIfExist(matriculePatient)){
            return true;
        }else{
            return false;
        }
    }
}
