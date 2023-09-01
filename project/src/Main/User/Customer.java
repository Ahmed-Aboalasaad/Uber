package Main.User;

import Main.*;

import java.util.Scanner;
public class Customer extends Person {

    public String Home;
    public String Work;
    public String paymentMethodtype;
    public paymentStrategy payer;

    private static Customer currentCustomer = null;

    public static Customer getInstance(){
        if(currentCustomer == null){
            currentCustomer = new Customer();
        }
        return currentCustomer;
    }


    public int ReservedBusRide = 0;


    public Customer(){
        super();
       // this.form();
    }


@Override
    public void form(){


    Scanner scanner = new Scanner(System.in);
    System.out.println("Hi Main.Main.Rides.User.Customer, Please enter the following data:");
    System.out.print("Name:");
    currentCustomer.Name = scanner.nextLine();
    System.out.print("Age:");
    currentCustomer.Age = Short.parseShort(scanner.nextLine());
    System.out.print("Mail:");
    currentCustomer.Uber_Mail = scanner.nextLine();
    System.out.print("Password:");
    currentCustomer.Uber_Password = scanner.nextLine();
    System.out.println("Home Location:");
    currentCustomer.Home = scanner.nextLine();
    System.out.println("Work Location:");
    currentCustomer.Work = scanner.nextLine();
    System.out.print("Payment Method: (Paypal - Card)");
    currentCustomer.paymentMethodtype = scanner.nextLine();


    CustomerSavedData.customerList.add(currentCustomer);

}

}
