public abstract class Ride {
    float distance ;
    float price ;
    float rate;
    public abstract float CalculatePrice (float distance);

    public Ride(float distance) {
        this.distance = distance;
    }

    //May Edit in ConsoleUi > requestARide
    /*
    public Ride ChooseRide(Ride RuquestedRide, String type){
        if(type.equals("bus"))
            RuquestedRide = new BusRide(distance);
        else if (type.equals("car"))
            RuquestedRide = new CarRide(distance);
        else
            RuquestedRide = new ScooterRide(distance);

        return RuquestedRide;
    }
    */
}

