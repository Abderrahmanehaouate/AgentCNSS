package ma.youcode.Repositories;

import ma.youcode.Models.Dossier;
import ma.youcode.Models.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientRepository {
    private  final Connection connection;
    public PatientRepository(Connection connection){
        this.connection = connection;
    }

    public boolean checkMatriculePatientIfExist(long matriculePatient) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String selectQuery = "SELECT Matricule FROM patient WHERE Matricule = ?";
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setLong(1, matriculePatient);
            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
