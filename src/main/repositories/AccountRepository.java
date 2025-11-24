package main.repositories;

import external.credit.CreditScore;
import main.models.Account;

import java.sql.*;
import java.time.LocalDate;

/**
 * Repository class for Account. Handles all database operations related to Account.
 * Improvement notes: findAll, batch calls, update, delete, and security risks with the current approach
 */
public class AccountRepository extends BaseRepository {

    /**
     * Retrieves an account from the database based on the provided AccountId.
     * @param accountId: account id of the account to be retrieved
     * @return Account: retrieved account or null if no account was found
     */
    public Account findOne(String accountId) {
        String sql = "SELECT * FROM ACCOUNT WHERE ACCOUNTID = ?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, accountId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Query failed: " + e.getMessage(), e);
        }
    }

    /**
     * Inserts an account into the database.
     * @param account: account to be inserted
     */
    public void insert(Account account) {
        String sql = "INSERT INTO ACCOUNT (ACCOUNTID, FIRSTNAME, LASTNAME, FISCALNUM, BIRTHDATE, CREDITSCORE, CLIENTTYPE, CREDITLIMIT) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)" ;
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, account.getId());
            stmt.setString(2, account.getFirstName());
            stmt.setString(3, account.getLastName());
            stmt.setLong(4, account.getFiscalNumber());
            stmt.setLong(5, account.getBirthdate().toEpochDay());
            stmt.setString(6, account.getCreditScore().name());
            stmt.setString(7, account.getClientType());
            stmt.setInt(8, account.getCreditLimit());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Insert failed: " + e.getMessage(), e);
        }
    }

    /**
     * Maps a row from the database to an Account object.
     * @param rs: result set from the database
     * @return: Account
     */
    private Account mapRow(ResultSet rs) throws SQLException {
        Account a = new Account();
        a.setId(rs.getString("ACCOUNTID"));
        a.setFirstName(rs.getString("FIRSTNAME"));
        a.setLastName(rs.getString("LASTNAME"));
        a.setFiscalNumber(rs.getLong("FISCALNUM"));
        a.setBirthdate(LocalDate.ofEpochDay(rs.getLong("BIRTHDATE")));
        a.setCreditScore(CreditScore.valueOf(rs.getString("CREDITSCORE")));
        a.setClientType(rs.getString("CLIENTTYPE"));
        a.setCreditLimit(rs.getInt("CREDITLIMIT"));
        return a;
    }
}
