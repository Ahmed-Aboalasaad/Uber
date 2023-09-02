package Uber.Graph;

import java.util.Stack;

/**
 * Has tester functions (automates testing)
 */
public class Testers {
    public static void graphTester() {
        Graph graph = new Graph("Egypt");
        graph.addCity("Cairo");
        graph.addCity("Alex");
        graph.addCity("Mahalla");
        graph.addOrUpdateRoad("Mahalla", "Cairo", 151.6f);
        graph.addOrUpdateRoad("Mahalla", "Cairo", 151.09f);
        graph.addOrUpdateRoad("Cairo", "Mahalla", 151.09f);
        graph.addOrUpdateRoad("Cairo", "Alex", 240f);
        graph.addOrUpdateRoad("Alex", "Cairo", 235f);
        graph.addOrUpdateRoad("Mahalla", "Alex", 99f);
        graph.addOrUpdateRoad("Alex", "Mahalla", 99f);
        /*graph.display();
        graph.deleteCity("Cairo");
        graph.display();*/
        graph.display();
        AdjacencyList outs = graph.getOutAdjacents("Cairo");
        System.out.println("Cairo Outs :");
        for (Road out : outs)
            System.out.println("\t" + out.destination + " " + out.distance);

        AdjacencyList ins = graph.getInAdjacents("Cairo");
        System.out.println("Cairo Ins :");
        for (Road in : ins)
            System.out.println("\t" + in.destination + " " + in.distance);

        AdjacencyList adjacents = graph.getAdjacents("Cairo");
        System.out.println("Cairo Adjacents :");
        for (Road adjacent : adjacents)
            System.out.println("\t" + adjacent.destination + " " + adjacent.distance);

        System.out.println("cities Names: ");
        for (String city : graph.getCitiesNames())
            System.out.println("\t" + city);

        System.out.println("#Out roads of Mahalla: " + graph.getOutRoadsNumber("Mahalla"));

        System.out.println("Alex -> Cairo : " + graph.getRoadDistance("Alex", "Cairo"));

        graph.deleteDirectedRoad("Cairo", "Mahall");
        graph.deleteDirectedRoad("Cairo", "Mahalla");
        graph.display();

        System.out.println("city Mahall exists: " + graph.cityExists("Mahall"));
        System.out.println("city Mahalla exists: " + graph.cityExists("Mahalla"));
        if (graph.roadExists("Cairo", "Mahalla"))
            System.out.println("Road Cairo -> Mahalla exists");
        if (graph.roadExists("Mahalla", "Cairo"))
            System.out.println("Road Mahalla -> Cairo exists");

        graph.addOrUpdateRoad("Alex", "Mahalla", 100);
        graph.display();
        System.out.println(graph.getRoadDistance("Alex", "Mahalla"));
    }
    public static void pathFinderTester() {
        Graph g = new Graph("Letters");
        g.addCity("A");
        g.addCity("B");
        g.addCity("C");
        g.addCity("D");
        g.addCity("E");
        g.addCity("F");
        g.addOrUpdateRoad("A", "B", 2f);
        g.addOrUpdateRoad("A", "C", 4f);
        g.addOrUpdateRoad("B", "C", 1f);
        g.addOrUpdateRoad("B", "D", 7f);
        g.addOrUpdateRoad("C", "D", 3f);
        g.addOrUpdateRoad("C", "A", 1f);
        g.addOrUpdateRoad("D", "E", 2f);
        g.addOrUpdateRoad("E", "F", 5f);
        g.addOrUpdateRoad("F", "A", 1f);
        g.display();
        Stack<Road> path = new Stack<>();
        PathFinder finder = new PathFinder(g, path);
        float distance = finder.find("A", "F");
        System.out.println("shortest distance: " + distance);
        finder.printPath();
    }
}
