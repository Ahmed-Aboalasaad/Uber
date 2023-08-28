import Rides.BusRide;
import Rides.CarRide;
import Rides.Ride;
import Rides.ScooterRide;

import java.util.Scanner;
public class Customer extends Person {

    String currentLocation;
    String paymentMethodtype;
    paymentStrategy payer;


    public Customer(){
        super();
        this.form();
    }


@Override
    public void form(){
        Scanner scanner = new Scanner(System.in);
    System.out.println("Hi Customer, Please enter the following data:");
    System.out.print("Name:");
   this.Name = scanner.nextLine();
    System.out.print("\nAge:");
    this.Age = scanner.nextShort();
    System.out.print("\nMail:");
    this.Uber_Mail = scanner.nextLine();
    System.out.print("\nPassword:");
    this.Uber_Password = scanner.nextLine();
    System.out.print("\nPayment Method: (Paypal - Card)");
    this.paymentMethodtype = scanner.nextLine();
}

}
