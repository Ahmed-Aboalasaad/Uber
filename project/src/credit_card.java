public class credit_card implements paymentStrategy {

String creditCardNumber;
String CVV;
double balance;


    public credit_card(String creditCardNumber, String CVV) {
        this.creditCardNumber = creditCardNumber;
        this.CVV = CVV;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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


}
