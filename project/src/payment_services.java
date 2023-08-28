import  java.util.Scanner;

public class payment_services {

    private paymentStrategy paymentStrategy;
    public void setStrategy(Customer customer){
        Scanner getacc = new Scanner(System.in);
        Scanner getpass = new Scanner(System.in);

        if (customer.payment_method.equals("paypal")){
            String acc_num =getacc.nextLine();
            String pass= getpass.nextLine();

            this.paymentStrategy= new paypal(acc_num,pass);

        }
        else if (customer.payment_method.equals("Credit_card")){
            String credit_card_Num=getacc.nextLine();
            String CVV=getpass.nextLine();
            this.paymentStrategy= new credit_card(credit_card_Num,CVV);
        }
    }

    public boolean check_credentials(String x,String y){
      return this.paymentStrategy.check_credentials(x,y);
    }
    public boolean check_balance(double amount){
        return this.paymentStrategy.check_balance(amount);
    }

    public void deducte_balance(double amount){
         this.paymentStrategy.deducte_balance(amount);
    }
    public void refund(double amount){
        this.paymentStrategy.refund(amount);
    }




}
