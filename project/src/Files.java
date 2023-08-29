import java.io.*;
import java.util.LinkedList;


public class Files {
    public static LinkedList<Driver> driverList = new LinkedList<>();
    public static LinkedList<Customer> customerList = new LinkedList<>();
    public static void ReadDriverData() throws IOException {
      BufferedReader Reader = new BufferedReader(new FileReader("C:\\Users\\Adham\\IdeaProjects\\Uber\\project\\src\\UserData\\Driver.txt"));
      String line;
      while((line = Reader.readLine()) != null){
          Driver newDriver = new Driver();
          newDriver.Name = line;
          newDriver.Age = Short.parseShort(Reader.readLine());
          newDriver.Uber_Mail = Reader.readLine();
          newDriver.Uber_Password = Reader.readLine();
          newDriver.licence = Reader.readLine();
          newDriver.licenceType = Reader.readLine();
          newDriver.vehicleModel = Reader.readLine();
          newDriver.vehicleType = Reader.readLine();
          newDriver.vehicleNumber = Reader.readLine();

          driverList.add(newDriver);
      }
    Reader.close();



   }
   public static void WriteDriverdata() throws IOException {
        BufferedWriter DriverData = new BufferedWriter(new FileWriter("C:\\Users\\Adham\\IdeaProjects\\Uber\\project\\src\\UserData\\Driver.txt"));

        for(int i = 0 ; i < driverList.size(); i ++) {
           DriverData.append(driverList.get(i).Name + "\n" + driverList.get(i).Age);
           DriverData.append("\n" + driverList.get(i).Uber_Mail + "\n" + driverList.get(i).Uber_Password);
           DriverData.append("\n" + driverList.get(i).licence + "\n" + driverList.get(i).licenceType);
           DriverData.append("\n" + driverList.get(i).vehicleModel + "\n" + driverList.get(i).vehicleType);
           DriverData.append("\n" + driverList.get(i).vehicleNumber + "\n");
       }
       DriverData.close();


   }
}
