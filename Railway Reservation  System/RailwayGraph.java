
import java.util.*;

public class RailwayGraph {

    // Adjacency List representation of Graph
    private Map<String, List<String>> graph;

    // Constructor
    public RailwayGraph() {
        graph = new HashMap<>();
    }

    // Add railway station
    public void addStation(String station) {
        graph.putIfAbsent(station, new ArrayList<>());
    }

    // Add connection between two stations
    public void addRoute(String source, String destination) {

        addStation(source);
        addStation(destination);

        graph.get(source).add(destination);
        graph.get(destination).add(source);
    }

    // Display all railway routes
    public void displayRoutes() {

        System.out.println("\n====== RAILWAY ROUTES ======");

        for (String station : graph.keySet()) {
            System.out.println(station + " -> " + graph.get(station));
        }
    }

    // Search route using BFS
    public void searchRoute(String source, String destination) {

        if (!graph.containsKey(source) ||
            !graph.containsKey(destination)) {

            System.out.println("Station not found!");
            return;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> parent = new HashMap<>();

        queue.add(source);
        visited.add(source);

        while (!queue.isEmpty()) {

            String current = queue.poll();

            if (current.equals(destination)) {

                List<String> path = new ArrayList<>();
                String station = destination;

                while (station != null) {
                    path.add(station);
                    station = parent.get(station);
                }

                Collections.reverse(path);

                System.out.println("Route Found: " + path);
                return;
            }

            for (String neighbour : graph.get(current)) {

                if (!visited.contains(neighbour)) {

                    visited.add(neighbour);
                    parent.put(neighbour, current);
                    queue.add(neighbour);
                }
            }
        }

        System.out.println("No route available!");
    }
}
