package main.models;
import external.credit.CreditScore;

/**
 * AccountCategory model class. Represents an account category with credit score, client type and credit limit.
 * This would be the values that would be in the other table, but we didn't go deep in to this, so I did not add the
 * Repository for it and instead just implemented the object to give an idea.
 */
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
