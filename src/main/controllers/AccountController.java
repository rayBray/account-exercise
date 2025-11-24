package main.controllers;
import main.models.Account;
import main.models.AccountCategory;
import main.repositories.AccountRepository;
import main.services.AccountService;

import java.time.LocalDate;
import java.util.UUID;

/**
* Controller class for Accounts
* @author Rachel Brestansky
* @version 1.0
 * Improvement notes: Logs and monitoring (how long does a request take, how many requests are made,
 * what are the common failures, how are we handling failures. etc.).
 * An actual endpoint should be created for this. If http it should be a Post. This could be a consumer if
 * event-driven.
  */
public class AccountController {

    /**
    * Creates a new Account for a user based on the provided information and credit score.
     * Places a new account in the Account table in the database.
    * @param firstName: first name of an account user
    * @param lastName: last name of an account user
    * @param fiscalNumber: fiscal number of an account user
    * @param birthdate: birthdate of an account user
    * @return Account: returns the newly created account
    * @throws Exception: throws an exception if the account cannot be created
     */
    public Account createNewAccount(String firstName, String lastName, long fiscalNumber, LocalDate birthdate) throws Exception {
        AccountService accountService = new AccountService();
        accountService.validateBasicInformation(firstName, lastName, birthdate, fiscalNumber);
        //AccountCategory could be its own table:
        AccountCategory accountCategory = accountService.fetchAccountCategory(fiscalNumber);
        UUID uuid = UUID.randomUUID();

        // Switched three tables in to one:
        Account account = new Account(uuid.toString(),
                firstName,
                lastName,
                fiscalNumber,
                birthdate,
                accountCategory.getCreditScore(),
                accountCategory.getClientType(),
                accountCategory.getCreditLimit());

        AccountRepository accountRepository = new AccountRepository();
        accountRepository.insert(account);
        return account;
    }

}
