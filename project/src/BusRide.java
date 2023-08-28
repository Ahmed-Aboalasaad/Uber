public class BusRide extends Ride {

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
}
