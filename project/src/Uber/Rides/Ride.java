package Uber.Rides;

/**
 * The Ride abstract class serves as a base class for representing different types of rides in the ride-sharing system.
 * It provides common properties such as distance, starting point, destination, price, and user rating.
 * Subclasses of this class should implement the 'CalculatePrice' method to calculate the ride price.
 */
public abstract class Ride {
    public float distance ;
    public String From;
    public String To;
    public float price ;
    public float rate;
    public abstract float CalculatePrice (float distance);

    /**
     * Default constructor for the Ride class.
     */
    public Ride(){}

    /**
     * Parameterized constructor for the Ride class.
     *
     * @param distance The distance of the ride.
     */
    public Ride(float distance) {
        this.distance = distance;
    }

    /**
     * Sets the starting point and destination of the ride.
     *
     * @param fromDistenation The starting point of the ride.
     * @param toDistenation  The destination of the ride.
     * @return The current Ride instance with updated route information.
     */
    public  Ride SetRoute(String fromDistenation, String toDistenation){
        this.From = fromDistenation;
        this.To = toDistenation;
        return this;
    }

    //May Edit in Main.ConsoleUi > requestARide
    /*
    public Main.Main.Rides.Ride ChooseRide(Main.Main.Rides.Ride RuquestedRide, String type){
        if(type.equals("bus"))
            RuquestedRide = new Main.Main.Rides.BusRide(distance);
        else if (type.equals("car"))
            RuquestedRide = new Main.Main.Rides.CarRide(distance);
        else
            RuquestedRide = new Main.Main.Rides.ScooterRide(distance);

        return RuquestedRide;
    }
    */
}

