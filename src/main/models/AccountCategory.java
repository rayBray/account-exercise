package main.models;
import external.credit.CreditScore;

public class AccountCategory {
    private CreditScore creditScore;
    private String clientType;
    private int creditLimit;

    public AccountCategory(CreditScore creditScore, String clientType, int creditLimit) {
        this.creditScore = creditScore;
        this.clientType = clientType;
        this.creditLimit = creditLimit;
    }
    public CreditScore getCreditScore() {
        return creditScore;
    }
    public String getClientType() {
        return clientType;
    }

    public int getCreditLimit() {
        return creditLimit;
    }
}
