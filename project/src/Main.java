import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        //reads data so all you can use the list to go through anything you need
        DriverSavedData.ReadDriverData();
        CustomerSavedData.ReadCustomerData();



        ConsoleUi CI = new ConsoleUi();
        CI.startPage();

        //writes the data back into the file so on the next run, everything in the list and everything added is saved
        CustomerSavedData.WriteCustomerData();
        DriverSavedData.WriteDriverdata();
    }
}