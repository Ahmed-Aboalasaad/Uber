import java.io.IOException;
import java.util.LinkedList;


public class Main {
    public LinkedList<Driver> driverList  = new LinkedList<>();
    public LinkedList<Customer> customerList  = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        //ConsoleUi CI = new ConsoleUi();
        //CI.startPage();

        Files.ReadDriverData();
    }
}