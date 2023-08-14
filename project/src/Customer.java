import java.util.Scanner;
public class Customer extends person {

    String current_location;
    String payment_method;
    public Ride Request_ride (float destance) {
        short choice;

        System.out.println(" press 1 to choose  Scooter Ride\n press 2  to choose Bus Ride \n press 3 to choose Car Ride");
        Scanner getchoice = new Scanner(System.in);
        choice = getchoice.nextShort();
        Ride c =null;
        switch (choice) {
            case 1: {
                c = new ScooterRide(destance);
                break;

            }
            case 2: {
                c = new BusRide(destance);
                break;


            }

            case 3: {
                c= new CarRide(destance);
                break;

            }

        }
        return c;


    }

    public Customer(short id, String name, short age, String password) {
        super(id, name, age, password);
    }
}
