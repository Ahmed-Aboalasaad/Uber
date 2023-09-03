package Uber.User;

import Uber.*;

import java.util.Scanner;
import java.util.Set;

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
    public int ReservedBusRide = 0;
    private static Customer currentCustomer = null;

    /**
     * Creates a 'singleton' instance of the Customer class(if it doesn't exist)
     * It there's already one, it returns it
     * @return The current customer instance.
     */
    public static Customer singletonCustomer(){
        if(currentCustomer == null)
            currentCustomer = new Customer();
        return currentCustomer;
    }

    public Customer(){
        super();
    }

    /**
 * Collects user input to fill in the customer's information and saves it.
 * Prompts the user to enter the customer's name, age, email, password, home,
 * work, and payment method.
 */
@Override
    public void form() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("\t== Input Your Data ==");
    System.out.print("Name:");
    currentCustomer.Name = scanner.nextLine();
    System.out.print("Age:");
    currentCustomer.Age = Short.parseShort(scanner.nextLine());
    System.out.print("Mail:");
    currentCustomer.Uber_Mail = scanner.nextLine();
    System.out.print("Password:");
    currentCustomer.Uber_Password = scanner.nextLine();
    System.out.println("Available Pickups:");
    Set<String> places = Global.graph.getCitiesNames();
    for (String place : places)
        System.out.print(place + ". ");
    while (true) {
        System.out.print("\nHome Location:");
        currentCustomer.Home = scanner.nextLine();
        if (Global.graph.cityExists(currentCustomer.Home))
            break;
        System.out.println("Invalid Home Location. Try Agian!");
    }
    while (true) {
        System.out.print("Work Location:");
        currentCustomer.Work = scanner.nextLine();
        if (Global.graph.cityExists(currentCustomer.Work))
            break;
        System.out.println("Invalid Work Location. Try Agian!");
    }
    System.out.print("Payment Method: (Paypal - Card)");
    currentCustomer.paymentMethodtype = scanner.nextLine();
paymentController pc = new paymentController();
pc.setStrategy(currentCustomer);
    CustomerDataSaver.customerList.add(currentCustomer);
    System.out.println("Thanks! now, login again");
}
}
