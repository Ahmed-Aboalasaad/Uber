public class Driver  extends Person {
    String licence;
    String licenceType;
    String vehicleModel;
    String vehicleType;
    String carNumber;

    public Driver(String name, short age,String Mail, String password, String licence, String licenceType, String vehicleModel, String vehicleType, String carNumber)
    {
        super(name, age,Mail, password);
        this.licence = licence;
        this.licenceType = licenceType;
        this.vehicleModel = vehicleModel;
        this.vehicleType = vehicleType;
        this.carNumber = carNumber;
    }
}
