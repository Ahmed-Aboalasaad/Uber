package Main;

import Main.User.Customer;

public class paypal implements paymentStrategy {
    String accountNumber ;
    String password;
    public double balance;

    public paypal(String accountNumber, String password) {
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = 0;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean checkCredentials(String accountNumber, String password ) {
        return (accountNumber.equals(this.accountNumber)) & (password.equals(this.password));
    }

    @Override
    public boolean checkBalance (double amount){
        return amount <= balance;
    }

    public void deductBalance(double amount){
    this.balance -= amount;
    }

    public void Refund(Customer customer, Double amount){
        balance += amount;
    }
}