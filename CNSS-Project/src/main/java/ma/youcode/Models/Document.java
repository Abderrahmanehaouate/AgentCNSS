package ma.youcode.Models;

public class Document {
    private int ID;
    private Long DossierCode;
    private float RemboursementRate;
    private float Price;
    private String Type;
    private Patient patient;



    public Document() {
    }

    public Document(Long dossierCode, float remboursementRate, float price, String type) {
        DossierCode = dossierCode;
        RemboursementRate = remboursementRate;
        Price = price;
        Type = type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Long getDossierCode() {
        return DossierCode;
    }

    public void setDossierCode(Long dossierCode) {
        DossierCode = dossierCode;
    }

    public float getRemboursementRate() {
        return RemboursementRate;
    }

    public void setRemboursementRate(float remboursementRate) {
        RemboursementRate = remboursementRate;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    private Dossier dossier;

    public Dossier getDossier() {
        return dossier;
    }

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }
}
