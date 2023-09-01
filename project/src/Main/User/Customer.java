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

    public Boolean ReservABus = false;
    public int ReservedBusRide = 0;


    public Customer(){
        super();
       // this.form();
    }


@Override
    public void form(){
    Customer nCustomer = new Customer();

    Scanner scanner = new Scanner(System.in);
    System.out.println("Hi Main.Main.Rides.User.Customer, Please enter the following data:");
    System.out.print("Name:");
    nCustomer.Name = scanner.nextLine();
    System.out.print("Age:");
    nCustomer.Age = Short.parseShort(scanner.nextLine());
    System.out.print("Mail:");
    nCustomer.Uber_Mail = scanner.nextLine();
    System.out.print("Password:");
    nCustomer.Uber_Password = scanner.nextLine();
    System.out.println("Home Location:");
    nCustomer.Home = scanner.nextLine();
    System.out.println("Work Location:");
    nCustomer.Work = scanner.nextLine();
    System.out.print("Payment Method: (Paypal - Card)");
    nCustomer.paymentMethodtype = scanner.nextLine();


    CustomerSavedData.customerList.add(nCustomer);
    currentCustomer =CustomerSavedData.customerList.getLast();
}

}
