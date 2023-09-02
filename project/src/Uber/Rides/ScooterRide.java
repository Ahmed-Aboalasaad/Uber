package Uber.Rides;

public class ScooterRide extends Ride {
    float pricePerKM = 5;

    /**
     * Default constructor for the ScooterRide class.
     */
    public ScooterRide(){super();}

    /**
     * Constructor for creating a scooter ride with the given distance.
     *
     * @param distance The distance of the scooter ride.
     */
    public ScooterRide(float distance) {
        super(distance);
    }

    /**
     * Calculates the price of the scooter ride based on the provided distance and price per kilometer.
     *
     * @param distance The distance of the scooter ride.
     * @return The calculated price of the scooter ride.
     */
    @Override
    public float CalculatePrice(float distance) {
        return distance * pricePerKM;
    }
}