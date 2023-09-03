package Uber;

import Uber.Graph.Graph;
import Uber.User.Driver;

public class Global {
    public static Graph graph = new Graph();
    public static Driver currentTripDriver;
    public static ConsoleUi consoleUI = new ConsoleUi();
}