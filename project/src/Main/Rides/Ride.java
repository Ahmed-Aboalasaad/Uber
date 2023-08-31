package Main.Rides;

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

