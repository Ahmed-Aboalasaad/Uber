package Uber;
import Uber.User.Customer;

/**
 * The credit_card class implements the paymentStrategy interface for credit card payments.
 */
public class CreditCard implements paymentStrategy {

    private String creditCardNumber;
    private String CVV;
    private float balance = 500f;

    /**
     * Constructor to create a credit card payment strategy.
     *
     * @param creditCardNumber The credit card number.
     * @param CVV              The Card Verification Value (CVV).
     */
    public CreditCard(String creditCardNumber, String CVV) {
        this.creditCardNumber = creditCardNumber;
        this.CVV = CVV;
    }

    /**
     * Sets the balance of the credit card account.
     *
     * @param balance The new balance.
     */
    public void setBalance(float balance) {
        this.balance = balance;
    }

    /**
     * Retrieves the account number associated with the credit card.
     *
     * @return The credit card number.
     */
    public String getAccountNumber() {
        return creditCardNumber;
    }

    /**
     * Retrieves the CVV associated with the credit card.
     *
     * @return The CVV.
     */
    public String getAccountPassword() {
        return CVV;
    }

    /**
     * Checks the credentials (credit card number and CVV) for authentication.
     *
     * @param creditCardNumber The credit card number for authentication.
     * @param CVV              The Card Verification Value (CVV) for authentication.
     * @return `true` if the credentials are valid, otherwise `false`.
     */
    @Override
    public boolean checkCredentials(String creditCardNumber, String CVV) {
        return (creditCardNumber.equals(this.creditCardNumber)) && (CVV.equals(this.CVV));
    }

    /**
     * Checks if the credit card account has sufficient balance for a transaction.
     *
     * @param amount The amount to be checked.
     * @return `true` if the balance is sufficient, otherwise `false`.
     */
    @Override
    public boolean checkBalance(float amount) {
        return amount <= balance;
    }

    /**
     * Deducts the specified amount from the credit card balance.
     *
     * @param amount The amount to be deducted.
     */
    public void deductBalance(float amount) {
        this.balance -= amount;
    }

    /**
     * Initiates a refund for a specific customer and amount.
     *
     * @param customer The customer to receive the refund.
     * @param amount   The amount to be refunded.
     */
    public void Refund(Customer customer, float amount) {
        balance += amount;
    }
}
