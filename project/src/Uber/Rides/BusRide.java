package Uber.Rides;

import java.util.ArrayList;
import Uber.User.*;

import static Uber.ReservedRidesData.Busrideslist;


public class BusRide extends Ride implements BusReservation {
   public ArrayList <Customer> revcustomerList = new ArrayList<Customer>();
   public String assignedvhmodel;
    public String assignedvhnumber;
    public static int Idtracker = 1;
   public int BusRideId;
   public float MinimumCharge;
   public int reservationsCount = 0;
   public int capacity;
   public float ticketPrice;
   public float oldticketprice = 0;
   public double Maxcharge = (ticketPrice)*this.capacity;
    public Boolean stillAvailable = true;
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

    public BusRide(){
       super();
   }
    public void Reserve(Customer customer){
        revcustomerList.add(customer);
        reservationsCount++;
        oldticketprice =ticketPrice;
        ticketPrice= MinimumCharge / reservationsCount;
        maderefund = false;
        customer.ReservedBusRide = this.BusRideId;
        System.out.println("Reserve Successfully");
    }

    public void CancelReservation(Customer customer){
        revcustomerList.remove(customer);
        reservationsCount--;
        oldticketprice =ticketPrice;
        ticketPrice= MinimumCharge / reservationsCount;
        tookaddition = false;
        customer.ReservedBusRide = this.BusRideId;
        System.out.println("Reserve Cancelled");
    }

    public void UpdateTicketPrice(){
        if(oldticketprice == 0)
            return;

        if(!maderefund){
            processRefund();
            System.out.println("your trip ticket price has changed from "
                    + oldticketprice + " to "+ ticketPrice);

            System.out.println((oldticketprice-ticketPrice)+" were refunded to your account");
        }else if(!tookaddition){
            processTakeAddition();
            System.out.println("your trip ticket price has changed from "
                    + oldticketprice + " to "+ ticketPrice);

            System.out.println((oldticketprice-ticketPrice)+" were deducted from your account");
        }

    }
    public void savedata(){
     Busrideslist.add(this);
    }

    public void processRefund(){
        for (Customer customer : revcustomerList){
            customer.payer.Refund(customer,(double)(oldticketprice - ticketPrice));
        }
        maderefund = true;
    }

    public void processTakeAddition(){
        for(Customer customer:revcustomerList){
            customer.payer.deductBalance((double)(oldticketprice - ticketPrice));
        }
        tookaddition = true;
    }

    public void processRefund(Customer customer) {
        customer.payer.Refund(customer, (double)ticketPrice);
        revcustomerList.remove(customer);
        customer.ReservedBusRide = 0;
        System.out.println("Ticket Price has been refunded to your account");
    }

    public void CheckAvailability(Customer customer)
    {
        if(stillAvailable)
        {
            UpdateTicketPrice();
            System.out.println("you trip awaits");
        }
        else {
            processRefund(customer);
        }
    }

    public void CheckAvailability(Driver driver){

        if(stillAvailable){
            System.out.println("still waiting to set off your trip");
        }else {
            System.out.println("your trip to "+ this.To +" has been cancelled");
            driver.BusrideId = 0;
        }

    }

}
