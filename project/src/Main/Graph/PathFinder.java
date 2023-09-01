package Main.Graph;
import java.util.*;

/**
 * Finds the shortest path between any 2 nodes in the given graph
 * To use it, 1. instantiate it with a graph and a stack of roads to
 * be filled with the detailed path.
 * 2. call the function find() with the source and destination you want.
 * find() will return the total distance of the shortest path.
 * after calling find, you'll find the path filled with the detailed path.
 * You can use find as many times as you want with different sources and
 * destinations within the same graph.
 * @author Ahmed Aboalesaad
 */
public class PathFinder {
    private Graph graph;
    private String startNode, targetNode;
    private Comparator<Road> roadComparator = new Comparator<Road>() {
        public int compare(Road road1, Road road2) {
            return (Float.compare(road1.distance, road2.distance));
        }
    };
    private PriorityQueue<Road> unvisitedNodes;
    // in Dijkstra's, visiting a node is checking its adjacents, updating their
    // distances and previous nodes (if needed)
    private HashMap<String, Float> shortestPath;
    private HashMap<String, String> previousNodes;
    private Stack<Road> path;

    /**
     * initiates a PathFinder object
     * @param graph the graph to find paths within
     * @param path a stack to be filled with roads each time find() is called
     */
    PathFinder(Graph graph, Stack<Road> path) {
        this.graph = graph;
        this.path = path;
    }

    /**
     * finds the shortest path between 2 nodes in a graph using Dijkstra's algorithm.
     * @param startNode source
     * @param targetNode destination
     * @return the total distance of the path
     */
    public float find(String startNode, String targetNode) {
        // reset the fields
        this.startNode = startNode;
        this.targetNode = targetNode;
        unvisitedNodes = new PriorityQueue<>(roadComparator);
        shortestPath = new HashMap<>();
        previousNodes = new HashMap<>();
        path = new Stack<>();

        // initial values for Dijkstra's table
        unvisitedNodes.add(new Road(startNode, 0));
        for (String city : graph.getCitiesNames()) {
            shortestPath.put(city, Float.MAX_VALUE);
            previousNodes.put(city, "");
        }
        shortestPath.put(startNode, 0f);

        // Dijkstra's
        while (!unvisitedNodes.isEmpty()) {
            // the city with the shortest path in the table till now
            String nearestCity = unvisitedNodes.poll().destination;
            // possible roads from the nearest city
            AdjacencyList outAdjacents = graph.getOutAdjacents(nearestCity);

            for (Road road : outAdjacents) {
                String adjacentName = road.destination;
                float distance = road.distance;
                if (shortestPath.get(adjacentName) > shortestPath.get(nearestCity) + distance) {
                    shortestPath.put(adjacentName, shortestPath.get(nearestCity) + distance);
                    previousNodes.put(adjacentName, nearestCity);
                    unvisitedNodes.add(new Road(adjacentName, shortestPath.get(adjacentName)));
                }
            }
        }

        if (shortestPath.get(targetNode) == Float.MAX_VALUE) {
            System.out.println("There is no such a trip possible :\\");
            return -1f;
        }
        fillPath();
        return shortestPath.get(targetNode);
    }

    /**
     * fills the path stack with the detailed roads
     */
    private void fillPath () {
        String  currentNode = targetNode;
        String previousNode = previousNodes.get(currentNode);
        float distance;

        while (currentNode != startNode) {
            distance = graph.getRoadDistance(previousNode, currentNode);
            path.push(new Road(currentNode, distance));
            currentNode = previousNode;
            previousNode = previousNodes.get(currentNode);
        }
    }

    /**
     * pritns the detailed path
     */
    public void printPath() {
        System.out.println(startNode);
        while (!path.empty()) {
            System.out.println(
                    " -> " + path.peek().distance +
                    " -> " + path.peek().destination);
            path.pop();
        }
    }
}
