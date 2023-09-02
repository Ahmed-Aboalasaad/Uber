package Uber;
import Uber.Rides.*;
import Uber.User.*;
import Uber.Menus.*;
import java.util.ArrayList;
import java.util.Scanner;

import static Uber.CustomerSavedData.customerList;
import static Uber.DriverSavedData.driverList;
import static Uber.ReservedRidesData.Busrideslist;

/**
 * The Console UI for the Uber System
 * All the Displayed functions and methods
 */
public class ConsoleUi {
    public static Scanner scanner = new Scanner(System.in);
    Customer currentCustomer;
    Driver currentDriver;
    vehicleinstance registeredVehicle = new vehicleinstance();

    /**
     * Entry point
     */
    public void mainMenu() {
        System.out.println("\t\t===  Uber System  ===");
        System.out.println("1] Login\n2] Register\n3] Exit");

        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                loginPage();
                break;
            case 2:
                register();
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice.");
                mainMenu();
        }
    }

    /**
     * Create a new account
     */
    public void register() {
        System.out.println("You are: 1] Customer\t\t2] Driver");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            currentCustomer = Customer.singletonCustomer();
            paymentController controller = new paymentController();
            controller.setStrategy(currentCustomer);
        } else
            currentDriver = Driver.singletonDriver();
        loginPage();
    }

    /**
     * Log in (Already having an account)
     */
    public void loginPage() {
        System.out.print("Email:");
        String mail = scanner.nextLine();
        System.out.print("Password:");
        String password = scanner.nextLine();

        // Authenticate
        for (Customer customer: customerList)
            if (customer.Uber_Mail.equals(mail) & customer.Uber_Password.equals(password)) {
                currentCustomer = customer;
                break;
            }
        for (Driver driver: driverList)
            if (driver.Uber_Mail.equals(mail) & driver.Uber_Password.equals(password)) {
                currentDriver = driver;
                break;
            }

        if (currentCustomer != null)
            customerHomePage();
        else if (currentDriver != null)
            driverHomePage();
        else
            System.out.println("Invalid Credentials :\\");
    }

    /**
     * Display customer's home page with new notifications(if any)
     * Update ticket prices for reserved bus rides if applicable.
     */
    public void customerHomePage() {
        System.out.println("New Notifications:");
        if (currentCustomer.ReservedBusRide != 0) {
            for (BusRide ride : Busrideslist) {
                if (currentCustomer.ReservedBusRide == ride.BusRideId) {
                    ride.CheckAvailability(currentCustomer);
                    break;
                }
            }
        }

        System.out.println("\n\n");
        System.out.println("1] Request a ride");
        System.out.println("2] Rides history");
        System.out.println("3] FAQs");
        System.out.println("4] Logout");

        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                requestARide();
                break;
            case 2:
                customerRidesHistory();
                break;
            case 3:
                FAQ.questions();
                break;
            case 4:
                logout();
                break;
            default:
                System.out.println("Invalid Input");
                customerHomePage();
        }
    }

    /**
     * Display the ride history for the current customer and optionally, clear it
     */
    public void customerRidesHistory() {
        System.out.println("You have " + currentCustomer.RidesCount + " Rides:\n");
        // todo: display customer rides history
        System.out.print("\n1]Clear\n2]Back to Home Page\n");

        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            currentCustomer.RidesCount = 0;
            // todo: clearHistory();
            System.out.println("Cleard History :)");
        } else
            customerHomePage();
    }

    /**
     * Show Driver Home Page (history / FAQs / logout)
     */
    public void driverHomePage() {
        System.out.println("New notifications:");
        if (currentDriver.BusrideId != 0) {
            for (BusRide ride: Busrideslist) {
                if (ride.BusRideId == currentDriver.BusrideId) {
                    ride.CheckAvailability(currentDriver);
                    break;
                }
            }
        }

        System.out.println("\n\n\n");
        System.out.println("1] Rides history");
        System.out.println("2] FAQs");
        System.out.println("3] Logout");

        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                System.out.println("To Do: Ride History");
                break;
            case 2:
                FAQ.questions();
                break;
            case 3:
                logout();
                break;
            default:
                System.out.println("Invalid Input");
                driverHomePage();
        }
    }

    /**
     * Log out the current user and return to the start page
     */
    public void logout() {
        System.out.println("Do you really want to log out? (y/n)");
        char choice = scanner.next().charAt(0);
        if (choice == 'y') {
            currentCustomer = null;
            currentDriver = null;
            mainMenu();
        } else
            customerHomePage();
    }

    // =================================================
    /**
     * Populates the list of available vehicles by iterating through the list of drivers and their vehicles.
     * For each driver, a vehicle is registered with its model, capacity, number, and type,
     * and added to the list of available vehicles.
     */
    public void availablevhlist() {

        for (Driver availableDriver: driverList) {
            registeredVehicle.setmodel(availableDriver.vehicleModel);
            registeredVehicle.setVehiclecapacity(availableDriver.vehiclecapacity);
            registeredVehicle.setVehicleNumber(availableDriver.vehicleNumber);
            registeredVehicle.setVehicleType(availableDriver.vehicleType);

            vehiclelist.add(registeredVehicle.build());
            registeredVehicle.reset();
        }
    }

    /**
     * Displays the page for rating a driver and providing feedback.
     */
    public void rateDiverPage() {
        System.out.print("Enter Number of Start (1 - 5)\n");
        currentDriver.totalrates += scanner.nextInt();
        currentDriver.numofrates++;
        scanner.nextLine();
        System.out.print("Enter FeedBack Message\n");
        String message = scanner.next();
        System.out.print("Thank you for the feedback, Wishes to you a good day\n");
    }

    /**
     * Validates payment details and processes the payment for a ride.
     *
     * @param totalPrice The total price of the ride.
     */
    public void PaymentValidation(float totalPrice) {
        paymentController paymentMethod = new paymentController();
        paymentMethod.setStrategy(currentCustomer);
        System.out.print("Enter Account Number:");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter Account Password:");
        String accountPassword = scanner.nextLine();
        // Waiting for Price ###ABO### :(

        if (paymentMethod.checkCredentials(accountNumber, accountPassword))
            if (paymentMethod.checkBalance(totalPrice)) {
                paymentMethod.deductBalance(totalPrice);
                System.out.println("Transaction Complete");
                currentCustomer.RidesCount++;

                System.out.println("Main.Main.Rides.Ride Done, Thank you to use Uber.\n[0: Home Page]\t[2: Rate Main.Main.Rides.User.Driver]");

                int choice2 = scanner.nextInt();
                scanner.nextLine();
                if (choice2 == 0)
                    customerHomePage();
                else
                    rateDiverPage();

            } else
                System.out.println("You have not enough money");
        else
            System.out.println("Incorrect Data");

    }
}