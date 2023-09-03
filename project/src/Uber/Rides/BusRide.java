package Uber.Rides;

import java.util.ArrayList;
import Uber.User.*;

import static Uber.RidesLoader.busRides;

public class BusRide extends Ride implements BusReservation {
    public ArrayList < Customer > customers = new ArrayList<>();
    public static int latestID = 1;
    public String vehicleModel;
    public String vehicleNumber;
    public int Id;
    public float minCharge;
    public int reservationsCount = 0;
    public int capacity;
    public float ticketPrice;
    public float oldticketprice = 0;
    public float maxCharge = (ticketPrice) * this.capacity;
    public Boolean waitingCustomers = true;
    public boolean madeRefund = true;
    public boolean tookAddition = true;

    @Override
    public float CalculatePrice(float distance) {
        return ticketPrice;
    }

    public BusRide(float distance, int capacity, Customer customer) {
        super(distance);
        this.capacity = capacity;
        this.Reserve(customer);
        Id = latestID;
        latestID++;
        savedata();
    }

    public BusRide() {
        super();
    }

    public void Reserve(Customer customer) {
        customers.add(customer);
        reservationsCount++;
        oldticketprice = ticketPrice;
        ticketPrice = minCharge / reservationsCount;
        madeRefund = false;
        customer.ReservedBusRide = this.Id;
        System.out.println("Reserve Successfully");
    }

    public void CancelReservation(Customer customer) {
        customers.remove(customer);
        reservationsCount--;
        oldticketprice = ticketPrice;
        ticketPrice = minCharge / reservationsCount;
        tookAddition = false;
        customer.ReservedBusRide = 0;
        processRefund(customer);
        System.out.println("Reserve Cancelled");
    }

    public void UpdateTicketPrice() {
        if (oldticketprice == 0)
            return;

        if (!madeRefund) {
            processRefund();
            System.out.println("your trip ticket price has changed from " +
                    oldticketprice + " to " + ticketPrice);

            System.out.println((oldticketprice - ticketPrice) + " were refunded to your account");
        } else if (!tookAddition) {
            processTakeAddition();
            System.out.println("your trip ticket price has changed from " +
                    oldticketprice + " to " + ticketPrice);

            System.out.println((ticketPrice - oldticketprice) + " were deducted from your account");
        }

    }

    public void savedata() {
        busRides.add(this);
    }

    public void processRefund() {
        for (Customer customer: customers) {
            customer.payer.Refund(customer, (oldticketprice - ticketPrice));
        }
        madeRefund = true;
    }

    public void processTakeAddition() {
        for (Customer customer: customers)
            customer.payer.deductBalance((ticketPrice - oldticketprice));
        tookAddition = true;
    }

    public void processRefund(Customer customer) {
        customer.payer.Refund(customer, ticketPrice);
        customers.remove(customer);
        customer.ReservedBusRide = 0;
        System.out.println("Ticket Price has been refunded to your account");
    }

    public void CheckAvailability(Customer customer) {
        if (waitingCustomers) {
            UpdateTicketPrice();
            System.out.println("you trip awaits");
        } else {
            processRefund(customer);
        }
    }

    public void CheckAvailability(Driver driver) {

        if (waitingCustomers) {
            System.out.println("still waiting to set off your trip");
        } else {
            System.out.println("your trip to " + this.To + " has been cancelled");
            driver.BusrideId = 0;
        }

    }
    @Override
    public String toString() {
        return "trip id: " + this.Id + "\n vehicle model: " + this.vehicleModel +
                "\n vehicle number: " + this.vehicleNumber + "\n Capacity: " + this.capacity +
                "\n ticketprice:" + this.ticketPrice + "\n From:" + this.From + "\nTo:" + this.To;
    }

}