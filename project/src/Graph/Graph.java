package Graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Graph {
    private int citiesNum, roadNum;
    String name;
    private HashMap <String, AdjacencyList> map = new HashMap<>();

    // Constructors & destrcutor
    public Graph() {
        roadNum = 0;
        citiesNum = 0;
        this.name = "Default Name";
    }
    public Graph(String name) {
        new Graph();
        this.name = name;
    }

    // Getters
    public int getEdgeNum() {
        return roadNum;
    }
    public int getVertexNum() {
        return citiesNum;
    }
    public int getOutEdgesNumber(String city) {
        int counter = 0;
        AdjacencyList adjacents = getOutAdjacents(city);

        for (Road road: adjacents)
            counter++;
        return counter;
    }
    public int getRoadDistance(String city1, String city2) {
        AdjacencyList adjacents = getOutAdjacents(city1);
        for (Road road : adjacents)
            if (road.destination.equals(city2))
                return road.distance;
        return -1;
    }
    public AdjacencyList getOutAdjacents(String city) {
        return (map.get(city));
    }
    public AdjacencyList getInAdjacents(String destination) {
        AdjacencyList adjacents = new AdjacencyList();
        Set<String> Cities = map.keySet();
        for (String city : Cities) {
            // if you found a road from any city to the city we're getting its in-adjacents
            if (roadExists(city, destination))
                // get the distance from that city to city we're getting its in-adjacents
                for (Road road : map.get(city))
                    if (road.destination.equals(destination))
                        adjacents.push(new Road(road));
        }
        return adjacents;
    }
    public AdjacencyList getAdjacents(String city) {
        AdjacencyList inAdjacents = getInAdjacents(city)
                , outAdjacents = getOutAdjacents(city);
        // combining all the adjacents into the in-adjacents excluding duplicates
        for (Road in : inAdjacents) {
            for (Road out : outAdjacents) {
                // if you found an already-existing road, skip
                if (in.destination.equals(out.destination) && in.distance == out.distance)
                    break; // not sure break or continue
                else
                    inAdjacents.push(new Road(out));
            }
        }
        return inAdjacents;
    }

    // Cities
    public void addCity(String city) {
        if (cityExists(city))
            System.out.println("Error: trying to add an already-existent city");

        map.put(city, new AdjacencyList());
        citiesNum++;
        System.out.println("Added " + city + " :)");
    }
    public void deleteCity(String city) {
        if (!cityExists(city)) {
            System.out.println("Error: trytin to delete a non-existing city");
            return;
        }

        AdjacencyList Outs = getOutAdjacents(city);
        for (Road road : Outs)
            deleteRoad(city, road.destination);

        AdjacencyList Ins = getInAdjacents(city);
        for (Road road : Ins)
            deleteRoad(city, road.destination);

        map.remove(city);
        citiesNum--;
    }

    // Roads
    /**
     * adds or edits roads between 2 cities (the order matters)
     * @param city1 source
     * @param city2 destination
     * @param distance the distance
     */
    public void AddOrEditRoad(String city1, String city2, int distance) {
        System.out.println("Adding/Editing road " + city1 + " -> " + city2);

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
        return;
    }

    /**
     * deletes road(s) between 2 cities (regardless the parameters order)
     * @param city1 one city
     * @param city2 the other
     */
    public void deleteRoad(String city1, String city2) {
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

        if(!roadExists(city1, city2)) {
            System.out.println("Error: no such a road!");
            return;
        }

        // 1-way road 1 -> 2
        else if (roadExists(city1, city2) && !roadExists(city2, city1)) {
            deleteDirectedRoad(city1, city2);
            System.out.println("Deleted road " + city1 + " -> " + city2);
        }

        // 1-way road 2 -> 1
        else if (roadExists(city2, city1) && !roadExists(city1, city2)) {
            deleteDirectedRoad(city2, city1);
            System.out.println("Deleted road " + city2 + " -> " + city1);
            return;
        }

        // 2-way road :
        // same distance
        if (getRoadDistance(city1, city2) == getRoadDistance(city2, city1)) {
            deleteRoad(city1, city2);
            deleteRoad(city2, city1);
            System.out.println("Deleted roads " + city1 + " <--> " + city2);
            return;
        }
        // different distances (let the user decide which road to delete)
        else {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("1- delete road " + city1 + " -> " + city2);
                System.out.println("2- delete road " + city2 + " -> " + city1);
                System.out.println("3- delete both roads");

                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        deleteRoad(city1, city2);
                        break;
                    case 2:
                        deleteRoad(city2, city1);
                        break;
                    case 3:
                        deleteRoad(city1, city2);
                        deleteRoad(city2, city1);
                        break;
                    default:
                        System.out.println("Invalid choice!  Try agian");
                        continue;
                }
                break;
            }
        }

        System.out.println("Deletion Done :)");
    }

    /**
     * deletes the road city1 -> city2
     * @param city1 the source
     * @param city2 the destination
     */
    public void deleteDirectedRoad(String city1, String city2) {
        for (Road road : map.get(city1))
            if (road.destination.equals(city2)) {
                map.get(city1).remove(road);
                break;
            }
    }

    // Boolean checkers
    public boolean cityExists(String city) {
        Set<String> Cities = map.keySet();
        for (String c : Cities)
            if (city.equals(c))
                return true;
        return false;
    }
    public boolean roadExists(String city1, String city2) {
        if (!cityExists(city1) || !cityExists(city2))
            return false;
        AdjacencyList adjacents = getOutAdjacents(city1);
        for (Road road : adjacents)
            if (road.destination.equals(city2))
                return true;
        return false;
    }
    public boolean empty() {
        return (map.isEmpty());
    }

    public void display() {
        if (empty()) {
            System.out.println("You don't have any cities in this map yet!");
            return;
        }

        System.out.println("\n\t" + name + " Cities:");
        for (Map.Entry<String, AdjacencyList> entry : map.entrySet()) {
            System.out.println("-> " + entry.getKey());
            for (Road road : entry.getValue())
                System.out.println("\t" + road.distance + " --> " + road.destination);

            System.out.println("\t\t\t=======================================================================");
        }
    }
}
