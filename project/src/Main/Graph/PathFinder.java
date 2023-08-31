package Main.Graph;

import java.util.*;

public class PathFinder {
    Graph graph;
    Comparator<Road> roadComparator = new Comparator<Road>() {
        public int compare(Road road1, Road road2) {
            return (Float.compare(road1.distance, road2.distance));
        }
    };
    PriorityQueue<Road> unknownNodes = new PriorityQueue<>(roadComparator);
    HashMap<String, Float> shortestPath = new HashMap<>();
    HashMap<String, String> previousNode = new HashMap<>();
    Stack<Road> path = new Stack<>();

    PathFinder(Graph graph, Stack<Road> path) {
        this.graph = graph;
        this.path = path;
    }

    float find(String source, String destination) {

    }

    /**
     * finds the shortest path between 2 nodes in a graph
     * @param startNode source
     * @param targetNode destination
     * @return the total distance of the path
     */
    private float Dijkstra(String startNode, String targetNode) {
        unknownNodes.add(new Road(startNode, 0));
        for (String city : graph.getCitiesNames()) { // initial values for Dijkstra's table
            shortestPath.put(city, Float.MAX_VALUE);
            previousNode.put(city, "");
        }
        shortestPath.put(startNode, 0f);

        while (!unknownNodes.isEmpty()) {
            // the city with the shortest path in the table till now
            String nearestCity = unknownNodes.poll().destination;
            // possible roads from the nearest city
            AdjacencyList outAdjacents = graph.getOutAdjacents(nearestCity);

            for (Road road : outAdjacents) {
                String adjacentName = road.destination;
                float distnace = road.distance;
                if (shortestPath.get(adjacentName) > shortestPath.get(nearestCity) + distnace) {
                    shortestPath.put(adjacentName, shortestPath.get(nearestCity) + distnace);
                    previousNode.put(adjacentName, nearestCity);
                    unknownNodes.add(new Road(adjacentName, shortestPath.get(adjacentName)));
                }
            }
        }

        // no path to the target node
        if (shortestPath.get(targetNode) == Float.MAX_VALUE)
            return -1f;
        path = fillPath(graph, startNode, targetNode, previousNode);
        return shortestPath.get(targetNode);
    }

    fillPath (Graph graph, String StartNode, String targetNode, HashMap<String, String> previousNode) {

    }


}
