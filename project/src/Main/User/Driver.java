
package Main.User;
import Main.*;
import java.util.Scanner;
public class Driver  extends Person {
    public String licence;
    public String licenceType;
    public String vehicleModel;
    public String vehicleType;
    public String vehicleNumber;

    public  int vehiclecapacity;

    private static Driver currentDriver = null;

    public static Driver getInstance(){
        if(currentDriver == null){
            currentDriver = new Driver();

        }
        return currentDriver;
    }


    public Driver(){

        super();
        //this.form();
    }

    @Override
    public void form(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hi Main.Main.Rides.User.Driver, Please enter the following data:");
        System.out.print("Name:");
        currentDriver.Name = scanner.nextLine();
        System.out.print("Age:");
        currentDriver.Age = Short.parseShort(scanner.nextLine());
        System.out.print("Mail:");
        currentDriver.Uber_Mail = scanner.nextLine();
        System.out.print("Password:");
        currentDriver.Uber_Password = scanner.nextLine();
        System.out.print("licence:");
        currentDriver.licence = scanner.nextLine();
        System.out.print("licence_type:");
        currentDriver.licenceType = scanner.nextLine();
        System.out.print("vehicle_model:");
        currentDriver.vehicleModel = scanner.nextLine();
        System.out.print("vehicle_Type:");
        currentDriver.vehicleType = scanner.nextLine();
        System.out.print("Car_Number:");
        currentDriver.vehicleNumber = scanner.nextLine();
        System.out.print("enter your vehicle's capacity:");
        currentDriver.vehiclecapacity = scanner.nextInt();

        DriverSavedData.driverList.add(currentDriver);

    }

}
