
import java.util.Scanner;
public class Driver  extends Person {
    String licence;
    String licenceType;
    String vehicleModel;
    String vehicleType;
    String vehicleNumber;


    public Driver(){
        super();
        this.form();
    }

    @Override
    public void form(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hi Driver, Please enter the following data:");
        System.out.print("Name:");
        this.Name = scanner.nextLine();
        System.out.print("Age:");
        this.Age = scanner.nextShort();
        System.out.print("Mail:");
        this.Uber_Mail = scanner.nextLine();
        System.out.print("Password:");
        this.Uber_Password = scanner.nextLine();
        System.out.print("licence:");
        this.licence = scanner.nextLine();
        System.out.print("licence_type:");
        this.licenceType = scanner.nextLine();
        System.out.print("vehicle_model:");
        this.vehicleModel = scanner.nextLine();
        System.out.print("vehicle_Type:");
        this.vehicleType = scanner.nextLine();
        System.out.print("Car_Number:");
        this.vehicleNumber = scanner.nextLine();
    }

}
