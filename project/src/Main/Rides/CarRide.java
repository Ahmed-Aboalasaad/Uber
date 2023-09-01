package Main.Rides;

/**
 * The CarRide class represents a ride provided by a car in the ride-sharing system.
 * This class extends the Ride class and calculates the price of the ride based on the distance traveled.
 */
public class CarRide extends Ride {
    float pricePerKM = 10;

    /**
     * Default constructor for the CarRide class.
     */
    public CarRide(){super();}

    /**
     * Constructor for creating a car ride with the given distance.
     *
     * @param distance The distance of the car ride.
     */
    public CarRide(float distance) {
        super(distance);
    }

    /**
     * Calculates the price of the car ride based on the provided distance and price per kilometer.
     *
     * @param distance The distance of the car ride.
     * @return The calculated price of the car ride.
     */
    @Override
    public float CalculatePrice(float distance) {
        return distance * pricePerKM;
    }
}
