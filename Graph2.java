import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
class Graph2 {
    private final Map<String, Node> nodes;

    public Graph2() {
        this.nodes = new HashMap<>();
    }

    public void addNode(String id) {
        nodes.put(id, new Node(id));
    }
    public Node getNode(String id){
        return nodes.get(id);
    }
    public Map<String, Node> getDistNodes(String[] id){
        Map<String , Node> distNodes =  new HashMap<>();
        for(String name: id){
            for(Node n : nodes.values()){
                if (name.compareTo(n.getId())==0) {
                    distNodes.put(name, n);
                }
            }
        }
        return distNodes;
    } 
    public Map<String, Node> getNodes(){
        return nodes;
    }
    public void addEdge(String sourceId, String destId, int weight) {
        Node source = nodes.get(sourceId);
        Node dest = nodes.get(destId);
        if (source != null && dest != null) {
            source.addNeighbor(dest, weight);
            // For undirected SecondGraph, uncomment the line below
            // dest.addNeighbor(source, weight);
        } else {
            throw new IllegalArgumentException("Node(s) not found");
        }
    }

    public Map<String, Integer> dijkstra(String startId) {
        Map<String, Integer> distances = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt((Node n) -> distances.getOrDefault(n.getId(), Integer.MAX_VALUE)));
        Set<String> visited = new HashSet<>();

        for (Node node : nodes.values()) {
            if (node.getId().equals(startId)) {
                distances.put(node.getId(), 0);
                pq.offer(node);
            } else {
                distances.put(node.getId(), Integer.MAX_VALUE);
            }
        }

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            visited.add(current.getId());

            for (Map.Entry<Node, Integer> neighborEntry : current.getNeighbors().entrySet()) {
                Node neighbor = neighborEntry.getKey();
                int weight = neighborEntry.getValue();
                if (!visited.contains(neighbor.getId())) {
                    int newDistance = distances.get(current.getId()) + weight;
                    if (newDistance < distances.get(neighbor.getId())) {
                        distances.put(neighbor.getId(), newDistance);
                        pq.offer(neighbor);
                    }
                }
            }
        }

        return distances;
    }
   public String getNodeInPathTo(String source,String destination,int distance){
        int shopToStop = 0;
        String names = "";
        for (Map.Entry<String, Integer> entry : dijkstra(source).entrySet()) {
            shopToStop = entry.getValue();
            if ((entry.getKey().compareTo(source)!=0)) {
                
            
            for (Map.Entry<String, Integer> entryDerivitive : dijkstra(entry.getKey()).entrySet()){
                if ((entryDerivitive.getKey().compareTo(entry.getKey())!=0)) {
                //System.out.println("From source "+source+" to Node "+entry.getKey()+" distance is "+entry.getValue()+" From Node to destination "+destination+ " Distance is "+ entryDerivitive.getValue() );
                if ((entryDerivitive.getKey().compareTo(destination)==0)&&(shopToStop+entryDerivitive.getValue()==distance)) {
                    
                    names += " "+entry.getKey();
            }
            }
        }
            
        }

    }
    
      return names;
   }   
   public int getNumberOfNodeInPathTo(String source,String destination,int distance){
      int shopToStop = 0;
        int number = 0;
        
        for (Map.Entry<String, Integer> entry : dijkstra(source).entrySet()) {
            shopToStop = entry.getValue();
            if ((entry.getKey().compareTo(source)!=0)) {
                
            
            
            
        }

    }
      return number;
   }

   
   public String getNodeInPathTo(String source,String destination,String[] payAttention,int distance,int counter){
        int shopToStop = 0;
        String names = "";
        for(String s:payAttention){
            if((getPathTo(s , destination)+getPathTo(source , s))==distance){
               names+= " "+s;
            }else if((getPathTo(s , destination)+getPathTo(source , s)) < distance){
               names += getNodeInPathTo( s, destination, payAttention, distance, counter + getPathTo(source , s)) ;
            }
        
   }
   return names;
   }
   
   private int getPathTo(String source, String destination){
      for (Map.Entry<String, Integer> entry : dijkstra(source).entrySet()){
          if (entry.getKey().compareTo(destination)==0){
            return entry.getValue();
          }
      }
      return 0;
   }
   
  public boolean hasMultipleSolution(String start, String destination){
      int solutions = 0;
      boolean check = false;
      int distance = getPathTo(start ,destination );
      Map<String , Integer> startSecondGraph = dijkstra(start);
      
      if(nodes.get(start).isNeighbor(destination)){
         if(nodes.get(start).getNeighbor(destination).getValue()==distance){
             solutions++;
         }
      }
      startSecondGraph.remove(start);
      startSecondGraph.remove(destination);
      for(Map.Entry<String, Integer> each : startSecondGraph.entrySet()){
            int toSecondary = getPathTo(start , each.getKey());
            int fromSecondary = getPathTo( each.getKey() , destination );
            if((toSecondary + fromSecondary )==distance){
                solutions++;
            }
      }
      
      if(solutions <= 1){
         check = true;
      }
      
      return check;
  }
  
  public String showMultipleSolutionsToShop(String start, String destination, String[] removeList ){
        int  distance = getPathTo(start, destination );
        String solutions = "";
        Map<String , Integer> startSecondGraph = dijkstra(start);
        for(String s : removeList ){
            startSecondGraph.remove(s);
        }
        startSecondGraph.remove(start);
        startSecondGraph.remove(destination);
        List<String> note = new ArrayList<>();
        List<Integer> noteDistance = new ArrayList<>();
        for(Map.Entry<String, Integer> each : startSecondGraph.entrySet()){
            int toSecondary = getPathTo(start , each.getKey());
            int fromSecondary = getPathTo( each.getKey() , destination );
            int sum = toSecondary + fromSecondary;
            if( sum ==distance){
                solutions+= " "+each.getKey();
            } else if( sum < distance ){
                  note.add(each.getKey());
                  noteDistance.add(each.getValue());
            }
       }  
       for(int i = 0; i < (note.size() - 1); i++){
           for(int k = 1; k <note.size() ; k++){
              int toSecondary = getPathTo(start , note.get(i));
              int toTetiary = getPathTo(note.get(i),note.get(k));
              int fromTetiary = getPathTo(note.get(k),destination);
              int sum = fromTetiary + toTetiary + toSecondary;
              if(sum == distance){
                  solutions += " "+note.get(i)+" "+note.get(k);
              }
           }
           
       }
       return solutions;     
  }
 
   

 
   
}