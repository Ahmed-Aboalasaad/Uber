package Uber;

import Uber.Graph.Graph;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Load
        DriverSavedData.ReadDriverData();
        CustomerSavedData.ReadCustomerData();
        ReservedRidesData.ReadBusrideData();


        Global.consoleUI.mainMenu();

        // Save
        CustomerSavedData.WriteCustomerData();
        DriverSavedData.WriteDriverdata();
        ReservedRidesData.WriteBusridedata();
    }
}