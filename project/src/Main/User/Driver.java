
package Main.User;
import Main.*;
import java.util.Scanner;

/**
 * The Driver class represents a user who is also a driver for the ride-sharing service.
 * This class extends the Person class and adds additional properties and methods
 * specific to a driver's information and behavior.
 */
public class Driver  extends Person {
    public String licence;
    public String licenceType;
    public String vehicleModel;
    public String vehicleType;
    public String vehicleNumber;

    public  int vehiclecapacity;

    private static Driver currentDriver = null;

    /**
     * Returns the singleton instance of the Driver class.
     * If the instance doesn't exist, creates a new instance and returns it.
     *
     * @return The current driver instance.
     */
    public static Driver getInstance(){
        if(currentDriver == null){
            currentDriver = new Driver();

        }
        return currentDriver;
    }

    /**
     * Private constructor to prevent external instantiation of Driver.
     * Use getInstance() method to obtain the current driver instance.
     */
    public Driver(){

        super();
        //this.form();
    }

    /**
     * Collects user input to fill in the driver's information and saves it.
     * Prompts the user to enter the driver's name, age, email, password, license,
     * license type, vehicle model, vehicle type, vehicle number, and vehicle capacity.
     */
    @Override
    public void form(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Driver, Please enter the following data:");
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
