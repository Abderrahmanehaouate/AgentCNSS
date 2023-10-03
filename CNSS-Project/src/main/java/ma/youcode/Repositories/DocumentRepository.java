package ma.youcode.Repositories;

import ma.youcode.Database.Database;
import ma.youcode.Models.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentRepository {
    private final Connection connection;
    public DocumentRepository(Connection connection){
        this.connection = connection;
    }

    public boolean create(Document document){
        PreparedStatement preparedStatement = null;
        try {
            String inserQuery = "INSERT INTO document (DossierCode, RemboursementRate, Price, Type) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(inserQuery);
            preparedStatement.setLong(1, document.getDossierCode());
            preparedStatement.setFloat(2, document.getRemboursementRate());
            preparedStatement.setFloat(3, document.getPrice());
            preparedStatement.setString(4, document.getType());

            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){

                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();

            return false;
        }

        return false;
    }

    public int checkAndGetMedicamentPrice(Long CodeBareMedicament){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String checkReservationQuery = "SELECT price FROM medicament WHERE CodeBarre = ?";
            preparedStatement = connection.prepareStatement(checkReservationQuery);
            preparedStatement.setLong(1, CodeBareMedicament);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("price");
            } else {
                System.out.println("No matricule Found , this code bare not exist " + CodeBareMedicament);
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;

        }
    }

    public float GetDocumentPrice(Long dossierCode){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String checkReservationQuery = "SELECT price FROM document WHERE DossierCode = ?";
            preparedStatement = connection.prepareStatement(checkReservationQuery);
            preparedStatement.setLong(1, dossierCode);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getFloat("price");
            } else {
                System.out.println("No dossier Found , this dossier not exist " + dossierCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean updatePriceOfDocument(Long dossierCode, float newPrice) {
        PreparedStatement preparedStatement = null;

        try {
            String updateDocumentPriceQuery = "UPDATE document SET price = ? WHERE DossierCode = ?";
            preparedStatement = connection.prepareStatement(updateDocumentPriceQuery);
            preparedStatement.setFloat(1, newPrice);
            preparedStatement.setLong(2, dossierCode);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("test update price");
                return true;
            } else {
                System.out.println("No document found with code: " + dossierCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Document> getAllDossierOfPatient(long patientMatricule) {
        List<Document> documents = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String selectQuery = "SELECT d.DossierCode, d.Status, d.PatientMatricule, " +
                    "doc.type, doc.price, doc.RemboursementRate " +
                    "FROM dossier d " +
                    "INNER JOIN document doc ON d.DossierCode = doc.DossierCode " +
                    "WHERE d.PatientMatricule = ?";

            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setLong(1, patientMatricule);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int dossierCode = resultSet.getInt("DossierCode");
                String status = resultSet.getString("Status");
                Long patientMatriculeFromDossier = resultSet.getLong("PatientMatricule");
                String type = resultSet.getString("type");
                float price = resultSet.getFloat("price");
                float remboursementRate = resultSet.getFloat("RemboursementRate");

                Document document = new Document();
                document.getDossier().setDossierCode(dossierCode);
                document.getDossier().setStatus(status);
                document.getPatient().setMatricule(patientMatriculeFromDossier);
                document.setType(type);
                document.setPrice(price);
                document.setRemboursementRate(remboursementRate);

                documents.add(document);
                System.out.println("done from repository");
            }
        } catch (SQLException e) {
            System.out.println("not don yet");
            e.printStackTrace();
        }

        return documents;
    }

}
