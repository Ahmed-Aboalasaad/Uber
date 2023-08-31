package Main;

import Main.User.Customer;

public interface paymentStrategy {

    public boolean checkCredentials(String username,String password);
    public boolean checkBalance(double amount);
    public void deductBalance(double amount);
    public void Refund(Customer customer, Double amount);
}