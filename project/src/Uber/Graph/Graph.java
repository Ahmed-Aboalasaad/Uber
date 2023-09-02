package Uber.Graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A graph(map) that you can add/delete cities/roads on.
 */
public class Graph {
    private int citiesNum, roadNum;
    private String name;
    private HashMap <String, AdjacencyList> map;

    // Constructors
    /**
     * Initialize a graph object
     */
    public Graph() {
        this.name = "Default Name";
        roadNum = 0;
        citiesNum = 0;
        map = new HashMap<>();
    }

    /**
     * Initialize a graph object with a custom map name
     * @param name the map name
     */
    public Graph(String name) {
        new Graph();
        this.name = name;
    }

    // Getters
    /**
     * Get the number of roads in the map
     * @return number of roads in the map
     */
    public int getRoadsNumber() {
        return roadNum;
    }

    /**
     * Get the number of cities in the map
     * @return number of cities in the map
     */
    public int getCitiesNum() {
        return citiesNum;
    }

    /**
     * Get the names of all the cities in the map
     * @return a String Set containing all the city names in the map
     */
    public Set<String> getCitiesNames() {
        return (map.keySet());
    }

    /**
     * Get the number of roads going out of the city
     * @param city the city to count its out-roads
     * @return int number of roads going out of the city
     */
    public int getOutRoadsNumber(String city) {
        int counter = 0;
        AdjacencyList adjacents = getOutAdjacents(city);

        for (Road road: adjacents)
            counter++;
        return counter;
    }

    /**
     * Get the distance of the road city1->city2 (if it exists)
     * @param city1 the starting city
     * @param city2 the destination city
     * @return float distance of the road city1->city2 (if it exists)
     */
    public float getRoadDistance(String city1, String city2) {
        AdjacencyList adjacents = getOutAdjacents(city1);
        for (Road road : adjacents)
            if (road.destination.equals(city2))
                return road.distance;
        return -1;
    }

    /**
     * Get all the roads going out from the given city
     * @param city the city to get its out-roads
     * @return a LinkedList of out-roads (adjacency list) of the given city
     */
    public AdjacencyList getOutAdjacents(String city) {
        return (map.get(city));
    }

    /**
     * Get all the roads going in the given city
     * @param destination the city to get its in-rods
     * @return a LinkedList of in-roads of the given city
     */
    public AdjacencyList getInAdjacents(String destination) {
        AdjacencyList inAdjacents = new AdjacencyList();
        Set<String> Cities = map.keySet();
        for (String city : Cities) {
            // if you found a road from any city to the city we're getting its in-adjacents
            if (roadExists(city, destination))
                // get the distance from that city to city we're getting its in-adjacents
                for (Road road : map.get(city))
                    if (road.destination.equals(destination))
                        inAdjacents.push(new Road(city, road.distance));
        }
        return inAdjacents;
    }

    /**
     * Get all the roads that connect the given city with
     * other cities excluding repeated roads (2-way same-distance roads)
     * @param city the city to get its adjacents
     * @return a LinkedList of all the in & out Roads connected to this city
     */
    public AdjacencyList getAdjacents(String city) {
        AdjacencyList inAdjacents = getInAdjacents(city)
                , outAdjacents = getOutAdjacents(city),
                adjacents = new AdjacencyList();
        boolean repeated;
        for (Road in : inAdjacents)
            adjacents.push(in);
        for (Road out : outAdjacents) {
            repeated = false;
            for (Road adjacent : adjacents)
                if (out.destination.equals(adjacent.destination) && out.distance == adjacent.distance) {
                    repeated = true;
                    break;
                }
            if (!repeated)
                adjacents.push(out);
        }
        return adjacents;
    }

    // Cities
    /**
     * Add a city to the map
     * @param city the city name
     */
    public void addCity(String city) {
        if (cityExists(city))
            System.out.println("Error: trying to add an already-existent city");

        map.put(city, new AdjacencyList());
        citiesNum++;
        System.out.println("Added " + city + " :)");
    }

    /**
     * Delete a city from the map
     * @param city the city name
     */
    public void deleteCity(String city) {
        if (!cityExists(city)) {
            System.out.println("Error: trying to delete a non-existing city");
            return;
        }

        AdjacencyList Ins = getInAdjacents(city);
        for (Road road : Ins)
            deleteDirectedRoad(road.destination, city);

        map.remove(city);
        citiesNum--;
    }

    // Roads
    /**
     * Add or Update roads between 2 cities (the order matters)
     * @param city1 the source city name
     * @param city2 the destination city name
     * @param distance the distance you want to add/update the road with
     */
    public void addOrUpdateRoad(String city1, String city2, float distance) {
        System.out.println("Adding/Updating road " + city1 + " -> " + city2);

        // make sure the cities exist
        if (!cityExists(city1)) {
            System.out.println("Error: " + city1 + " doesn't exist");
            return;
        }
        if (!cityExists(city2)) {
            System.out.println("Error: " + city2 + " doesn't exist");
            return;
        }

        // if the road already exists , just update the distance
        if (roadExists(city1, city2)) {
            for (Road road : map.get(city1))
            {
                if (road.destination.equals(city2))
                {
                    road.distance = distance;
                    break;
                }
            }
            System.out.println("Updated road: " + city1 + " --" + distance + "--> " + city2 + " :)");
            return;
        }

        // no road between them... add it
        map.get(city1).push(new Road(city2, distance));
        System.out.println("Added road: " + city1 + " --" + distance + "--> " + city2 + " :)");
    }

    /**
     * Delete all the road(s) between 2 cities
     * @param city1 one city
     * @param city2 the other
     */
    public void deleteRoads(String city1, String city2) {
        System.out.println("Deleting road (" + city1 + ", " + city2 + ")");

        // make sure the cities exist
        if (!cityExists(city1)) {
            System.out.println("Error: " + city1 + " doesn't exist");
            return;
        }
        if (!cityExists(city2)) {
            System.out.println("Error: " + city2 + " doesn't exist");
            return;
        }

        if(!roadExists(city1, city2))
            System.out.println("Error: no such a road!");
        // 1-way road 1 -> 2
        else if (roadExists(city1, city2) && !roadExists(city2, city1))
            deleteDirectedRoad(city1, city2);
        // 1-way road 2 -> 1
        else if (roadExists(city2, city1) && !roadExists(city1, city2))
            deleteDirectedRoad(city2, city1);
        // 2-way road :
        else {
            deleteDirectedRoad(city1, city2);
            deleteDirectedRoad(city2, city1);
        }
    }

    /**
     * Delete the road city1 -> city2 (the order matters)
     * @param city1 the source city name
     * @param city2 the destination city name
     */
    public void deleteDirectedRoad(String city1, String city2) {
        for (Road road : map.get(city1))
            if (road.destination.equals(city2)) {
                map.get(city1).remove(road);
                System.out.println("Deleted Road: " + city1 + " -> " + city2);
                return;
            }
        System.out.println("Failed to delete Road: " + city1 + " -> " + city2);
    }

    // Boolean checkers
    /**
     * Check if the given city exists
     * @param city the city name
     * @return 1 if it exists, 0 otherwise
     */
    public boolean cityExists(String city) {
        Set<String> Cities = map.keySet();
        for (String c : Cities)
            if (city.equals(c))
                return true;
        return false;
    }

    /**
     * Check if the road city1 -> city2 exists (the order matter)
     * @param city1 the source city name
     * @param city2 the destination city name
     * @return 1 if it exists, 0 otherwise
     */
    public boolean roadExists(String city1, String city2) {
        if (!cityExists(city1) || !cityExists(city2))
            return false;
        AdjacencyList adjacents = getOutAdjacents(city1);
        for (Road road : adjacents)
            if (road.destination.equals(city2))
                return true;
        return false;
    }

    /**
     * Check if the map is empty
     * @return 1 if the map is empty, 0 otherwise
     */
    public boolean empty() {
        return (map.isEmpty());
    }

    /**
     * Display all the cities and the possible roads out of them
     */
    public void display() {
        if (empty()) {
            System.out.println("You don't have any cities in this map yet!");
            return;
        }

        System.out.println("\n\t  ==== " + name + " Cities ====");
        for (Map.Entry<String, AdjacencyList> entry : map.entrySet()) {
            System.out.println("-> " + entry.getKey());
            for (Road road : entry.getValue())
                System.out.println("\t" + road.destination + " --> " + road.distance);

            System.out.println("==================================");
        }
    }
}
