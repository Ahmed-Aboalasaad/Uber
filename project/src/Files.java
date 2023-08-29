import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Files {
   public static void ReadDriverData() throws IOException {
      int i = 0;
      BufferedReader Reader = new BufferedReader(new FileReader("UserData\\Driver.txt"));

      String line;
      String DriverName ,DriverAge ,DriverUberMail, DriverUberPassword;


          DriverName = Reader.readLine();
          DriverAge = Reader.readLine();
          DriverUberMail = Reader.readLine();
          DriverUberPassword = Reader.readLine();


          //insert rest of data in the same format

      //let's say there is a list of drivers, we take all the data from the loop into a driver consturctor, then append it to the list.
      //this now adds the data into a list


   }
   public static void WriteDriverdata() throws IOException {
       //BufferedWriter DriverData = new BufferedWriter(new FileWriter("DriverData.txt"));
       //DriverData.write("/n" + Driver);


   }
}
