import Graph.Graph;

public class Main {
    public static void main(String[] args) {
        Graph myGraph = new Graph("Cairo");
        System.out.println(myGraph.getVertexNum());
        System.out.println(myGraph.getEdgeNum());
        System.out.println(myGraph.getOutAdjacents("Cairo"));
    }
}