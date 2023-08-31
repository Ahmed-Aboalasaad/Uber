package Graph;

public class Road {
    public String destination;
    public float distance;

    public Road(String source, float distance) {
        this.destination = source;
        this.distance = distance;
    }

    public Road(Road road) {
        this.destination = road.destination;
        this.distance = road.distance;
    }
}