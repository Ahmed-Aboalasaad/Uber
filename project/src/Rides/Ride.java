package Rides;

public abstract class Ride {
    float distance ;
    String From;
    String To;
    float price ;
    float rate;
    public abstract float CalculatePrice (float distance);

    public Ride(float distance) {
        this.distance = distance;
    }

    public  Ride SetRoute(String fromDistenation, String toDistenation){
        this.From = fromDistenation;
        this.To = toDistenation;
        return this;
    }

    //May Edit in ConsoleUi > requestARide
    /*
    public Rides.Ride ChooseRide(Rides.Ride RuquestedRide, String type){
        if(type.equals("bus"))
            RuquestedRide = new Rides.BusRide(distance);
        else if (type.equals("car"))
            RuquestedRide = new Rides.CarRide(distance);
        else
            RuquestedRide = new Rides.ScooterRide(distance);

        return RuquestedRide;
    }
    */
}

