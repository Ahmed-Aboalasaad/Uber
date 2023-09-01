package Main;
import Main.User.*;

import java.io.*;
import java.util.LinkedList;
import Main.Rides.*;

import static Main.CustomerSavedData.customerList;
// the file pathes in this class must be changed because they are wrong
public class ReservedRidesData {
   public static LinkedList<BusRide> Busrideslist = new LinkedList<BusRide>();
    public static void ReadDriverData() throws IOException {
        BufferedReader Reader = new BufferedReader(new FileReader("C:\\Users\\Adham\\IdeaProjects\\Uber\\project\\src\\Main.Main.Rides.UserData\\Main.Main.Rides.User.Driver.txt"));
        String line;
        while((line = Reader.readLine()) != null){
            BusRide newbusride = new BusRide();
            newbusride.From = line;
            newbusride.To = Reader.readLine();
            newbusride.distance = Float.parseFloat(Reader.readLine());
            newbusride.MinimumCharge = Float.parseFloat(Reader.readLine());
            newbusride.Maxcharge = Float.parseFloat(Reader.readLine());
            newbusride.reservationsCount= Integer.parseInt(Reader.readLine());
            newbusride.capacity = Integer.parseInt(Reader.readLine());
            newbusride.ticketPrice = Float.parseFloat(Reader.readLine());
            newbusride.oldticketprice = Float.parseFloat(Reader.readLine());
           for(Customer revcustomer:customerList){
               if(Reader.readLine().equals(revcustomer.Uber_Mail) & Reader.readLine().equals(revcustomer.Uber_Password))
           newbusride.revcustomerList.add(revcustomer);
           }

            Busrideslist.add(newbusride);
        }
        Reader.close();


    }


    public static void WriteDriverdata() throws IOException {
        BufferedWriter BusRideData = new BufferedWriter(new FileWriter("C:\\Users\\Adham\\IdeaProjects\\Uber\\project\\src\\Main.Main.Rides.UserData\\Main.Main.Rides.User.Driver.txt"));

        for (int i = 0; i < Busrideslist.size(); i++) {
            BusRideData.append(Busrideslist.get(i).From + "\n" + Busrideslist.get(i).To);
            BusRideData.append("\n" + Busrideslist.get(i).distance + "\n" + Busrideslist.get(i).MinimumCharge);
            BusRideData.append("\n" + Busrideslist.get(i).Maxcharge + "\n" + Busrideslist.get(i).reservationsCount);
            BusRideData.append("\n" + Busrideslist.get(i).capacity + "\n" + Busrideslist.get(i).ticketPrice);
            BusRideData.append("\n" + Busrideslist.get(i).oldticketprice + "\n");
            for (Customer savedCustomer : Busrideslist.get(i).revcustomerList) {
                BusRideData.append("\n" + savedCustomer.Uber_Mail + "\n");
                BusRideData.append("\n" + savedCustomer.Uber_Password + "\n");
            }
        }
        BusRideData.close();

    }
    }
