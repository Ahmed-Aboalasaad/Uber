package Uber.Rides;

import Uber.Global;
import Uber.Graph.PathFinder;
import Uber.Graph.Road;
import Uber.User.Customer;
import Uber.vehicleBuilder;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class RideRequest {
    private Customer currentCustomer;
    Ride ride;
    String source = null;
    String destination = null;
    float distance = 0;
    Scanner scanner = new Scanner(System.in);
    ArrayList<vehicleBuilder.vehicle> vehiclelist = new ArrayList <>();

    /**
     * Initialize the ride request
     * @param currentCustomer reference to the current logged-in customer
     */
    RideRequest(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
        inputRideInfo();
        confirmPrice();
    }

    /**
     * let the user determine the source, destination, and type of their trip
     */
    private void inputRideInfo() {
        // input source & destination
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
                System.out.println("Available Pickups: ");
                Set<String> Pickups = Global.map.getCitiesNames();
                for (String pickup : Pickups)
                    System.out.print(pickup + ". ");
                System.out.print("You'll go From: ");
                source = scanner.nextLine();
                System.out.print(" -> ");
                destination = scanner.nextLine();
        }

        // determine ride type & call its suitable requester
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
    }

    /**
     *
     */
    private void requestBus() {
        vehicleBuilder.vehicle chosenBus;
        ArrayList< vehicleBuilder.vehicle > availableBuses = new ArrayList < vehicleBuilder.vehicle > ();
        for (vehicleBuilder.vehicle currentvehicle: vehiclelist) {

            if (currentvehicle.vehiclecapacity >= 14) {
                availableBuses.add(currentvehicle);
            }
        }
        int numvh = 0;

        for (vehicleBuilder.vehicle bus: availableBuses) {
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
        vehicleBuilder.vehicle chosencar;
        ArrayList < vehicleBuilder.vehicle > avcars = new ArrayList < vehicleBuilder.vehicle > ();
        for (vehicleBuilder.vehicle currentvh: vehiclelist) {

            if (currentvh.vehiclecapacity >= 4 & currentvh.vehiclecapacity <= 6) {
                avcars.add(currentvh);
            }
        }
        int numcar = 0;

        for (vehicleBuilder.vehicle eligible: avcars) {
            System.out.println(++numcar + "." + eligible.toString());
        }
        System.out.println("Enter the number of your choice");
        int carid = scanner.nextInt();

        chosencar = avcars.get(carid - 1);

        ride = new CarRide(distance);
        ride.SetRoute(source, destination);
    }

    private void requestScooter() {
        vehicleBuilder.vehicle chosenscouter;
        ArrayList < vehicleBuilder.vehicle > avscouters = new ArrayList < vehicleBuilder.vehicle > ();
        for (vehicleBuilder.vehicle currentvh: vehiclelist) {

            if (currentvh.vehicleType.equals("scouter")) {
                avscouters.add(currentvh);
            }
        }
        int numvh = 0;

        for (vehicleBuilder.vehicle eligible: avscouters) {
            System.out.println(++numvh + "." + eligible.toString());
        }
        System.out.println("enter the number of your choice");
        int scouterid = scanner.nextInt();

        chosenscouter = avscouters.get(scouterid - 1);
        ride = new ScooterRide(distance);
        ride.SetRoute(source, destination);
    }

    /**
     * Calculate the distance, price, and ask the user to confirm reservation
     * with the calculated price
     */
    public void confirmPrice() {
        // find the trip distance
        Stack<Road> path = new Stack<>();
        PathFinder pathFinder = new PathFinder(Global.map, path);
        distance = pathFinder.find(source, destination);

        // calculate price
        float totalPrice = ride.CalculatePrice(distance);
        System.out.println("TotalPrice($):" + totalPrice);
        System.out.println("Confirm? (y/n)");
        char choiceChar = scanner.next().charAt(0);
        if (choiceChar == 'y')
            PaymentValidation(totalPrice);
    }
}
