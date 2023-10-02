package ma.youcode.Repositories;

import ma.youcode.Models.Dossier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DossierRepository {
    private final Connection connection;

    public DossierRepository(Connection connection) {
        this.connection = connection;
    }

    public void createDossier(Long matriculePatient){
        PreparedStatement preparedStatement = null;

        try {
            String insertQuery = "INSERT INTO Dossier (PatientMatricule) VALUES (?)";

            preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setLong(1, matriculePatient);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("DossierYou has been added.");
            } else {
                System.out.println("Dossier not be added.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Long getDossierCodeByMatriculePatient(Long matriculePatient) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String checkReservationQuery = "SELECT DossierCode FROM dossier WHERE PatientMatricule = ?";
            preparedStatement = connection.prepareStatement(checkReservationQuery);
            preparedStatement.setLong(1, matriculePatient);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getLong("DossierCode");
            } else {
                System.out.println("No matricule Found , this matricule not exist " + matriculePatient);
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Dossier> getAllDossiersByPatientMatricule(long patientMatricule) {
        List<Dossier> dossiers = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String selectQuery = "SELECT * FROM dossier WHERE PatientMatricule = ?";
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setLong(1, patientMatricule);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Dossier dossier = new Dossier();
                dossier.setDossierCode(resultSet.getInt("DossierCode"));
                dossier.setStatus(resultSet.getString("Status"));
                dossier.setPatientMatricule(resultSet.getLong("PatientMatricule"));

                dossiers.add(dossier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dossiers;
    }
}
