public class BusRide extends Ride {


    int numofreservationsCounter=0;
    float TicketPrice;
    @Override
    public float CalculatePrice(float Distance) {
        return TicketPrice ;
    }

    public BusRide(float distance) {
        super(distance);
        numofreservationsCounter++;
    }


}
