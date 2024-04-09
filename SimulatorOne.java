/*
 * Author: Lalelani Eddie Nene
 * Description: This class represents a simulator for a taxi service, where clients need to be transported from their locations to shops.
 */

 import java.io.File;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import java.util.Scanner;
 
 import Graphs.Graph;
 
 public class SimulatorOne {
     public static void main(String[] args) {
         // Initialize the main graph and an auxiliary graph
         Graph mainGraph = new Graph();
         Graph2 auxiliaryGraph = new Graph2();
 
         try {
             // Read input data from file
             File file = new File("test.txt");
             Scanner scan = new Scanner(System.in);
 
             // Get the number of drivers and their details
             int driverCount = Integer.parseInt(scan.nextLine().strip());
             String[][] driverDetails = new String[driverCount][];
             for (int i = 0; i < driverCount; i++) {
                 String driverInfo = scan.nextLine();
                 driverDetails[i] = driverInfo.split(" ");
                 auxiliaryGraph.addNode(driverDetails[i][0]);
             }
 
             // Add edges for drivers' routes
             for (int i = 0; i < driverCount; i++) {
                 for (int j = 1; j < driverDetails[i].length;) {
                     mainGraph.addEdge(driverDetails[i][0], driverDetails[i][j], Integer.parseInt(driverDetails[i][j + 1]));
                     auxiliaryGraph.addEdge(driverDetails[i][0], driverDetails[i][j], Integer.parseInt(driverDetails[i][j + 1]));
                     j = j + 2;
                 }
             }
 
             // Extract shop information
             boolean cannotBeHelped = false;
             int shopCount = Integer.parseInt(scan.nextLine().strip());
             String[] shopList = new String[shopCount];
             shopList = scan.nextLine().split(" ");
 
             // Extract client information
             int clientCount = Integer.parseInt(scan.nextLine().strip());
             String[] clientList = new String[clientCount];
             clientList = scan.nextLine().split(" ");
             String shopClient = "";
 
             // Iterate through each client
             for (String client : clientList) {
                 int shortestPath = 10000;
                 String bestTaxi = "";
                 String printStatement = "";
                 List<String> keys = new ArrayList<>();
                 List<String> keyValues = new ArrayList<>();
 
                 // Iterate through each node in the auxiliary graph to find the closest taxi
                 for (String id : auxiliaryGraph.getDistNodes(shopList).keySet()) {
                     if (client.compareTo(id) != 0) {
                         Map<String, Integer> distances = auxiliaryGraph.dijkstra(id);
                         for (Map.Entry<String, Integer> entry : distances.entrySet()) {
                             if (entry.getKey().compareTo(client) == 0) {
                                 if (shortestPath > entry.getValue()) {
                                     shortestPath = entry.getValue();
                                     bestTaxi = "taxi " + id;
                                     keys.clear();
                                     keyValues.clear();
                                     keyValues.add(printStatement);
                                     keys.add(bestTaxi);
                                 } else if (shortestPath == entry.getValue()) {
                                     bestTaxi = "taxi " + id;
                                     keys.add(bestTaxi);
                                 }
                             }
                         }
                     }
 
                     // Find the closest shop to the client
                     Map<String, Integer> distances = auxiliaryGraph.dijkstra(client);
                     int distToShop = 9999;
                     String shopName = "";
                     String nameStatement = "";
                     List<String> shops = new ArrayList<>();
                     List<String> nameShops = new ArrayList<>();
 
                     for (Map.Entry<String, Integer> entry : distances.entrySet()) {
                         shopName = entry.getKey();
                         for (String s : shopList) {
                             if (s.compareTo(shopName) == 0) {
                                 if (distToShop > entry.getValue()) {
                                     distToShop = entry.getValue();
                                     nameStatement = "shop " + shopName;
                                     shopClient = client;
                                     shops.clear();
                                     nameShops.clear();
                                     shops.add(nameStatement);
                                     nameShops.add(shopClient);
                                 }
                             } else if (distToShop == entry.getValue()) {
                                 nameStatement = "shop " + shopName;
                                 shopClient = client;
                                 shops.add(nameStatement);
                                 nameShops.add(shopClient);
                             }
                         }
                     }
                 }
 
                 // Check if the client cannot be helped
                 if ((keys.size() == 0) || (shopClient.compareTo("") == 0)) {
                     System.out.println("client " + client);
                     System.out.println("cannot be helped");
                     cannotBeHelped = true;
                 }
             }
 
             // If all clients can be helped, proceed with providing taxi and shop information
             if (cannotBeHelped == false) {
                 List<String> taxiInformation = new ArrayList<>();
                 List<String> shopInformation = new ArrayList<>();
                 
                 for (String client : clientList) {
                     System.out.println("client " + client);
 
                     // Getting the taxi or taxies with the shortest path
                     double distance = Double.MAX_VALUE;
                     for (String taxi : shopList) {
                         mainGraph.dijkstra(taxi);
                         double distanceFrom = mainGraph.getDistanceEdge(client);
                         
                         if (distance > distanceFrom) {
                             distance = distanceFrom;
                             taxiInformation.clear();
                             taxiInformation.add(taxi);
                         } else if (distance == distanceFrom) {
                             taxiInformation.add(taxi);
                         }
                     }
                     
                     // Print taxi information
                     for (String each : taxiInformation) {
                         System.out.println("taxi " + each);
                         mainGraph.dijkstra(each);
                         
                         if (mainGraph.hasMultipleSolutions(each, client) == false) {
                             mainGraph.dijkstra(each);
                             mainGraph.printPath(client);
                         } else {
                             mainGraph.dijkstra(each);
                             System.out.println("multiple solutions cost " + (int) mainGraph.getDistanceEdge(client));
                         }
                     }
                     
                     // Getting the nearest shop or shops
                     distance = 99999;
                     for (String shop : shopList) {
                         mainGraph.dijkstra(client);
                         double distanceFrom = mainGraph.getDistanceEdge(shop);
                         
                         if (distance > distanceFrom) {
                             distance = distanceFrom;
                             shopInformation.clear();
                             shopInformation.add(shop);
                         } else if (distance == distanceFrom) {
                             shopInformation.add(shop);
                         }
                     }
                     
                     // Print shop information
                     for (String shopFound : shopInformation) {
                         System.out.println("shop " + shopFound);
                         mainGraph.dijkstra(client);
                         
                         if (mainGraph.hasMultipleSolutions(client, shopFound) == false) {
                             mainGraph.dijkstra(client);
                             mainGraph.printPath(shopFound);
                         } else {
                             mainGraph.dijkstra(client);
                             System.out.println("multiple solutions cost " + (int) mainGraph.getDistanceEdge(shopFound));
                         }
                     }
                 }
             }
         } catch (Exception e) {
             System.out.println("File not found/ there was an error!");
         }
     }
 }
 