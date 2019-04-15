import edu.princeton.cs.algs4.TrieST;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Graph for storing all of the intersection (vertex) and road (edge) information.
 * Uses your GraphBuildingHandler to convert the XML files into a graph. Your
 * code must include the vertices, adjacent, distance, closest, lat, and lon
 * methods. You'll also need to include instance variables and methods for
 * modifying the graph (e.g. addNode and addEdge).
 *
 * @author Alan Yao, Josh Hug
 */
public class GraphDB {
    /** Your instance variables for storing the graph. You should consider
     * creating helper classes, e.g. Node, Edge, etc. */

    public final Map<String, Integer> bound = new HashMap<>();  // bound for store bound info ('minlat', 'maxlat', 'minlon', 'maxlon')
    public Map<Long, Vertex> verticesMap  = new HashMap<>();     // vertex will be 'node' in osm xml file
    public Map<Long, Edge> edgesMap = new HashMap<>();         // edge will be 'way' in osm xml file
    // Trie is a string-symbol table for extended ASCII strings, implemented using a 256-way trie.
    // Trie is good for storing strings, but might be a bit waste of storage space
    public TrieST<Location> locationTrieST = new TrieST<>();
    /**
     * Example constructor shows how to create and start an XML parser.
     * You do not need to modify this constructor, but you're welcome to do so.
     * @param dbPath Path to the XML file to be parsed.
     */
    public GraphDB(String dbPath) {
        try {
            File inputFile = new File(dbPath); // create a java representation of the file according the file (graph database) path;
            FileInputStream inputStream = new FileInputStream(inputFile);
            // GZIPInputStream stream = new GZIPInputStream(inputStream);

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            GraphBuildingHandler gbh = new GraphBuildingHandler(this);
            saxParser.parse(inputStream, gbh);      //how saxParser.parse(FileInputStream, GraphBuildingHandler) works?
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        clean();
    }

    /**
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
        // regex - regular expression, [^a-zA-Z] means any character that is not alphabet - punctuation and capitalization will be ignored
        // then cleaned form of strings will be turned into lower case
    }

    /**
     *  Remove nodes with no connections from the graph.
     *  While this does not guarantee that any two nodes in the remaining graph are connected,
     *  we can reasonably assume this since typically roads are connected.
     */
    private void clean() {
        // YOUR CODE HERE.
        // Iterate through Vertex Map, if one vertex.adj is empty, then remove the vertex from vertex map
        // ??? how to iterate through a HashMap?
        for (long k : vertices()) {       // iterate through all keys in verticesMap (HashMap)
            Vertex v = verticesMap.get(k);          // obtain value (Vertex) according to the key
            if (v.getAdj().isEmpty()) {             // if a vertex has an empty adj() (no adj, not connected at all, remove it from database)
                verticesMap.remove(k);
            }
        }
    }

    /**
     * Returns an iterable of all vertex IDs in the graph.
     * @return An iterable of id's of all vertices in the graph.
     */
    Iterable<Long> vertices() {
        //YOUR CODE HERE, this currently returns only an empty list.
        return verticesMap.keySet();
    }

    /**
     * Returns ids of all vertices adjacent to v.
     * @param v The id of the vertex we are looking adjacent to.
     * @return An iterable of the ids of the neighbors of v.
     */
    Iterable<Long> adjacent(long v) {
        // YOUR CODE HERE

        return verticesMap.get(v).getAdj();
    }

    /**
     * Returns the great-circle distance between vertices v and w in miles.
     * Assumes the lon/lat methods are implemented properly.
     * <a href="https://www.movable-type.co.uk/scripts/latlong.html">Source</a>.
     * @param v The id of the first vertex.
     * @param w The id of the second vertex.
     * @return The great-circle distance between the two locations from the graph.
     */
    double distance(long v, long w) {
        return distance(lon(v), lat(v), lon(w), lat(w));
    }

    static double distance(double lonV, double latV, double lonW, double latW) {
        double phi1 = Math.toRadians(latV);
        double phi2 = Math.toRadians(latW);
        double dphi = Math.toRadians(latW - latV);
        double dlambda = Math.toRadians(lonW - lonV);

        double a = Math.sin(dphi / 2.0) * Math.sin(dphi / 2.0);
        a += Math.cos(phi1) * Math.cos(phi2) * Math.sin(dlambda / 2.0) * Math.sin(dlambda / 2.0);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 3963 * c;
    }

    /**
     * Returns the initial bearing (angle) between vertices v and w in degrees.
     * The initial bearing is the angle that, if followed in a straight line
     * along a great-circle arc from the starting point, would take you to the
     * end point.
     * Assumes the lon/lat methods are implemented properly.
     * <a href="https://www.movable-type.co.uk/scripts/latlong.html">Source</a>.
     * @param v The id of the first vertex.
     * @param w The id of the second vertex.
     * @return The initial bearing between the vertices.
     */
    double bearing(long v, long w) {
        return bearing(lon(v), lat(v), lon(w), lat(w));
    }

    static double bearing(double lonV, double latV, double lonW, double latW) {
        double phi1 = Math.toRadians(latV);
        double phi2 = Math.toRadians(latW);
        double lambda1 = Math.toRadians(lonV);
        double lambda2 = Math.toRadians(lonW);

        double y = Math.sin(lambda2 - lambda1) * Math.cos(phi2);
        double x = Math.cos(phi1) * Math.sin(phi2);
        x -= Math.sin(phi1) * Math.cos(phi2) * Math.cos(lambda2 - lambda1);
        return Math.toDegrees(Math.atan2(y, x));
    }

    /**
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    long closest(double lon, double lat) {
        // YOUR CODE HERE
        // errorhandling - if input (lon, lat) is out of map's boundary - handle the error properly
        double distance = Double.POSITIVE_INFINITY;
        long id = 0;
        for (long k : verticesMap.keySet()) {
            Vertex v = verticesMap.get(k);
            double updatedDistance = distance(lon, lat, lon(k), lat(k));
            if (updatedDistance < distance) {
                distance = updatedDistance;
                id = k;
            }
        }
        return id;
    }

    /**
     * Gets the longitude of a vertex.
     * @param v The id of the vertex.
     * @return The longitude of the vertex.
     */
    double lon(long v) {
        // YOUR CODE HERE
        Vertex vertex = verticesMap.get(v);
        return vertex.getLon();
    }

    /**
     * Gets the latitude of a vertex.
     * @param v The id of the vertex.
     * @return The latitude of the vertex.
     */
    double lat(long v) {
        // YOUR CODE HERE
        Vertex vertex = verticesMap.get(v);
        return vertex.getLat();
    }

    /**
     * class Vertex is the data structure for storing info for each node
     * Long id, for instance, 318886226
     * double lat, lon for latitude and longitude of each node
     * Map<String, String> tags for storing tags info (K, V)
     * List<Long> adj for storing adjacent nodes
     */
    static class Vertex {
        private long id;
        // String name;
        private double lon;
        private double lat;
        private Map<String, String> tags;
        private List<Long> adj;

        Vertex(long id, double lon, double lat) {
            this.id = id;
            this.lon = lon;
            this.lat = lat;
            tags = new HashMap<>();
            this.adj = new ArrayList<>();
            // why ArrayList, not LinkedList here?
            // LinkedList is better at add and remove; ArrayList is better at get and set
        }
        /*
        void setName(String name) {
            this.name = name;
        }
        */

        /**
         * add tag (K, V) in tags
         * @param k
         * @param v
         */
        public void addTag(String k, String v) {
            tags.put(k, v);
        }

        /**
         * store adjacent nodes
         * @param vertexId
         */
        public void connectTo(long vertexId) {
            adj.add(vertexId);
        }

        /**
         * get methods
         */
        public List<Long> getAdj() {
            return adj;
        }
        public double getLon() {
            return lon;
        }
        public double getLat() {
            return lat;
        }
    }

    /**
     * class Edge is the data structure for storing edge element, it includes:
     * Long id, edge's id
     * List<Long> vertexList, a list of nodes on the edge
     *
     */
    static class Edge{
        private long id;        // edge's id
        // private String name;
        private List<Long> vertexList;      // vertex on the edge
        private Map<String, String> tags;   // for storing tags (K, V)

        Edge(long id, List<Long> vertexList) {      // constructor
            this.id = id;
            this.vertexList = vertexList;
            tags = new HashMap<>();
        }
        Edge() {                                    // default constructor
            vertexList = new ArrayList<>();
            tags = new HashMap<>();
        }

        /**
         * add vertex in vertex list
         * @param id
         */
        public void addVertex(Long id) {
            vertexList.add(id);
        }

        /**
        void setName(String name) {
            this.name = name;
        }
         */
        public void addTags(String k, String v) {
            tags.put(k, v);
        }
    }


    static class Location {
        long id;
        String name;
        double lon;
        double lat;
        Location(long id, double lon, double lat, String name) {
            this.id = id;
            this.name = name;
            this.lon = lon;
            this.lat = lat;
        }
    }

    /**
     *
     */
    public void connect(long v, long w) {
        Vertex v_vertex = verticesMap.get(v);
        Vertex w_vertex = verticesMap.get(w);
        v_vertex.connectTo(w);
        w_vertex.connectTo(v);
    }
}
