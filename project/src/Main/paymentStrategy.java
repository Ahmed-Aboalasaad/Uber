package Main;

import Main.User.Customer;
/**
 * The paymentStrategy interface defines methods that encapsulate,
 * payment-related functionality and strategies.
 * Implementing classes can provide various payment methods and operations.
 */
public interface paymentStrategy {
    public String getAccountNumber();
    public String getAccountPassword();
    public boolean checkCredentials(String username,String password);
    public boolean checkBalance(double amount);
    public void deductBalance(double amount);
    public void Refund(Customer customer, Double amount);
}
