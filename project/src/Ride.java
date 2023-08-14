public  abstract class Ride {
    float distance ;
    float price ;
    float rate;
    public abstract float CalculatePrice ( float Distance );

    public Ride(float distance) {
        this.distance = distance;
    }
}

