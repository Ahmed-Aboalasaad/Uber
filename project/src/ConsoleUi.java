import java.util.Scanner;

public class ConsoleUi {
    public static Scanner scanner = new Scanner(System.in);
    Customer currentCustomer;
    Driver currentDriver;

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

    public void driverHomePage() {
        System.out.println("1] Rides history");
        System.out.println("2] Customer Support");
        System.out.println("3] Logout");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (choice == 1) {
            System.out.println("This is Ride History");
        } else if (choice==2) {
            System.out.println("This is Ride Customer Support");
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

    public void CustomerDataEntery() {

        CustomerRegisterForm customerForm = new CustomerRegisterForm();

        System.out.println("Hi Customer, Please enter the following data:");
        System.out.print("Name:");
        customerForm.Name = scanner.nextLine();
        System.out.print("\nAge:");
        customerForm.age = scanner.nextShort();
        System.out.print("\nMail:");
        customerForm.Mail = scanner.nextLine();
        System.out.print("\nPassword:");
        customerForm.password = scanner.nextLine();
        System.out.print("\nPayment Method: (Paypal - Card)");
        customerForm.paymentMethod = scanner.nextLine();

        currentCustomer = customerForm.get_info();
    }

    public void DriverDataEntery() {

            DriverRegisterForm drf  = new DriverRegisterForm();

            System.out.println("Hi Driver, Please enter the following data:");
            System.out.print("Name:");
            drf.Name = scanner.nextLine();
            System.out.print("Age:");
            drf.age = scanner.nextShort();
            System.out.print("Mail:");
            drf.Mail = scanner.nextLine();
            System.out.print("Password:");
            drf.password = scanner.nextLine();
            System.out.print("licence:");
            drf.licence = scanner.nextLine();
            System.out.print("licence_type:");
            drf.licence_type = scanner.nextLine();
            System.out.print("vehicle_model:");
            drf.vehicle_model = scanner.nextLine();
            System.out.print("vehicle_Type:");
            drf.licence_type = scanner.nextLine();
            System.out.print("Car_Number:");
            drf.car_Number = scanner.nextLine();

            currentDriver = drf.get_info();
        }

    public void newAccount() {
        System.out.println("Welcome To Uber, Are you Customer or Driver ?");
        System.out.println("1] Customer\t2] Driver");
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

    public  void requestARide() {
        Ride RuquestedRide;

        System.out.print("From:");
        String from = scanner.nextLine();
        System.out.print("To:");
        String to = scanner.nextLine();
        System.out.print("Ride type: (Bus - Car - Scooter)");
        String rideType = scanner.nextLine();

        float _distance_ = 0 ;

        if(rideType.equals("bus"))
            RuquestedRide = new BusRide(_distance_);
        else if (rideType.equals("car"))
            RuquestedRide = new CarRide(_distance_);
        else
            RuquestedRide = new ScooterRide(_distance_);

        float totalPrice = RuquestedRide.CalculatePrice(_distance_);

        System.out.println("TotalPrice($):" + totalPrice);

        System.out.println("Request? (y/n)");
        char choice = scanner.next().charAt(0);
        if(choice == 'y')
        {
            PaymentValidation(totalPrice);
        }
    }

    public  void RateDiverPage() {
        System.out.print("Enter Number of Start (1 - 5)\n");
        int rate = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter FeedBack Message\n");
        String message = scanner.next();
        System.out.print("Thank you for the feedback, Wishes to you a good day\n");
    }

    public  void logout() {
        System.out.println("Do you really want to logout ? (y/n)");
        char choice = scanner.next().charAt(0);
        if(choice == 'y')
            startPage();
        else
            customerHomePage();
    }

    public  void customerHomePage() {
        System.out.println("1] Request a ride");
        System.out.println("2] Rides history");
        System.out.println("3] Customer Support");
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

    public void RideHistory() {
        System.out.println("You have " + currentCustomer.RidesCount + " Rides:\n1- Cairo - Alex - 400km - 30$ - Car\n2- Aswan - Poirsaid - 900km - 270$ - Bus\n");
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

    public void loginPage() {
        System.out.print("Email:");
        String userMail = scanner.nextLine();
        System.out.print("Password:");
        String userPassword = scanner.nextLine();
        customerHomePage();
    }

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

    public void PaymentValidation(float totalPrice){
        payment_services paymentMethod = new payment_services();
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

                System.out.println("Ride Done, Thank you to use Uber.\n[0: Home Page]\t[2: Rate Driver]");

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
