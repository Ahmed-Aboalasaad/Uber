package Main.User;

public  abstract class Person {

    String Name;
    short Age;
    String Uber_Mail;
    String Uber_Password;
    public int RidesCount = 0;

    public Person(){}

    public Person(String Name, short Age, String Uber_Mail, String Uber_Password) {

        this.Name = Name;
        this.Age = Age;
        this.Uber_Mail = Uber_Mail;
        this.Uber_Password = Uber_Password;
    }

    abstract public void form();
}
