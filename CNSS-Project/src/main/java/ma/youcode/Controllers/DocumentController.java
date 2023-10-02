package ma.youcode.Controllers;

import ma.youcode.Database.Database;
import ma.youcode.Models.Document;
import ma.youcode.Repositories.DocumentRepository;

import java.sql.Connection;
import java.util.List;

public class DocumentController {
    private final DocumentRepository documentRepository;
    public DocumentController(){
        Connection connection = Database.getInstance();
        this.documentRepository = new DocumentRepository(connection);
    }

    public boolean create(Long dossierCode, float remboursementRate, float price, String type){

        float returnPrice = calculerReturnPrice(remboursementRate, price);
        Document document = new Document(dossierCode, remboursementRate, returnPrice, type);

        return documentRepository.create(document);
    }

    public float calculerReturnPrice(float RemboursementRate, float Price) {
        if (RemboursementRate < 0 || RemboursementRate > 100000) {
            throw new IllegalArgumentException("RemboursementRate must be between 0 and 100000");
        }
        float returnAmount = (RemboursementRate / 100) * Price;

        return returnAmount;
    }

    public int checkAndGetMedicamentPrice(Long CodeBareMedicament){

        return documentRepository.checkAndGetMedicamentPrice(CodeBareMedicament);
    }
    public float GetDocumentPrice(Long dossierCode){
        return documentRepository.GetDocumentPrice(dossierCode);
    }

    public boolean updatePriceOfDocument( Long dossierCode, float NewPrice){

        return documentRepository.updatePriceOfDocument(dossierCode, NewPrice);
    }

    public List<Document> displayDocuments(long patientMatricule){
        return documentRepository.getAllDossierOfPatient(patientMatricule);
    }
}
