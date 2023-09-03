package Uber.Rides;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import Uber.*;
import Uber.Graph.PathFinder;
import Uber.Graph.Road;
import Uber.User.Customer;
import Uber.User.Driver;

import static Uber.DriverDataSaver.drivers;

public class RideRequest {
    private Customer currentCustomer;
    Ride ride;
    String source = null;
    String destination = null;
    float distance = 0;
    Scanner scanner = new Scanner(System.in);
    public ArrayList<VehicleBuilder.vehicle> vehicles = new ArrayList <>();
    VehicleBuilder vehicle = new VehicleBuilder();

    /**
     * Initialize the ride request
     * @param currentCustomer reference to the current logged-in customer
     */
    public RideRequest(Customer currentCustomer) {
        vehicles.clear();
        fillVehicles();
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
                Set<String> Pickups = Global.graph.getCitiesNames();
                for (String pickup : Pickups)
                    System.out.print(pickup + ". ");
                System.out.print("You'll go From: ");
                source = scanner.nextLine();
                System.out.print(" -> ");
                destination = scanner.nextLine();
        }

        // determine ride type & call its suitable requester
        System.out.print("Ride type:\n1] Bus\n2] Car\n3] Scooter\n");
        int rideType = scanner.nextInt();
        switch (rideType) {
            case 1:
                requestBus();
                break;
            case 2:
                requestCar();
                break;
            case 3:
                requestScooter();
                break;
        }
    }

    /**
     * Request a
     */
    private void requestBus() {
        VehicleBuilder.vehicle chosenBus;
        ArrayList<BusRide> busRides = new ArrayList<BusRide>();
        ArrayList< VehicleBuilder.vehicle > availableBuses = new ArrayList < VehicleBuilder.vehicle > ();
        for (VehicleBuilder.vehicle currentvehicle: vehicles)
            if (currentvehicle.vehicleCapacity >= 14)
                availableBuses.add(currentvehicle);

        for(BusRide searchRide: RidesLoader.busRides)
            if((searchRide.reservationsCount < searchRide.capacity) && searchRide.From.equals(source)   & searchRide.To.equals(destination) )
                busRides.add(searchRide);

        for(Driver assigndriver: drivers)
            if (assigndriver.vehicleNumber.equals(((BusRide) ride).vehicleNumber)) {
                assigndriver.BusrideId = ((BusRide) ride).Id;
                break;
            }

        if (!RidesLoader.busRides.isEmpty()) {
            System.out.println("these are available rides with the same road:");
            for(BusRide chooseride:busRides)
                System.out.println(chooseride.toString());

            System.out.println("enter the id of your chosen ride:");
            int chosenrideid = scanner.nextInt();
            for(BusRide chosen:busRides){
                if(chosen.Id == chosenrideid){
                    chosen.Reserve(currentCustomer);
                    ride = chosen;
                    return;
                }
            }
        }

        int vehicleNumber = 0;
        for (VehicleBuilder.vehicle bus: availableBuses)
            System.out.println(++vehicleNumber + "." + bus.toString());
        System.out.println("Enter the number of your choice");
        int busId = scanner.nextInt();

        chosenBus = availableBuses.get(busId - 1);
        ride = new BusRide(distance, chosenBus.vehicleCapacity,currentCustomer);
        ride.SetRoute(source, destination);
        ((BusRide) ride).vehicleModel = chosenBus.vehicleModel; // compiler made this I don't why
        ((BusRide) ride).vehicleNumber = chosenBus.vehicleNumber;

        for (Driver driver : drivers)
            if (chosenBus.vehicleNumber.equals(driver.vehicleNumber))
                Global.currentTripDriver = driver;
    }

    private void requestCar() {
        VehicleBuilder.vehicle chosenCar;
        ArrayList < VehicleBuilder.vehicle > Cars = new ArrayList<>();
        for (VehicleBuilder.vehicle currentvh: vehicles)
            if (currentvh.vehicleCapacity >= 4 && currentvh.vehicleCapacity <= 6)
                Cars.add(currentvh);
        int numcar = 0;

        for (VehicleBuilder.vehicle car: Cars)
            System.out.println(++numcar + "." + car.toString());
        System.out.print("Choose a car: ");

        int carID = scanner.nextInt();
        chosenCar = Cars.get(carID - 1);

        ride = new CarRide(distance);
        ride.SetRoute(source, destination);

        for (Driver driver : drivers)
            if (chosenCar.vehicleNumber.equals(driver.vehicleNumber))
                Global.currentTripDriver = driver;
    }

    private void requestScooter() {
        VehicleBuilder.vehicle chosenScouter;
        ArrayList < VehicleBuilder.vehicle > avscouters = new ArrayList < VehicleBuilder.vehicle > ();
        for (VehicleBuilder.vehicle currentvh: vehicles) {

            if (currentvh.vehicleType.equals("scouter")) {
                avscouters.add(currentvh);
            }
        }
        int numvh = 0;

        for (VehicleBuilder.vehicle eligible: avscouters) {
            System.out.println(++numvh + "." + eligible.toString());
        }
        System.out.println("enter the number of your choice");
        int scouterid = scanner.nextInt();

        chosenScouter = avscouters.get(scouterid - 1);
        ride = new ScooterRide(distance);
        ride.SetRoute(source, destination);
        for (Driver driver : drivers)
            if (chosenScouter.vehicleNumber.equals(driver.vehicleNumber))
                Global.currentTripDriver = driver;
    }

    /**
     * Calculate the distance, price, and ask the user to confirm reservation
     * with the calculated price
     */
    public void confirmPrice() {
        // find the trip distance
        Stack<Road> path = new Stack<>();
        PathFinder pathFinder = new PathFinder(Global.graph, path);
        distance = pathFinder.find(source, destination);

        // calculate price
        float totalPrice = ride.CalculatePrice(distance);
        System.out.println("TotalPrice($):" + totalPrice);
        System.out.println("Confirm? (y/n)");
        char choiceChar = scanner.next().charAt(0);
        if (choiceChar == 'y')
            PaymentValidation(totalPrice , currentCustomer);
    }

    // ====================================
    /**
     * Populates the list of available vehicles by iterating through the list of drivers and their vehicles.
     * For each driver, a vehicle is registered with its model, capacity, number, and type,
     * and added to the list of available vehicles.
     */
    public void fillVehicles() {
        for (Driver availableDriver: drivers) {
            vehicle.setmodel(availableDriver.vehicleModel);
            vehicle.setVehiclecapacity(availableDriver.vehiclecapacity);
            vehicle.setVehicleNumber(availableDriver.vehicleNumber);
            vehicle.setVehicleType(availableDriver.vehicleType);

            vehicles.add(vehicle.build());
            vehicle.reset();
        }
    }

    /**
     * Displays the page for rating a driver and providing feedback.
     */
    public void rateDiverPage() {
        System.out.print("Enter Number of Start (1 - 5)\n");
        Global.currentTripDriver.ratesSum += scanner.nextInt();
        Global.currentTripDriver.ratesNumber++;
        scanner.nextLine();
        System.out.print("Enter FeedBack Message\n");
        String message = scanner.next();
        System.out.print("Thank you for the feedback, Wishe to you a good day\n");
    }

    /**
     * Validates payment details and processes the payment for a ride.
     *
     * @param totalPrice The total price of the ride.
     */
    public void PaymentValidation(float totalPrice, Customer customer) {
        String accountNumber = currentCustomer.payer.getAccountNumber();
        String accountPassword = currentCustomer.payer.getAccountPassword();
        if (customer.payer.checkCredentials(accountNumber, accountPassword))
            if (customer.payer.checkBalance(totalPrice)) {
                customer.payer.deductBalance(totalPrice);
                System.out.println("Transaction Complete");
                currentCustomer.RidesCount++;

                System.out.println("Done, Thank you to use Uber.");
                System.out.println("1] Home Page");
                System.out.println("2] Rate Driver");

                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        Global.consoleUI.customerHomePage();
                        break;
                    case 2:
                        rateDiverPage();
                        break;
                }
            } else {
                System.out.println("You have not enough money");
            }
        else {
            System.out.println("Incorrect Data");
        }
    }
}
