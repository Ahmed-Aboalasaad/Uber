import java.util.Scanner;
public class Customer extends Person {

    String currentLocation;
    String paymentMethod;

    public Ride RequestARide (float distance) {
        System.out.println(" press 1 to choose  Scooter Ride\n press 2  to choose Bus Ride \n press 3 to choose Car Ride");
        short choice;
        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextShort();

        Ride RuquestedRide = null;
        switch (choice) {
            case 1: {
                RuquestedRide = new ScooterRide(distance);
                break;
            }
            case 2: {
                RuquestedRide = new BusRide(distance);
                break;
            }
            case 3: {
                RuquestedRide= new CarRide(distance);
                break;
            }
        }
        return RuquestedRide;
    }

    public Customer(String name, short age,String Mail, String password) {
        super(name, age,Mail, password);
    }
    public void SetPaymentMethod(String paymentMethod){
        this.paymentMethod = paymentMethod;
    }
}
