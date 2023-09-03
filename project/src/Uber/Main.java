package Uber;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Stack;

import Uber.Graph.Graph;
import Uber.Graph.PathFinder;
import Uber.Graph.Road;

public class Main {

    public static void main(String[] args) throws IOException {
        // Load
       CustomerDataSaver.ReadCustomerData();
        DriverDataSaver.ReadDriverData();
        RidesLoader.ReadBusrideData();

        fillMap(Global.graph);
        Global.consoleUI.mainMenu();

        // Save
        CustomerDataSaver.WriteCustomerData();
        DriverDataSaver.WriteDriverdata();
        RidesLoader.WriteBusridedata();
    }

    static void fillMap(Graph map) {
        map.addCity("Abbasia");
        map.addCity("Abdo Basha");
        map.addCity("Atba");
        map.addCity("Ramsees");
        map.addCity("Ard El-Maared");
        map.addCity("Nasr City");
        map.addCity("Rehab");
        map.addCity("5th settlement");
        map.addCity("6th Actober");
        map.addCity("Zamalek");
        map.addCity("Maady");
        map.addCity("Mohandseen");
        map.addCity("Marg");
        map.addCity("Doki");
        map.addCity("Faisal");
        map.addOrUpdateRoad("Abbasia", "Abdo Basha", 2f);
        map.addOrUpdateRoad("Abbasia", "Ramsees", 5.5f);
        map.addOrUpdateRoad("Abbasia", "Atba", 3.2f);
        map.addOrUpdateRoad("Atba", "Ramsees", 2.1f);
        map.addOrUpdateRoad("Atba", "Doki", 6f);
        map.addOrUpdateRoad("Doki", "Faisal", 4f);
        map.addOrUpdateRoad("Doki", "Rehab", 6.9f);
        map.addOrUpdateRoad("Maady", "Zamalek", 8f);
        map.addOrUpdateRoad("Maady", "Mohandseen", 4.3f);
        map.addOrUpdateRoad("Marg", "Atba", 8.6f);
        map.addOrUpdateRoad("Faisal", "Marg", 4.3f);
        map.addOrUpdateRoad("Mohandseen", "6th Actober", 6.7f);
        map.addOrUpdateRoad("Zamalek", "Maady", 8f);
        map.addOrUpdateRoad("6th Actober", "Mohandseen", 6.7f);
        map.addOrUpdateRoad("5th settlement", "Nasr City", 8.4f);
        map.addOrUpdateRoad("Nasr City", "5th settlement", 8.4f);
        map.addOrUpdateRoad("Rehab", "Nasr City", 6f);
        map.addOrUpdateRoad("Ard El-Maared", "Ramsees", 3f);
        map.addOrUpdateRoad("Ramsees", "Abbasia", 5.5f);
        map.addOrUpdateRoad("Ramsees", "Nasr City", 2f);
        map.addOrUpdateRoad("Ramsees", "Faisal", 4.5f);
//        Stack<Road> path = new Stack<>();
//        PathFinder finder = new PathFinder(map, path);
//        System.out.println(finder.find("Ramsees", "Faisal"));
    }
}