public class Driver  extends person {
    String licence;
    String licence_type;
    String vehicle_model;
    String vehicle_Type;

    String car_Number;

    public Driver(String name, short age,String Mail, String password, String licence, String licence_type, String vehicle_model, String vehicle_Type, String car_Number) {
        super(name, age,Mail, password);
        this.licence = licence;
        this.licence_type = licence_type;
        this.vehicle_model = vehicle_model;
        this.vehicle_Type = vehicle_Type;
        this.car_Number = car_Number;
    }
}
