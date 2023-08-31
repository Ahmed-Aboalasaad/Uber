package Main.Rides;

public class CarRide extends Ride {
    float pricePerKM = 10;

    public CarRide(float distance) {
        super(distance);
    }

    @Override
    public float CalculatePrice(float distance) {
        return distance * pricePerKM;
    }
}
