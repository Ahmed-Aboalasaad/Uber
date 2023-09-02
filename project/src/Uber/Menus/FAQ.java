package Uber.Menus;
import Uber.*;
import static Uber.ConsoleUi.scanner;

public class FAQ {

    ConsoleUi consoleUi = new ConsoleUi();

    /**
     * Displays a list of frequently asked questions (FAQs) related to customer support and provides answers.
     * Allows the user to select a question and read its corresponding answer.
     */
    public static void questions() {
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
        if (choice == 1)
            System.out.println("A1: To request a ride, simply open the Uber app, enter your destination, and choose your preferred type of ride. Confirm the details and a driver will be on their way to pick you up.\n");
        else if (choice == 2)
            System.out.println("A2: Uber accepts various payment methods, including credit/debit cards and digital wallets. You can add your payment details to the app, and your fare will be automatically charged after each ride.\n");
        else if (choice == 3)
            System.out.println("A3: To cancel a ride, open the app, go to the active ride screen, and tap on the \"Cancel\" button. Please note that there might be a cancellation fee if the driver is already on their way.\n");
        else if (choice == 4)
            System.out.println("A4: Fares are calculated based on factors such as distance, time, traffic conditions, and the type of ride you select. You'll see an estimated fare before confirming your ride.\n");
        else if (choice == 5)
            System.out.println("A5: If you believe you've left an item in the vehicle, you can use the app to contact your driver. Go to the \"Trips\" section, select the relevant ride, and choose \"Contact driver about a lost item.\"\n");
        else if (choice == 6)
            System.out.println("A6: When you're about to request a ride, tap on the \"Promotions\" section in the app and enter the promo code. The discount will be applied to your fare if it meets the terms and conditions.\n");
        else if (choice == 7)
            System.out.println("A7: Yes, you can change your destination after requesting a ride, but the fare may be adjusted based on the new route. You can update the destination in the app before your driver arrives or during the ride.\n");
        else if (choice == 8)
            System.out.println("A8: After each ride, you'll have the opportunity to rate your driver and provide feedback. You can do this by selecting the trip in the app and giving a star rating along with any comments.\n");
        else if (choice == 9)
            System.out.println("A9: Uber prioritizes safety with features like driver background checks, GPS tracking, and the ability to share your trip details with friends or family. You can also call emergency services directly from the app.\n");
        else if (choice == 10)
            System.out.println("A10: If you're interested in becoming a driver, you can sign up on the Uber website or app. You'll need to meet certain requirements, provide necessary documentation, and pass a background check.\n");

        System.out.println("1] Another Question\n2] Home Page");
        choice = scanner.nextInt();
        if (choice == 1)
            questions();
    }
}
