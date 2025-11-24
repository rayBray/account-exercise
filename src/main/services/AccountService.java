package main.services;

import external.credit.CreditScore;
import main.gateways.CreditScoreGateway;
import main.models.AccountCategory;

import java.time.LocalDate;
import java.time.Period;

/**
 * Service class for Accounts. Handles all business logic for Accounts.
 * Improvement notes: Update java to make the switch statement more simple.
 */
public class AccountService {

    /**
     * Fetches the account category based on the credit score
     * @param fiscalNumber: account users fiscal number
     * @return AccountCategory: returns the account category based on the credit score
     * @throws Exception: throws an exception if the credit score cannot be retrieved
     */
    public AccountCategory fetchAccountCategory(Long fiscalNumber) throws Exception {
        CreditScore score = new CreditScoreGateway().getCreditScore(fiscalNumber);
        AccountCategory accountCategory;
        switch (score) {
            case BAD:
                accountCategory = new AccountCategory(score, "Ivory", 0);
                break;
            case GOOD:
                accountCategory = new AccountCategory(score, "Silver", 1000);
                break;
            case EXCELLENT:
                accountCategory = new AccountCategory(score, "Gold", 5000);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + score);
        }
        return accountCategory;
    }

    /**
     * Validates basic account information
     * @param firstName: account users first name
     * @param lastName: account users last name
     * @param birthdate: account users birthdate
     * @param fiscalNumber: account users fiscal number
     * @throws Exception: throws an exception if any of the basic account information is invalid
     */
    public void validateBasicInformation(String firstName, String lastName, LocalDate birthdate, long fiscalNumber)
            throws Exception {
        validateName(firstName);
        validateName(lastName);
        validateAge(birthdate);
        validateFiscalNumber(fiscalNumber);
    }

    /**
     * Validates the name of an account user
     * @param name: name of an account user
     * @throws Exception: throws an exception if the name is invalid
     */
    private void validateName(String name) throws Exception {
        if(name == null || name.isBlank()) {
            throw new Exception("Name is empty");
        }
    }

    /**
     * Validates the age of an account user
     * @param birthdate: birthdate of an account user
     * @throws Exception: throws an exception if the age is invalid
     */
    private void validateAge(LocalDate birthdate) throws Exception {
        int years = Period.between(birthdate, LocalDate.now()).getYears();
        if (years < 18) {
            throw new Exception("Cannot open a bank main.controllers for a minor," +
                    " must attach main.controllers to an adult parent");
        }
    }

    /**
     * Validates the fiscal number of an account user
     * @param fiscalNumber: fiscal number of an account user
     * @throws Exception: throws an exception if the fiscal number is invalid
     */
    private void validateFiscalNumber(long fiscalNumber) throws Exception {
        String fiscalNumberString = Long.toString(fiscalNumber);
        if(fiscalNumberString.length() != 13){
            throw new Exception("Fiscal number must be 13 digits");
        }
    }
}
