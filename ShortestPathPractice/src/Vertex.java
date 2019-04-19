import java.util.HashSet;
import java.util.Set;

public class Vertex {
    private String id;
    private Set<WeightedEdge> adj = new HashSet<>();

    public Vertex(String id) {
        this.id = id;
    }

    public void addEdge(WeightedEdge e) {
        adj.add(e);
    }

    // getters and setters
    public String getID() {
        return id;
    }

    public Iterable<WeightedEdge> getAdj() {
        return adj;
    }

}
