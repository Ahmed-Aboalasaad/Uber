package Uber.Rides;

import Uber.User.Customer;
import Uber.vehicleinstance;

import java.util.ArrayList;
import java.util.Scanner;

public class RideRequest {
    private Customer currentCustomer;
    Ride ride;
    String source = null;
    String destination = null;
    float distance = 0;
    Scanner scanner = new Scanner(System.in);
    ArrayList<vehicleinstance.vehicle> vehiclelist = new ArrayList <>();

    /**
     * Initialize the ride request
     * @param currentCustomer reference to the current logged-in customer
     */
    RideRequest(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    /**
     * Let the user choose a ride and request it
     */
    public void request() {
        // determine source & destination
        System.out.println("1] Home -> Work\n2] Work -> Home\n3] Other");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                source = currentCustomer.Home;
                destination = currentCustomer.Work;
                break;
            case 2:
                destination = currentCustomer.Home;
                source = currentCustomer.Work;
                break;
            case 3:

                System.out.print("From:");
                source = scanner.nextLine();
                System.out.print("To:");
                destination = scanner.nextLine();
        }

        // determine ride type & call its suitable request
        System.out.print("Ride type: (Bus - Car - Scooter)");
        String rideType = scanner.nextLine();
        switch (rideType) {
            case "bus":
                requestBus();
                break;
            case "car":
                requestCar();
                break;
            case "scooter":
                requestScooter();
                break;
        }

        float totalPrice = ride.CalculatePrice(distance);
        System.out.println("TotalPrice($):" + totalPrice);
        System.out.println("Confirm? (y/n)");
        char choiceChar = scanner.next().charAt(0);
        if (choiceChar == 'y')
            PaymentValidation(totalPrice);
    }
    private void requestBus() {
        vehicleinstance.vehicle chosenBus;
        ArrayList< vehicleinstance.vehicle > availableBuses = new ArrayList < vehicleinstance.vehicle > ();
        for (vehicleinstance.vehicle currentvehicle: vehiclelist) {

            if (currentvehicle.vehiclecapacity >= 14) {
                availableBuses.add(currentvehicle);
            }
        }
        int numvh = 0;

        for (vehicleinstance.vehicle bus: availableBuses) {
            System.out.println(++numvh + "." + bus.toString());
        }
        System.out.println("Enter the number of your choice");
        int busId = scanner.nextInt();

        chosenBus = availableBuses.get(busId - 1);
        ride = new BusRide(distance, chosenBus.vehiclecapacity);
        ride.SetRoute(source, destination);
        ((BusRide) ride).assignedvhmodel = chosenBus.vehicleModel; // compiler made this I don't why
        ((BusRide) ride).assignedvhnumber = chosenBus.vehicleNumber;
    }
    private void requestCar() {
        vehicleinstance.vehicle chosencar;
        ArrayList < vehicleinstance.vehicle > avcars = new ArrayList < vehicleinstance.vehicle > ();
        for (vehicleinstance.vehicle currentvh: vehiclelist) {

            if (currentvh.vehiclecapacity >= 4 & currentvh.vehiclecapacity <= 6) {
                avcars.add(currentvh);
            }
        }
        int numcar = 0;

        for (vehicleinstance.vehicle eligible: avcars) {
            System.out.println(++numcar + "." + eligible.toString());
        }
        System.out.println("Enter the number of your choice");
        int carid = scanner.nextInt();

        chosencar = avcars.get(carid - 1);

        ride = new CarRide(distance);
        ride.SetRoute(source, destination);
    }
    private void requestScooter() {
        vehicleinstance.vehicle chosenscouter;
        ArrayList < vehicleinstance.vehicle > avscouters = new ArrayList < vehicleinstance.vehicle > ();
        for (vehicleinstance.vehicle currentvh: vehiclelist) {

            if (currentvh.vehicleType.equals("scouter")) {
                avscouters.add(currentvh);
            }
        }
        int numvh = 0;

        for (vehicleinstance.vehicle eligible: avscouters) {
            System.out.println(++numvh + "." + eligible.toString());
        }
        System.out.println("enter the number of your choice");
        int scouterid = scanner.nextInt();

        chosenscouter = avscouters.get(scouterid - 1);
        ride = new ScooterRide(distance);
        ride.SetRoute(source, destination);
    }
}
