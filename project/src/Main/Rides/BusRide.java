package Main.Rides;
import Main.ReservedRidesData;

import java.util.ArrayList;
import Main.User.*;
import static Main.ReservedRidesData.Busrideslist;


/**
 * The BusRide class represents a ride provided by a bus in the ride-sharing system.
 * This class extends the Ride class and adds additional properties and methods specific to bus rides.
 */
public class BusRide extends Ride implements BusReservation {

    // ADD Main.Main.Rides.User.Customer Package !!!!
   public ArrayList <Customer> revcustomerList = new ArrayList<Customer>();

   public static int Idtracker = 1;
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

    /**
     * Constructor for creating a bus ride with the given distance and capacity.
     *
     * @param distance The distance of the bus ride.
     * @param capacity The capacity of the bus.
     */
    public BusRide(float distance, int capacity) {
        super(distance);
        this.capacity = capacity;
        BusRideId = Idtracker;
        Idtracker++;
        savedata();
    }

    /**
     * Default constructor for the BusRide class.
     */
    public BusRide(){super();}

    /**
     * Reserves a seat for the specified customer on this bus ride.
     *
     * @param customer The customer reserving the seat.
     */
    public void Reserve(Customer customer){
        revcustomerList.add(customer);
        customer.ReservABus = true;
        reservationsCount++;
        oldticketprice =ticketPrice;
        ticketPrice= MinimumCharge / reservationsCount;
        maderefund = false;
        System.out.println("Reserve Successfully");
    }

    /**
     * Cancels the reservation of a seat for the specified customer on this bus ride.
     *
     * @param customer The customer canceling the reservation.
     */
    public void CancelReservation(Customer customer){
        revcustomerList.remove(customer);
        reservationsCount--;
        customer.ReservABus=false;
        oldticketprice =ticketPrice;
        ticketPrice= MinimumCharge / reservationsCount;
        tookaddition = false;
        System.out.println("Reserve Cancelled");
    }

    /**
     * Updates the ticket price for the bus ride based on changes in reservations.
     */
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
        }else{
            System.out.println("your trip to "+ From+" awaits you");
        }



    }

    /**
     * Saves the bus ride data to the list of reserved rides.
     */
    public void savedata(){
     Busrideslist.add(this);
    }


    /**
     * Processes refunds for customers who reserved seats for the bus ride.
     */
    public void processrefund(){
        for (Customer customer : revcustomerList){

            customer.payer.Refund(customer,(double)(oldticketprice - ticketPrice));

        }

        maderefund = true;
    }

    /**
     * Processes additional charges for customers who reserved seats for the bus ride.
     */
    public void processtakeaddition(){
        for(Customer customer:revcustomerList){
            customer.payer.deductBalance((double)(oldticketprice - ticketPrice));
        }
        tookaddition = true;
    }

}
