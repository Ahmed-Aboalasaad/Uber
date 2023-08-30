
import java.util.Scanner;
public class Driver  extends Person {
    public String licence;
    public String licenceType;
    public String vehicleModel;
    public String vehicleType;
    public String vehicleNumber;

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
        Driver nDriver = new Driver();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hi Driver, Please enter the following data:");
        System.out.print("Name:");
        nDriver.Name = scanner.nextLine();
        System.out.print("Age:");
        nDriver.Age = Short.parseShort(scanner.nextLine());
        System.out.print("Mail:");
        nDriver.Uber_Mail = scanner.nextLine();
        System.out.print("Password:");
        nDriver.Uber_Password = scanner.nextLine();
        System.out.print("licence:");
        nDriver.licence = scanner.nextLine();
        System.out.print("licence_type:");
        nDriver.licenceType = scanner.nextLine();
        System.out.print("vehicle_model:");
        nDriver.vehicleModel = scanner.nextLine();
        System.out.print("vehicle_Type:");
        nDriver.vehicleType = scanner.nextLine();
        System.out.print("Car_Number:");
        nDriver.vehicleNumber = scanner.nextLine();

        DriverSavedData.driverList.add(nDriver);
    }

}
