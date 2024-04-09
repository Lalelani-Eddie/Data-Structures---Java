/*
 * Author: Lalelani Eddie Nene
 * Description: This class represents an edge in a graph with a destination node and weight.
 */

 class Edges {
    // Destination node of the edge
    private Node destination;
    
    // Weight of the edge
    private int weight;

    // Constructor to initialize the destination node and weight of the edge
    public Edges(Node destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    // Getter method to retrieve the destination node of the edge
    public Node getDestination() {
        return destination;
    }

    // Getter method to retrieve the weight of the edge
    public int getWeight() {
        return weight;
    }
}
