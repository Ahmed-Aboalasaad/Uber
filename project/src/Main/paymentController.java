package Main;

import Main.User.Customer;

import java.util.Scanner;

public class paymentController {

    private paymentStrategy paymentStrategy;

    public void setStrategy(Customer customer){
        Scanner scanner = new Scanner(System.in);

        if (customer.paymentMethodtype.equals("Main.paypal")){
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

   public String getaccnum(){
        return this.paymentStrategy.getaccnum();
   }

   public String getpassword(){
        return this.paymentStrategy.getpassword();
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

    public void Refund(Customer customer, Double amount){
        this.paymentStrategy.Refund(customer, amount);
    }
}
