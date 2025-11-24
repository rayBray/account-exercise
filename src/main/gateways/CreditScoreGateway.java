package main.gateways;

import external.credit.CreditScore;
import external.credit.CreditScoreService;

/**
 * Gateway class for Credit Scores. Handles all communication with the Credit Score Service or throws errors if
 * communication fails.
 * Improvement notes: create a real gateway with better error handling/monitoring. If heavy traffic: see if webhooks exist
 * instead of http calls.
 */
public class CreditScoreGateway {

    /**
     * Retrieves the credit score for a given fiscal number.
     * @param fiscalNumber: fiscal number of an account user
     * @return CreditScore: returns the credit score for the given fiscal number
     * @throws Exception: throws an exception if the credit score cannot be retrieved
     */
    public CreditScore getCreditScore(long fiscalNumber) throws Exception {
        CreditScore creditScore;
        try {
            CreditScoreService creditScoreService = new CreditScoreService();
            creditScore = creditScoreService.getCreditScore(fiscalNumber);
        } catch (Exception e) { // the errors would be http common ones, but we aren't calling a real service,
            // and I did not want to touch the CreditScoreService seeing as I was not supposed to
            throw new Exception("Failed to retrieve credit score", e);
        }
        return creditScore;
    }
}
