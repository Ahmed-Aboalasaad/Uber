package Uber;
import Uber.Rides.*;
import Uber.User.*;
import Uber.Menus.*;

import java.util.Scanner;

import static Uber.CustomerDataSaver.customerList;
import static Uber.DriverDataSaver.drivers;
import static Uber.RidesLoader.busRides;

/**
 * The Console UI for the Uber System
 * All the Displayed functions and methods
 */
public class ConsoleUi {
    public static Scanner scanner = new Scanner(System.in);
    public Customer currentCustomer;
    public Driver currentDriver;

    /**
     * Entry point
     */
    public void mainMenu() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\t\t");
        System.out.println("===  Uber System  ===");
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
            currentCustomer.form();
            paymentController controller = new paymentController();
            //controller.setStrategy(currentCustomer);
        } else {
            currentDriver = Driver.singletonDriver();
            currentDriver.form();
        }
        loginPage();
    }

    /**
     * Log in (Already having an account)
     */
    public void loginPage() {
        System.out.println("\n\t\t=== Login ===");
        System.out.print("Email:");
        String mail = scanner.nextLine();
        System.out.print("Password:");
        String password = scanner.nextLine();

        // Authenticate
        for (Customer customer: customerList)
            if (customer.Uber_Mail.equals(mail) && customer.Uber_Password.equals(password)) {
                currentCustomer = customer;
                break;
            }
        for (Driver driver: drivers)
            if (driver.Uber_Mail.equals(mail) && driver.Uber_Password.equals(password)) {
                currentDriver = driver;
                break;
            }

        if (currentCustomer != null)
            customerHomePage();
        else if (currentDriver != null)
            driverHomePage();
        else {
            System.out.println("Invalid Credentials :\\");
            loginPage();
        }
    }

    /**
     * Display customer's home page with new notifications(if any)
     * Update ticket prices for reserved bus rides if applicable.
     */
    public void customerHomePage() {
        System.out.println("\t\t === Home Page ===");
        System.out.print("Notifications:");
        if (currentCustomer.ReservedBusRide != 0) {
            for (BusRide ride : busRides) {
                if (currentCustomer.ReservedBusRide == ride.Id) {
                    ride.CheckAvailability(currentCustomer);
                    break;
                }

            }
        }

        System.out.println("\n\n");
        System.out.println("1] Request a ride");
        System.out.println("2] FAQs");
        System.out.println("3] Logout");

        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                RideRequest RideService = new RideRequest(currentCustomer);
                break;
            case 2:
                FAQ.questions();
                break;
            case 3:
                logout();
                break;
            default:
                System.out.println("Invalid Input");
                customerHomePage();
        }
    }

    /**
     * Show Driver Home Page (history / FAQs / logout)
     */
    public void driverHomePage() {
        System.out.println("New notifications:");
        if (currentDriver.BusrideId != 0) {
            for (BusRide ride: busRides) {
                if (ride.Id == currentDriver.BusrideId) {
                    ride.CheckAvailability(currentDriver);
                    break;
                }
            }
        }

        System.out.println("\n1] FAQs");
        System.out.println("2] Logout");

        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                FAQ.questions();
                break;
            case 2:
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
        Global.currentTripDriver = null;
    }
}