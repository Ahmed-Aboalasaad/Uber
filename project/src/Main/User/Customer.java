package Main.User;

import Main.*;

import java.util.Scanner;

/**
 * The Customer class represents a user who can make reservations for bus rides.
 * This class extends the Person class and adds additional properties and methods
 * specific to a customer's behavior.
 */
public class Customer extends Person {

    public String Home;
    public String Work;
    public String paymentMethodtype;
    public paymentStrategy payer;

    private static Customer currentCustomer = null;

    /**
     * Returns the singleton instance of the Customer class.
     * If the instance doesn't exist, creates a new instance and returns it.
     *
     * @return The current customer instance.
     */
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

/**
     * Collects user input to fill in the customer's information and saves it.
     * Prompts the user to enter the customer's name, age, email, password, home,
     * work, and payment method.
     */
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
