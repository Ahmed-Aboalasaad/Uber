import java.util.Scanner;
public class Customer extends Person {

    public String currentLocation;
    public String paymentMethodtype;
    public paymentStrategy payer;


    public Customer(){
        super();
       // this.form();
    }


@Override
    public void form(){
    Customer nCustomer = new Customer();
    Scanner scanner = new Scanner(System.in);
    System.out.println("Hi Customer, Please enter the following data:");
    System.out.print("Name:");
    nCustomer.Name = scanner.nextLine();
    System.out.print("Age:");
    nCustomer.Age = Short.parseShort(scanner.nextLine());
    System.out.print("Mail:");
    nCustomer.Uber_Mail = scanner.nextLine();
    System.out.print("Password:");
    nCustomer.Uber_Password = scanner.nextLine();
    System.out.println("Current Location:");
    nCustomer.currentLocation = scanner.nextLine();
    System.out.print("Payment Method: (Paypal - Card)");
    nCustomer.paymentMethodtype = scanner.nextLine();

    CustomerSavedData.customerList.add(nCustomer);
}

}
