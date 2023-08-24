public class credit_card implements paymentStrategy {

double balance;
String creditCardNumber;
String CVV;

    public credit_card(String creditCardNumber, String CVV) {
        this.creditCardNumber = creditCardNumber;
        this.CVV = CVV;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean check_credentials(String c_Num ,String cvv ) {
        return (c_Num.equals(this.creditCardNumber) ) & (cvv.equals(this.CVV));
    }
    @Override
    public boolean check_balance (double amount){
        return amount<=balance;
    }

    public void deducte_balance(double amount){
        this.balance-=amount;
    }

    @Override
    public void refund(double amount) {
        this.balance+=amount;

    }
}
