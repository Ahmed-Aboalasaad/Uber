public class CarRide extends Ride {
        float KMprice;

    public CarRide(float distance) {
        super(distance);

    }

    @Override
    public float CalculatePrice(float Distance) {

        return distance*KMprice ;
    }
}
