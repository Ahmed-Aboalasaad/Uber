public class ScooterRide extends Ride {
    float KMprice;

    public ScooterRide(float distance) {
        super(distance);

    }

    @Override
    public float CalculatePrice(float Distance) {

        return distance * KMprice;
    }
}