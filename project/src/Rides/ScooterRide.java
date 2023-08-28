package Rides;

public class ScooterRide extends Ride {
    float pricePerKM;

    public ScooterRide(float distance) {
        super(distance);
    }

    @Override
    public float CalculatePrice(float distance) {
        return distance * pricePerKM;
    }
}