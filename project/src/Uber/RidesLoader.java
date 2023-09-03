package Uber;
import Uber.User.*;

import java.io.*;
import java.util.LinkedList;
import Uber.Rides.*;

import static Uber.CustomerDataSaver.customerList;
// the file pathes in this class must be changed because they are wrong
public class RidesLoader {
   public static LinkedList<BusRide> busRides = new LinkedList<>();
    public static void ReadBusrideData() throws IOException {
        String path = ".\\project\\src\\Uber\\UserData\\Rides.txt";
        BufferedReader Reader = new BufferedReader(new FileReader(path));
        String line;
        while((line = Reader.readLine()) != null){
            BusRide newbusride = new BusRide();
            newbusride.From = line;
            newbusride.To = Reader.readLine();
            newbusride.distance = Float.parseFloat(Reader.readLine());
            BusRide.latestID = Integer.parseInt(Reader.readLine());
            newbusride.Id = Integer.parseInt(Reader.readLine());
            newbusride.waitingCustomers = (Reader.readLine().equals("True") || Reader.readLine().equals("true")) ? true : false;
            newbusride.vehicleModel = Reader.readLine();
            newbusride.vehicleNumber = Reader.readLine();
            newbusride.minCharge = Float.parseFloat(Reader.readLine());
            newbusride.maxCharge = Float.parseFloat(Reader.readLine());
            newbusride.reservationsCount= Integer.parseInt(Reader.readLine());
            newbusride.capacity = Integer.parseInt(Reader.readLine());
            newbusride.ticketPrice = Float.parseFloat(Reader.readLine());
            newbusride.oldticketprice = Float.parseFloat(Reader.readLine());
           for(Customer revcustomer:customerList){
               if(revcustomer.ReservedBusRide == newbusride.Id)
           newbusride.customers.add(revcustomer);
           }

            busRides.add(newbusride);
        }
        Reader.close();

    }


    public static void WriteBusridedata() throws IOException {
        String path = ".\\project\\src\\Uber\\UserData\\Rides.txt";
        BufferedWriter BusRideData = new BufferedWriter(new FileWriter(path));

        for(BusRide checkride: busRides){
            if(checkride.customers.isEmpty()){
               busRides.remove(checkride);}
            else if (checkride.reservationsCount < (checkride.capacity*(1/4)) ){
                checkride.waitingCustomers = false;
            }
        }

        for (int i = 0; i < busRides.size(); i++) {
            BusRideData.append(busRides.get(i).From + "\n" + busRides.get(i).To);
            BusRideData.append("\n" + BusRide.latestID + "\n" + busRides.get(i).Id);
            BusRideData.append("\n" + busRides.get(i).waitingCustomers.toString());
            BusRideData.append("\n" + busRides.get(i).distance + "\n" + busRides.get(i).vehicleModel + "\n" + busRides.get(i).vehicleNumber + "\n"+ busRides.get(i).minCharge);
            BusRideData.append("\n" + busRides.get(i).maxCharge + "\n" + busRides.get(i).reservationsCount);
            BusRideData.append("\n" + busRides.get(i).capacity + "\n" + busRides.get(i).ticketPrice);
            BusRideData.append("\n" + busRides.get(i).oldticketprice + "\n");

        }
        BusRideData.close();

    }
    }
