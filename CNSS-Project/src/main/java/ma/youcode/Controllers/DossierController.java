package ma.youcode.Controllers;

import ma.youcode.Database.Database;
import ma.youcode.Models.Dossier;
import ma.youcode.Repositories.DossierRepository;

import java.sql.Connection;
import java.util.List;

public class DossierController {
    private final DossierRepository repository;
    public DossierController(){
        Connection connection = Database.getInstance();
        this.repository = new DossierRepository(connection);
    }

    public void createDossier(long matriculePatient) {
        repository.createDossier(matriculePatient);
    }

    public Long getDossierCodeByMatriculePatient(Long matriculePatient){
        return repository.getDossierCodeByMatriculePatient(matriculePatient);
    }

    public List<Dossier> getAllDossiersForPatient(long patientMatricule) {
        return repository.getAllDossiersByPatientMatricule(patientMatricule);
    }

    public boolean checkIfPatientHasDossier(Long matriculePatient){
        if(repository.checkIfPatientHasDossier(matriculePatient)){
            return true;
        }else {
            return false;
        }
    }

    public boolean checkIfDossierExist(Long dossierCode){
        if(repository.checkIfDossierExist(dossierCode)){
            return true;
        }else {
            return false;
        }
    }
}
