public class Customer_register_form implements register_form {

    short id;
    String Name;
    short age;

    String password;
    @Override
    public person get_info() {
        Customer customer = new Customer(id, Name, age, password);
        return customer;
    }
}
