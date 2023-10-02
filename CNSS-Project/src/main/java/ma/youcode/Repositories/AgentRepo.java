package ma.youcode.Repositories;

import ma.youcode.Helpers.Database;
import ma.youcode.Models.Agent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AgentRepo {
    private Connection connection;

    public AgentRepo() {
        Database dbConnector = new Database();
        connection = dbConnector.getConnection();
    }

    public boolean isValidAgent(Agent agent) {
        String sql = "SELECT * FROM agent WHERE Email = ? AND Password = ? AND Validation = 1";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, agent.getEmail());
            preparedStatement.setString(2, agent.getPassword());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setValidationStatus(Agent agent, int status) {
        String sql = "UPDATE agent SET Validation = ? WHERE Email = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, status);
            preparedStatement.setString(2, agent.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
