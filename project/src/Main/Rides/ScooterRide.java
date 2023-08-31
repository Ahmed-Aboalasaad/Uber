package Main.Rides;

public class ScooterRide extends Ride {
    float pricePerKM = 5;

    public ScooterRide(float distance) {
        super(distance);
    }

    @Override
    public float CalculatePrice(float distance) {
        return distance * pricePerKM;
    }
}