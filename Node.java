/*
 * Author: Lalelani Eddie Nene
 * Description: This class represents a node in a graph with its id and neighboring nodes.
 */

 import java.util.HashMap;
 import java.util.Map;
 
 class Node {
     private final String id;
     private final Map<Node, Integer> neighbors;
 
     public Node(String id) {
         this.id = id;
         this.neighbors = new HashMap<>();
     }
 
     public String getId() {
         return id;
     }
 
     // Add a neighboring node with the given weight
     public void addNeighbor(Node neighbor, int weight) {
         neighbors.put(neighbor, weight);
     }
 
     // Get all neighboring nodes with their weights
     public Map<Node, Integer> getNeighbors() {
         return neighbors;
     }
 
     // Check if a node with the given id is a neighbor
     public boolean isNeighbor(String id){
         for (Map.Entry<Node, Integer> entry : neighbors.entrySet()) {
             if (entry.getKey().getId().compareTo(id) == 0) {
                 return true;
             }
         }
         return false;
     }
 
     // Get the neighboring node with the given id
     public Map.Entry<Node, Integer> getNeighbor(String id){
         for (Map.Entry<Node, Integer> entry : neighbors.entrySet()) {
             if (entry.getKey().getId().compareTo(id) == 0) {
                 return entry;
             }
         }
         return null;
     }
 }
 