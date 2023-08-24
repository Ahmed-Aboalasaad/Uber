public class paypal  implements paymentStrategy {
    String account_number ;
    String password;
     double balance ;
    public paypal(String account_number, String password) {
        this.account_number = account_number;
        this.password = password;
        this.balance=0;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean check_credentials(String acc_num ,String pass ) {
        return (acc_num.equals(this.account_number) ) & (pass.equals(this.password));
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
