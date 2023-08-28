import java.util.Scanner;

public class paymentController {

    private paymentStrategy paymentStrategy;

    public void setStrategy(Customer customer){
        Scanner scanner = new Scanner(System.in);

        if (customer.paymentMethodtype.equals("paypal")){
            String paypalNumber = scanner.nextLine();
            String paypalPassword = scanner.nextLine();

            this.paymentStrategy= new paypal(paypalNumber, paypalPassword);

        }
        else if (customer.paymentMethodtype.equals("Credit_card")){
            String creditCardNumber = scanner.nextLine();
            String CVV = scanner.nextLine();
            this.paymentStrategy= new credit_card(creditCardNumber,CVV);
        }




        customer.payer = paymentStrategy;
    }


    public boolean checkCredentials(String username,String password){
      return this.paymentStrategy.checkCredentials(username, password);
    }
    public boolean checkBalance(double amount){
        return this.paymentStrategy.checkBalance(amount);
    }

    public void deductBalance(double amount){
         this.paymentStrategy.deductBalance(amount);
    }


}
