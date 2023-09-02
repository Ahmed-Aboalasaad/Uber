package Uber;
import Uber.User.Customer;
import java.util.Scanner;


/**
 * The paymentController class handles payment strategies and interactions with payment-related operations.
 */
public class paymentController {

    private paymentStrategy paymentStrategy;

    /**
     * Sets the payment strategy based on the customer's selected payment method.
     *
     * @param customer The customer for whom the strategy is being set.
     */
    public void setStrategy(Customer customer) {
        Scanner scanner = new Scanner(System.in);

        if (customer.paymentMethodtype.equals("paypal")) {
            String paypalNumber = scanner.nextLine();
            String paypalPassword = scanner.nextLine();

            this.paymentStrategy = new paypal(paypalNumber, paypalPassword);
        } else if (customer.paymentMethodtype.equals("Credit_card")) {
            String creditCardNumber = scanner.nextLine();
            String CVV = scanner.nextLine();
            this.paymentStrategy = new credit_card(creditCardNumber, CVV);
        }

        customer.payer = paymentStrategy;
    }

    /**
     * Gets the account number associated with the current payment strategy.
     *
     * @return The account number.
     */
    public String getaccnum() {
        return this.paymentStrategy.getAccountNumber();
    }

    /**
     * Gets the account password associated with the current payment strategy.
     *
     * @return The account password.
     */
    public String getpassword() {
        return this.paymentStrategy.getAccountPassword();
    }

    /**
     * Checks the credentials for authentication against the current payment strategy.
     *
     * @param username The username for authentication.
     * @param password The password for authentication.
     * @return `true` if the credentials are valid, otherwise `false`.
     */
    public boolean checkCredentials(String username, String password) {
        return this.paymentStrategy.checkCredentials(username, password);
    }

    /**
     * Checks if the account has sufficient balance for a transaction using the current payment strategy.
     *
     * @param amount The amount to be checked.
     * @return `true` if the balance is sufficient, otherwise `false`.
     */
    public boolean checkBalance(double amount) {
        return this.paymentStrategy.checkBalance(amount);
    }

    /**
     * Deducts the specified amount from the account balance using the current payment strategy.
     *
     * @param amount The amount to be deducted.
     */
    public void deductBalance(double amount) {
        this.paymentStrategy.deductBalance(amount);
    }

    /**
     * Initiates a refund for a specific customer and amount using the current payment strategy.
     *
     * @param customer The customer to receive the refund.
     * @param amount   The amount to be refunded.
     */
    public void Refund(Customer customer, Double amount) {
        this.paymentStrategy.Refund(customer, amount);
    }
}
