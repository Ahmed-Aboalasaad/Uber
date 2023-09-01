package Main.User;

/**
 * The Person abstract class serves as a base class for representing individuals in the ride-sharing system.
 * It provides common properties such as name, age, email, password, and a rides count.
 * Subclasses of this class should implement the 'form' method to gather user-specific information.
 */
public  abstract class Person {

   public String Name;
    public short Age;
    public String Uber_Mail;
     public String Uber_Password;
    public int RidesCount = 0;

    /**
     * Default constructor for the Person class.
     */
    public Person(){}

    /**
     * Parameterized constructor for the Person class.
     *
     * @param Name          The name of the person.
     * @param Age           The age of the person.
     * @param Uber_Mail     The email associated with the person.
     * @param Uber_Password The password associated with the person.
     */
    public Person(String Name, short Age, String Uber_Mail, String Uber_Password) {

        this.Name = Name;
        this.Age = Age;
        this.Uber_Mail = Uber_Mail;
        this.Uber_Password = Uber_Password;
    }
    /**
     * Abstract method to be implemented by subclasses.
     * This method is responsible for gathering specific user information.
     */
    abstract public void form();
}
