public  abstract class Person {
    short ID = 0;
    String Name;
    short Age;
    String Uber_Mail;
    String Uber_Password;
    int RidesCount = 0;

    public Person(String Name, short Age, String Uber_Mail, String Uber_Password) {
        ID++;
        this.Name = Name;
        this.Age = Age;
        this.Uber_Mail = Uber_Mail;
        this.Uber_Password = Uber_Password;
    }
}
