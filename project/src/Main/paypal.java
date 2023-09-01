package Main;

import Main.User.Customer;

public class paypal implements paymentStrategy {
    public String accountNumber ;
     public String password;
    public double balance;

    public paypal(String accountNumber, String password) {
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = 0;
    }

    /**
     * Getter to get the account number of PayPal account
     * @return paypal Account Number
     */
    public String getAccountNumber(){
        return this.accountNumber;
    }
    /**
     * Getter to get the account password of PayPal account
     * @return paypal Account password
     */
    public String getAccountPassword(){
        return this.password;
    }

    /**
     * Set the balance of paypal account
     * @param balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Check if the Credentials of the account is correct
     * @param accountNumber
     * @param password
     * @return bool value
     */
    @Override
    public boolean checkCredentials(String accountNumber, String password ) {
        return (accountNumber.equals(this.accountNumber)) & (password.equals(this.password));
    }

    /**
     * Check if the account has enough money or not
     * @param amount
     * @return boolean value
     */
    @Override
    public boolean checkBalance (double amount){
        return amount <= balance;
    }

    /**
     * decrease the balance of account by amount value
     * @param amount
     */
    public void deductBalance(double amount){
    this.balance -= amount;
    }

    /**
     * increase the account balance by amount value
     * @param customer
     * @param amount
     */
    public void Refund(Customer customer, Double amount){
        balance += amount;
    }
}
