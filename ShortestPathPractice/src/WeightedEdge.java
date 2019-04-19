// store weighted edge

public class WeightedEdge implements Comparable<WeightedEdge> {
    String v, w;
    int length;

    public WeightedEdge(String v, String w, int length) {
        this.v = v;
        this.w = w;
        this.length = length;
    }

    // getters and setter
    public int getLength() {
        return length;
    }
    // return v by default
    public String either() {
        return v;
    }
    // return the other vertex when one vertex is known
    public String other(String vertex) {
        if (vertex.equals(v)) return w;
        else if (vertex.equals(w)) return v;
        else throw new RuntimeException("Inconsistent edge");
    }

    public int compareTo(WeightedEdge that) {
        if (this.getLength() < that.getLength()) return -1;
        else if (this.getLength() > that.getLength()) return 1;
        else return 0;
    }

    @Override
    public String toString() {
        return String.format("%s-%s %d", v, w, length);
    }
}
