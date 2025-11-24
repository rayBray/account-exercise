package main.repositories;

import external.credit.CreditScore;
import main.models.Account;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Repository class for Account. Handles all database operations related to Account.
 */
public class AccountRepository extends BaseRepository {

    /**
     * Retrieves an account from the database based on the provided AccountId.
     * @param accountId: account id of the account to be retrieved
     * @return Account: retrieved account or null if no account was found
     */
    public Account findOne(int accountId) {
        String sql = "SELECT * FROM ACCOUNT WHERE ACCOUNTID = " + accountId; //sql injection? Never heard of it.
        List<Map<String, Object>> rows = query(sql);

        if (rows.isEmpty()) {
            return null;
        }
        return mapRow(rows.get(0));
    }

    /**
     * Inserts an account into the database.
     * @param account: account to be inserted
     */
    public void insert(Account account) {
        String sql = "INSERT INTO ACCOUNT (ACCOUNTID, FIRSTNAME, LASTNAME, FISCALNUM, BIRTHDATE, CREDITSCORE, CLIENTTYPE, CREDITLIMIT) " +
                "VALUES '" +
                account.getId() + "', '" +
                account.getFirstName() + "', '" +
                account.getLastName() + "', " +
                account.getFiscalNumber() + "', '" +
                account.getBirthdate().toEpochDay() + "', '" +
                account.getCreditScore().name() + "', '" +
                account.getClientType() + "', '" +
                account.getCreditLimit() + "';";
        insert(sql);
    }

    /**
     * Maps a row from the database to an Account object.
     * @param row: row from the database
     * @return: Account
     */
    private Account mapRow(Map<String, Object> row) {
        Account a = new Account();

        a.setId((Integer) row.get("ACCOUNTID"));
        a.setFirstName((String) row.get("FIRSTNAME"));
        a.setLastName((String) row.get("LASTNAME"));
        a.setFiscalNumber((Long) row.get("FISCALNUM"));
        a.setBirthdate(LocalDate.ofEpochDay((Long) row.get("BIRTHDATE")));
        a.setCreditScore(CreditScore.valueOf((String) row.get("CREDITSCORE")));
        a.setClientType((String) row.get("CLIENTTYPE"));
        a.setCreditLimit((Integer) row.get("CREDITLIMIT"));

        return a;
    }
}
