public class Customer_register_form implements register_form {

    short id;
    String Name;
    short age;
    String Mail;
    String password;
    String paymentMethod;
    @Override
    public Customer get_info() {
        Customer customer = new Customer(Name, age, Mail, password);
        customer.setPaymentMethod(paymentMethod);
        return customer;
    }

}
