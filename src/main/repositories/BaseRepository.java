package main.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

import static resource.ConfigFile.*;

/**
 * Base repository class for database operations.
 * Improvements to note: Batch calls, update, delete, and what if we use a different database?
 */
public class BaseRepository {
    /**
     * Inserts a new record into the database.
     * @param sql: SQL statement to be executed
     */
    protected void insert(String sql) {
        int generatedId = -1;
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    generatedId = keys.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Insert failed: " + e.getMessage(), e);
        }
        if (generatedId == -1) {
            throw new RuntimeException("Insert failed: No generated key returned");
        }
    }
    /**
     * Executes a query and returns the result set as a list of maps.
     * @param sql: SQL statement to be executed
     * @return: List of maps representing the result set
     */
    protected List<Map<String, Object>> query(String sql) {
        List<Map<String, Object>> results = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            ResultSetMetaData meta = rs.getMetaData();
            int columns = meta.getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                for (int i = 1; i <= columns; i++) {
                    row.put(meta.getColumnLabel(i), rs.getObject(i));
                }
                results.add(row);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Query failed: " + e.getMessage(), e);
        }

        return results;
    }

    /**
     * Retrieves a connection to the database.
     * @return: Connection object
     * @throws SQLException: if a database access error occurs
     */
    private Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(
                    DATABASE_URL,
                    DATABASE_USER,
                    DATABASE_PASSWORD
            );
        } catch (SQLException e) {
            throw new SQLException("Error while trying to connect to Database. ",e);
        }
    }
}
