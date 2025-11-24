package main.models;

import external.credit.CreditScore;

import java.time.LocalDate;

/**
 * Account model class. Represents an account with personal information and credit details.
 * Improvement notes: Take out clientType, creditLimit and CreditScore; instead use an index to point to the respective
 * account type (account category).
 */
public class Account {
    private  String id;
    private  String lastName;
    private String firstName;
    private CreditScore creditScore;
    private LocalDate birthdate;
    private String clientType;
    private int creditLimit;
    private long fiscalNumber;

    public Account(String accountId, String firstName, String lastName, long fiscalNumber, LocalDate birthdate, CreditScore creditScore, String clientType, int creditLimit) {

        this.id = accountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.creditScore = creditScore;
        this.fiscalNumber = fiscalNumber;
        this.birthdate = birthdate;
        this.clientType = clientType;
        this.creditLimit = creditLimit;
    }
    public Account() {} // For DB mapping

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public CreditScore getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(CreditScore creditScore) {
        this.creditScore = creditScore;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(int creditLimit) {
        this.creditLimit = creditLimit;
    }

    public long getFiscalNumber() {
        return fiscalNumber;
    }

    public void setFiscalNumber(long fiscalNumber) {
        this.fiscalNumber = fiscalNumber;
    }
}
