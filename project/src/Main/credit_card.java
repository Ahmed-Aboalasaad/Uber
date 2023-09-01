package Main;

import Main.User.Customer;

public class credit_card implements paymentStrategy  {

public String creditCardNumber;
 public String CVV;
public double balance;


    public credit_card(String creditCardNumber, String CVV) {
        this.creditCardNumber = creditCardNumber;
        this.CVV = CVV;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
   public String getaccnum(){
        return creditCardNumber;
   }
   public String getpassword(){
        return CVV;
   }


    @Override
    public boolean checkCredentials(String creditCardNumber, String CVV) {
        return (creditCardNumber.equals(this.creditCardNumber) ) & (CVV.equals(this.CVV));
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
