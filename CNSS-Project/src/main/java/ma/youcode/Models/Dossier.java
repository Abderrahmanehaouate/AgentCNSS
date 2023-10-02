package ma.youcode.Models;

public class Dossier {
    private long PatientMatricule;
    private Patient patient;
    private int DossierCode;
    private String Status;

    public void setPatientMatricule(long patientMatricule) {
        PatientMatricule = patientMatricule;
    }

    public void setDossierCode(int dossierCode) {
        DossierCode = dossierCode;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public long getPatientMatricule() {
        return PatientMatricule;
    }

    public int getDossierCode() {
        return DossierCode;
    }

    public String getStatus() {
        return Status;
    }
}
