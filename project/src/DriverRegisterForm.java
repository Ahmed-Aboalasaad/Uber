public class DriverRegisterForm implements register_form {

    String car_Number; short id;
    String Name;
    short age;
    String Mail;
    String password;
    String licence;
    String licence_type;
    String vehicle_model;
    String vehicle_Type;

    @Override
    public Driver get_info() {
        Driver driver = new Driver(Name, age,Mail, password,licence,licence_type,vehicle_model,vehicle_Type,car_Number);
        return driver;
    }
}
