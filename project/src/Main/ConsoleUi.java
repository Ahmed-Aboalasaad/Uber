package Main;

import Main.Rides.*;
import Main.User.Customer;
import Main.User.Driver;

import java.util.ArrayList;
import java.util.Scanner;

import static Main.CustomerSavedData.customerList;
import static Main.DriverSavedData.driverList;
import static Main.ReservedRidesData.Busrideslist;

/**
 * The Console UI for the Uber System
 * All the Displayed functions and methods
 */
public class ConsoleUi {
    public static Scanner scanner = new Scanner(System.in);
    Customer currentCustomer;
    Driver currentDriver;

    ArrayList<vehicleinstance.vehicle> vehiclelist= new ArrayList<vehicleinstance.vehicle>();
    vehicleinstance registeredVehicle = new vehicleinstance();

  /**
     * Populates the list of available vehicles by iterating through the list of drivers and their vehicles.
     * For each driver, a vehicle is registered with its model, capacity, number, and type,
     * and added to the list of available vehicles.
     */
  public void availablevhlist(){

      for(Driver availableDriver: driverList){
      registeredVehicle.setmodel(availableDriver.vehicleModel);
      registeredVehicle.setVehiclecapacity(availableDriver.vehiclecapacity);
      registeredVehicle.setVehicleNumber(availableDriver.vehicleNumber);
      registeredVehicle.setVehicleType(availableDriver.vehicleType);

      vehiclelist.add(registeredVehicle.build());
      registeredVehicle.reset();
      }
  }


    /**
     * Main Screen of Uber System
     */
    public void startPage() {
        System.out.println("Welcome To Uber System");
        System.out.println("1] Login");
        System.out.println("2] New account");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (choice == 1) {
            loginPage();
        } else if (choice == 2) {
            newAccount();
        } else {
            System.out.println("Invalid choice.");
            startPage();
        }
    }

    /**
     * Home Page for driver with options for ride history, customer support, and logout.
     *
     */
    public void driverHomePage() {
        System.out.println("New notifications:");
        if(currentDriver.BusrideId!=0){
            for(BusRide avbusrides:Busrideslist){
                if(avbusrides.BusRideId == currentDriver.BusrideId)
                {
                    avbusrides.CheckAvailability(currentDriver);
                    break;
                }
            }
        }

        System.out.println("\n\n\n");
        System.out.println("1] Rides history");
        System.out.println("2] Customer Support");
        System.out.println("3] Logout");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (choice == 1) {
            System.out.println("This is Ride History");
        } else if (choice==2) {
            System.out.println("This is Customer Support");
        }
        else if (choice==3) {
            logout();
        }
        else if (choice ==0) {

        }
        else {
            System.out.println("Invalid Input");
            driverHomePage();
        }
    }

    /**
     * Collects customer data for account creation.
     */
    public void CustomerDataEntery() {

        currentCustomer = Customer.getInstance();  // NEW
        paymentController paymentcontrol= new paymentController();
        paymentcontrol.setStrategy(currentCustomer);
    }

    /**
     * Collects driver data for account creation.
     */
    public void DriverDataEntery() {

            currentDriver = Driver.getInstance();

        }

    /**
     * Handles the process of creating a new account.
     */
    public void newAccount() {
        System.out.println("Welcome To Uber, Are you Main.Main.Rides.User.Customer or Main.Main.Rides.User.Driver ?");
        System.out.println("1] Main.Main.Rides.User.Customer\t2] Main.Main.Rides.User.Driver");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if(choice == 1){ CustomerDataEntery();}
        else DriverDataEntery();

        System.out.println("Now you have a new account, press 1 to login!");
        choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            loginPage();
        }else if (choice ==0) {
            startPage();
        } else {
            System.out.println("Invalid choice.");
            newAccount();
        }
    }

    /**
     * Represents the process of requesting a ride.
     */
    public  void requestARide() {
        Ride RequestedRide;

        System.out.println("1] From Home to Work\n2] From Work to Home\n3] Others");
        int choice = scanner.nextInt();
        String from =null;
        String to = null;

        if(choice == 1)
        {
            from = currentCustomer.Home;
            to = currentCustomer.Work;
        } else if (choice == 2) {
            to = currentCustomer.Home;
            from = currentCustomer.Work;
        }
        if(choice == 3) {
            System.out.print("From:");
            from= scanner.nextLine();
            System.out.print("To:");
            to = scanner.nextLine();
        }

        System.out.print("Ride type: (Bus - Car - Scooter)");
        String rideType = scanner.nextLine();

        float _distance_ = 0; // Will Change According to ABO Main.Main.Rides.Graph

        if(rideType.equals("bus")){
            vehicleinstance.vehicle chosenBus;
            ArrayList<vehicleinstance.vehicle> availableBuses = new ArrayList<vehicleinstance.vehicle>();
            for(vehicleinstance.vehicle currentvehicle: vehiclelist){

                if(currentvehicle.vehiclecapacity >= 14){
                    availableBuses.add(currentvehicle);
                }
            }
            int numvh = 0;

            for(vehicleinstance.vehicle bus : availableBuses){
                System.out.println(++numvh + "." + bus.toString());
            }
            System.out.println("Enter the number of your choice");
            int busId = scanner.nextInt();

            chosenBus = availableBuses.get(busId-1);
            RequestedRide = new BusRide(_distance_,chosenBus.vehiclecapacity);
            RequestedRide.SetRoute(from, to);
             ((BusRide) RequestedRide).assignedvhmodel = chosenBus.vehicleModel; // compiler made this I don't why
             ((BusRide) RequestedRide).assignedvhnumber = chosenBus.vehicleNumber;
        }
        else if (rideType.equals("car")){
            vehicleinstance.vehicle chosencar;
            ArrayList<vehicleinstance.vehicle> avcars= new ArrayList<vehicleinstance.vehicle>();
            for(vehicleinstance.vehicle currentvh: vehiclelist){

                if(currentvh.vehiclecapacity >=4 & currentvh.vehiclecapacity<=6){
                    avcars.add(currentvh);
                }
            }
            int numcar = 0;

            for(vehicleinstance.vehicle eligible:avcars){
                System.out.println(++numcar + "." + eligible.toString());
            }
            System.out.println("Enter the number of your choice");
            int carid = scanner.nextInt();

            chosencar = avcars.get(carid-1);

            RequestedRide = new CarRide(_distance_);
            RequestedRide.SetRoute(from, to);}
        else{
        vehicleinstance.vehicle chosenscouter;
        ArrayList<vehicleinstance.vehicle> avscouters = new ArrayList<vehicleinstance.vehicle>();
        for(vehicleinstance.vehicle currentvh: vehiclelist){

            if(currentvh.vehicleType.equals("scouter")){
                avscouters.add(currentvh);
            }
        }
        int numvh = 0;

        for(vehicleinstance.vehicle eligible:avscouters){
            System.out.println(++numvh + "." + eligible.toString());
        }
        System.out.println("enter the number of your choice");
        int scouterid = scanner.nextInt();

        chosenscouter= avscouters.get(scouterid-1);
            RequestedRide = new ScooterRide(_distance_);
            RequestedRide.SetRoute(from, to);
        }

        float totalPrice = RequestedRide.CalculatePrice(_distance_);
        System.out.println("TotalPrice($):" + totalPrice);


        System.out.println("Request? (y/n)");
        char choiceChar = scanner.next().charAt(0);
        if(choiceChar == 'y')
        {
            PaymentValidation(totalPrice);
        }
    }

    /**
     * Displays the page for rating a driver and providing feedback.
     */
    public  void RateDiverPage() {
        System.out.print("Enter Number of Start (1 - 5)\n");
        currentDriver.totalrates += scanner.nextInt();
        currentDriver.numofrates++;
        scanner.nextLine();
        System.out.print("Enter FeedBack Message\n");
        String message = scanner.next();
        System.out.print("Thank you for the feedback, Wishes to you a good day\n");
    }


    /**
     * Displays the page for rating a driver and providing feedback.
     */
    public void RateDriverPage() {
        System.out.print("Enter Number of Stars (1 - 5)\n");
        int rate = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Feedback Message\n");
        String message = scanner.nextLine();
        System.out.print("Thank you for the feedback. Wishing you a good day!\n");
    }

    /**
     * Logs out the current user and returns to the start page or customer home page.
     */
    public void logout() {
        System.out.println("Do you really want to log out? (y/n)");
        char choice = scanner.next().charAt(0);
        if (choice == 'y') {
            currentCustomer = null; // Reset currentCustomer
            currentDriver = null;   // Reset currentDriver
            startPage();
        } else {
            customerHomePage();
        }
    }

    /**
     * Displays the customer's home page with new notifications.
     * Updates ticket prices for reserved bus rides if applicable.
     */
    public  void customerHomePage() {
      System.out.println("New Notifications:");
      if(currentCustomer.ReservedBusRide !=0){
          for( BusRide sob:Busrideslist){
              if(currentCustomer.ReservedBusRide == sob.BusRideId){
                  sob.CheckAvailability(currentCustomer);
                  break;
              }
          }
      }

      System.out.println("\n\n\n");
        System.out.println("1] Request a ride");
        System.out.println("2] Main.Main.Rides history");
        System.out.println("3] Main.Main.Rides.User.Customer Support");
        System.out.println("4] Logout");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (choice == 1) {
            requestARide();
        } else if (choice==2) {
            RideHistory();
        }
        else if (choice==3) {
            CustomerSupport();
        }
        else if (choice==4) {
            logout();
        }
        else {
            System.out.println("Invalid Input");
        }
        customerHomePage();

    }

    /**
     * Displays the ride history for the current customer, allowing them to clear it if desired.
     */
    public void RideHistory() {
        System.out.println("You have " + currentCustomer.RidesCount + " Main.Main.Rides:\n1- Cairo - Alex - 400km - 30$ - Car\n2- Aswan - Poirsaid - 900km - 270$ - Bus\n");
        System.out.print("1]Clear\t2]Back\n");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1){
            currentCustomer.RidesCount = 0;
            // clearHistory();  // it's Done When we make Files
            System.out.println("History Cleared Successfully\n");
        } else
            customerHomePage();
    }

    /**
     * Displays the login page where the user can enter their email and password to log in.
     * If successful, sets the current customer and displays the customer home page.
     */
    public void loginPage() {
        System.out.print("Email:");
        String userMail = scanner.nextLine();
        System.out.print("Password:");
        String userPassword = scanner.nextLine();
        for(Customer loggedin : customerList){
            if(loggedin.Uber_Mail.equals(userMail) & loggedin.Uber_Password.equals(userPassword))
            {
                currentCustomer = loggedin;
                break;
            }
        }
       for (Driver loggedin : driverList)
       {
           if(loggedin.Uber_Mail.equals(userMail) & loggedin.Uber_Password.equals(userPassword)) {
               currentDriver = loggedin;
               break;
           }
       }

       if(currentCustomer != null)
           customerHomePage();

       else if (currentDriver != null)
           driverHomePage();
    }

    /**
     * Displays a list of frequently asked questions (FAQs) related to customer support and provides answers.
     * Allows the user to select a question and read its corresponding answer.
     */
    public void CustomerSupport() {
        System.out.println("Q1: How do I request a ride?\n" +
                "Q2: How do I pay for my ride?\n" +
                "Q3: How do I cancel a ride?\n" +
                "Q4: How are fares calculated?\n" +
                "Q5: What if I left something in the vehicle?\n" +
                "Q6: How do I apply a promo code to my ride?\n" +
                "Q7: Can I change my destination after requesting a ride?\n" +
                "Q8: How can I provide feedback on my ride experience?\n" +
                "Q9: What safety features does Uber offer?\n" +
                "Q10: How do I become an Uber driver?\n");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            System.out.println("A1: To request a ride, simply open the Uber app, enter your destination, and choose your preferred type of ride. Confirm the details and a driver will be on their way to pick you up.\n");
        } else if (choice == 2) {
            System.out.println("A2: Uber accepts various payment methods, including credit/debit cards and digital wallets. You can add your payment details to the app, and your fare will be automatically charged after each ride.\n");
        } else if (choice == 3) {
            System.out.println("A3: To cancel a ride, open the app, go to the active ride screen, and tap on the \"Cancel\" button. Please note that there might be a cancellation fee if the driver is already on their way.\n");
        } else if (choice == 4) {
            System.out.println("A4: Fares are calculated based on factors such as distance, time, traffic conditions, and the type of ride you select. You'll see an estimated fare before confirming your ride.\n");
        } else if (choice == 5) {
            System.out.println("A5: If you believe you've left an item in the vehicle, you can use the app to contact your driver. Go to the \"Trips\" section, select the relevant ride, and choose \"Contact driver about a lost item.\"\n");
        } else if (choice == 6) {
            System.out.println("A6: When you're about to request a ride, tap on the \"Promotions\" section in the app and enter the promo code. The discount will be applied to your fare if it meets the terms and conditions.\n");
        } else if (choice == 7) {
            System.out.println("A7: Yes, you can change your destination after requesting a ride, but the fare may be adjusted based on the new route. You can update the destination in the app before your driver arrives or during the ride.\n");
        } else if (choice == 8) {
            System.out.println("A8: After each ride, you'll have the opportunity to rate your driver and provide feedback. You can do this by selecting the trip in the app and giving a star rating along with any comments.\n");
        } else if (choice == 9) {
            System.out.println("A9: Uber prioritizes safety with features like driver background checks, GPS tracking, and the ability to share your trip details with friends or family. You can also call emergency services directly from the app.\n");
        } else if (choice == 10) {
            System.out.println("A10: If you're interested in becoming a driver, you can sign up on the Uber website or app. You'll need to meet certain requirements, provide necessary documentation, and pass a background check.\n");
        }
        System.out.println("Press 0 to Back and 1 To Exit");
        choice  = scanner.nextInt();
        if(choice == 0)
            CustomerSupport();
        else{
            customerHomePage();
        }
    }

    /**
     * Validates payment details and processes the payment for a ride.
     *
     * @param totalPrice The total price of the ride.
     */
    public void PaymentValidation(float totalPrice){
        paymentController paymentMethod = new paymentController();
        paymentMethod.setStrategy(currentCustomer);
        System.out.print("Enter Account Number:");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter Account Password:");
        String accountPassword = scanner.nextLine();
        // Waiting for Price ###ABO### :(

        if (paymentMethod.checkCredentials(accountNumber, accountPassword))
            if(paymentMethod.checkBalance(totalPrice))
            {
                paymentMethod.deductBalance(totalPrice);
                System.out.println("Transaction Complete");
                currentCustomer.RidesCount++;

                System.out.println("Main.Main.Rides.Ride Done, Thank you to use Uber.\n[0: Home Page]\t[2: Rate Main.Main.Rides.User.Driver]");

                int choice2 = scanner.nextInt();
                scanner.nextLine();
                if (choice2 == 0)
                    customerHomePage();
                else
                    RateDiverPage();

            }else
                System.out.println("You have not enough money");
        else
            System.out.println("Incorrect Data");

    }
}
