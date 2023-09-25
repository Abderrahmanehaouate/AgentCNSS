package ma.youcode.Repositories;

import ma.youcode.Database.Database;
import ma.youcode.Models.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepo {
    private Connection connection;

    public AdminRepo() {
        Database dbConnector = new Database();
        connection = dbConnector.getConnection();
    }

    public boolean isValidAdmin(Admin admin) {
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, admin.getUsername());
            preparedStatement.setString(2, admin.getPassword());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Returns true if a matching admin record is found
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Error occurred, return false
        }
    }

    public boolean addAgent(String Email, String Password) {
        String sql = "INSERT INTO agent (Email, Password) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, Email);
            preparedStatement.setString(2, Password);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Returns true if the agent is added successfully
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Error occurred while adding the agent
        }
    }

    public boolean reactivateAgent(String Email) {
        String sql = "UPDATE agent SET Validation = 1 WHERE Email = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, Email);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Returns true if the agent is reactivated successfully
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Error occurred while reactivating the agent
        }
    }
}
