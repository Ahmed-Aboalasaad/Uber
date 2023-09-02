package Uber;
import Uber.User.*;
import java.io.*;
import java.util.LinkedList;

/**
 * The DriverSavedData class manages reading and writing driver data to/from a file.
 */
public class DriverSavedData {

    public static LinkedList<Driver> driverList = new LinkedList<>();

    /**
     * Reads driver data from a file and populates the driverList.
     *
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static void ReadDriverData() throws IOException {
        String path = ".\\project\\src\\Uber\\UserData\\Driver.txt";
        BufferedReader Reader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = Reader.readLine()) != null) {
            Driver newDriver = new Driver();
            newDriver.Name = line;
            newDriver.Age = Short.parseShort(Reader.readLine());
            newDriver.Uber_Mail = Reader.readLine();
            newDriver.Uber_Password = Reader.readLine();
            newDriver.BusrideId = Integer.parseInt(Reader.readLine());
            newDriver.numofrates = Integer.parseInt(Reader.readLine());
            newDriver.totalrates = Double.parseDouble(Reader.readLine());
            newDriver.licence = Reader.readLine();
            newDriver.licenceType = Reader.readLine();
            newDriver.vehicleModel = Reader.readLine();
            newDriver.vehicleType = Reader.readLine();
            newDriver.vehicleNumber = Reader.readLine();
            newDriver.vehiclecapacity = Integer.parseInt(Reader.readLine());

            driverList.add(newDriver);
        }
        Reader.close();
    }

    /**
     * Writes driver data from the driverList to a file.
     *
     * @throws IOException If an I/O error occurs while writing the file.
     */
    public static void WriteDriverdata() throws IOException {
        String path = ".\\project\\src\\Uber\\UserData\\Driver.txt";
        BufferedWriter DriverData = new BufferedWriter(new FileWriter(path));

        for (int i = 0; i < driverList.size(); i++) {
            DriverData.append(driverList.get(i).Name + "\n" + driverList.get(i).Age);
            DriverData.append("\n" + driverList.get(i).Uber_Mail + "\n" + driverList.get(i).Uber_Password);
            DriverData.append("\n" + driverList.get(i).BusrideId);
            DriverData.append("\n" + driverList.get(i).numofrates + "\n" + driverList.get(i).totalrates);
            DriverData.append("\n" + driverList.get(i).licence + "\n" + driverList.get(i).licenceType);
            DriverData.append("\n" + driverList.get(i).vehicleModel + "\n" + driverList.get(i).vehicleType);
            DriverData.append("\n" + driverList.get(i).vehicleNumber + "\n");
            DriverData.append("\n" + driverList.get(i).vehiclecapacity + "\n");
        }
        DriverData.close();
    }
}
