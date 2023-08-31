package Main.Rides;

import java.util.ArrayList;
import Main.User.*;


public class BusRide extends Ride implements BusReservation {

    // ADD Main.Main.Rides.User.Customer Package !!!!
    ArrayList <Customer> customerList = new ArrayList<Customer>();

    Double MinimumCharge;
    int reservationsCount = 0;
    float ticketPrice;
    @Override
    public float CalculatePrice(float distance) {
        return ticketPrice ;
    }

    public BusRide(float distance) {
        super(distance);
        reservationsCount++;
    }

    public void Reserve(Customer customer){
        customerList.add(customer);
        reservationsCount++;
        customer.ReservationPrice = MinimumCharge / reservationsCount;
        System.out.println("Reserve Successfully");
    }

    public void CancelReservation(Customer customer){
        customerList.remove(customer);
        reservationsCount--;
        System.out.println("Reserve Cancelled");
    }

    public void UpdateTicketPrice(){
        double latestPrice = customerList.get(customerList.size()-1).ReservationPrice;
        for (Customer customer : customerList){
            if(latestPrice != customer.ReservationPrice){
                customer.payer.Refund(customer,customer.ReservationPrice - latestPrice);
            }
        }
    }



}
