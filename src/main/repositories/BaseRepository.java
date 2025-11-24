package main.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static resource.ConfigFile.*;

/**
 * Base repository class for database operations.
 * Improvement notes: only one connection should be opened at a time.Can be shared between repositories.
 * Eventually Scaling would need to be considered because this is a single hard coded connection.
 */
public class BaseRepository {
    /**
     * Retrieves a connection to the database.
     * @return: Connection object
     * @throws SQLException: if a database access error occurs
     */
    protected Connection getConnection() throws SQLException {
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
