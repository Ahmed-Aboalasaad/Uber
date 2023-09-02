package Uber;
import Uber.User.*;

import java.io.*;
import java.util.LinkedList;
import Uber.Rides.*;

import static Uber.CustomerSavedData.customerList;
// the file pathes in this class must be changed because they are wrong
public class ReservedRidesData {
   public static LinkedList<BusRide> Busrideslist = new LinkedList<BusRide>();
    public static void ReadBusrideData() throws IOException {
        String path = ".\\project\\src\\Uber\\UserData\\Rides.txt";
        BufferedReader Reader = new BufferedReader(new FileReader(path));
        String line;
        while((line = Reader.readLine()) != null){
            BusRide newbusride = new BusRide();
            newbusride.From = line;
            newbusride.To = Reader.readLine();
            newbusride.distance = Float.parseFloat(Reader.readLine());
            BusRide.Idtracker = Integer.parseInt(Reader.readLine());
            newbusride.BusRideId = Integer.parseInt(Reader.readLine());
            newbusride.stillAvailable = (Reader.readLine().equals("True") || Reader.readLine().equals("true")) ? true : false;
            newbusride.assignedvhmodel = Reader.readLine();
            newbusride.assignedvhnumber = Reader.readLine();
            newbusride.MinimumCharge = Float.parseFloat(Reader.readLine());
            newbusride.Maxcharge = Float.parseFloat(Reader.readLine());
            newbusride.reservationsCount= Integer.parseInt(Reader.readLine());
            newbusride.capacity = Integer.parseInt(Reader.readLine());
            newbusride.ticketPrice = Float.parseFloat(Reader.readLine());
            newbusride.oldticketprice = Float.parseFloat(Reader.readLine());
           for(Customer revcustomer:customerList){
               if(revcustomer.ReservedBusRide == newbusride.BusRideId)
           newbusride.revcustomerList.add(revcustomer);
           }

            Busrideslist.add(newbusride);
        }
        Reader.close();

    }


    public static void WriteBusridedata() throws IOException {
        String path = ".\\project\\src\\Uber\\UserData\\Rides.txt";
        BufferedWriter BusRideData = new BufferedWriter(new FileWriter(path));

        for(BusRide checkride:Busrideslist){
            if(checkride.revcustomerList.isEmpty()){
               Busrideslist.remove(checkride);}
            else if (checkride.reservationsCount < (checkride.capacity*(1/4)) ){
                checkride.stillAvailable = false;
            }
        }

        for (int i = 0; i < Busrideslist.size(); i++) {
            BusRideData.append(Busrideslist.get(i).From + "\n" + Busrideslist.get(i).To);
            BusRideData.append(BusRide.Idtracker+ "\n" + Busrideslist.get(i).BusRideId);
            BusRideData.append(Busrideslist.get(i).stillAvailable.toString()+ "\n" );
            BusRideData.append("\n" + Busrideslist.get(i).distance + "\n" + Busrideslist.get(i).assignedvhmodel + "\n" + Busrideslist.get(i).assignedvhnumber + "\n"+ Busrideslist.get(i).MinimumCharge);
            BusRideData.append("\n" + Busrideslist.get(i).Maxcharge + "\n" + Busrideslist.get(i).reservationsCount);
            BusRideData.append("\n" + Busrideslist.get(i).capacity + "\n" + Busrideslist.get(i).ticketPrice);
            BusRideData.append("\n" + Busrideslist.get(i).oldticketprice + "\n");

        }
        BusRideData.close();

    }
    }
