package Main.Rides;
import Main.ReservedRidesData;

import java.util.ArrayList;
import Main.User.*;

import static Main.ReservedRidesData.Busrideslist;


public class BusRide extends Ride implements BusReservation {

    // ADD Main.Main.Rides.User.Customer Package !!!!
   public ArrayList <Customer> revcustomerList = new ArrayList<Customer>();

   public static int Idtracker = 1;

   public Boolean stillavailable = true;
   public int BusRideId;
    public float MinimumCharge;

     public int reservationsCount = 0;
     public int capacity;

    public float ticketPrice;
   public float oldticketprice = 0;
     public double Maxcharge = (ticketPrice)*this.capacity;

     public boolean maderefund = true;
     public boolean tookaddition = true;
    @Override
    public float CalculatePrice(float distance) {
        return ticketPrice ;
    }

    public BusRide(float distance, int capacity) {
        super(distance);
        this.capacity = capacity;
        BusRideId = Idtracker;
        Idtracker++;
        savedata();
    }
public BusRide(){super();}
    public void Reserve(Customer customer){
        revcustomerList.add(customer);
        customer.ReservABus = true;
        reservationsCount++;
        oldticketprice =ticketPrice;
        ticketPrice= MinimumCharge / reservationsCount;
        maderefund = false;
        System.out.println("Reserve Successfully");
    }

    public void CancelReservation(Customer customer){
        revcustomerList.remove(customer);
        reservationsCount--;
        customer.ReservABus=false;
        oldticketprice =ticketPrice;
        ticketPrice= MinimumCharge / reservationsCount;
        tookaddition = false;
        System.out.println("Reserve Cancelled");
    }

    public void UpdateTicketPrice(){
        if(oldticketprice == 0)
            return;

        if(!maderefund){
            processrefund();
            System.out.println("your trip ticket price has changed from "
                    + oldticketprice + " to "+ ticketPrice);

            System.out.println((oldticketprice-ticketPrice)+" were refunded to your account");
        }else if(!tookaddition){
            processtakeaddition();
            System.out.println("your trip ticket price has changed from "
                    + oldticketprice + " to "+ ticketPrice);

            System.out.println((oldticketprice-ticketPrice)+" were deducted from your account");
        }



    }
    public void savedata(){
     Busrideslist.add(this);
    }



    public void processrefund(){
        for (Customer customer : revcustomerList){

            customer.payer.Refund(customer,(double)(oldticketprice - ticketPrice));

        }

        maderefund = true;
    }

    public void processrefund(Customer customer){
        customer.payer.Refund(customer,(double)this.ticketPrice);

        customer.ReservABus = false;
        customer.ReservedBusRide = 0;
        revcustomerList.remove(customer);
        System.out.println("the trip was cancelled and "+ this.ticketPrice +" were refunded to your account");
    }

    public void processtakeaddition(){
        for(Customer customer:revcustomerList){
            customer.payer.deductBalance((double)(oldticketprice - ticketPrice));
        }

        tookaddition = true;
    }
   public void checkavailability(Customer customer){
        if(this.stillavailable){
            UpdateTicketPrice();
            System.out.println("\nyour trip awaits");
        }else{
           processrefund(customer);
        }
   }


}
