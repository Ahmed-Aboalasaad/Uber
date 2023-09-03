package Uber;

import Uber.User.Customer;
/**
 * The paymentStrategy interface defines methods that encapsulate,
 * payment-related functionality and strategies.
 * Implementing classes can provide various payment methods and operations.
 */
public interface paymentStrategy {
    public String getAccountNumber();
    public String getAccountPassword();
    public boolean checkCredentials(String username,String password);
    public boolean checkBalance(float amount);
    public void deductBalance(float amount);
    public void Refund(Customer customer, float amount);

}
