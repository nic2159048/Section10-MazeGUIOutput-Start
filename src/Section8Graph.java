import java.util.TreeSet;
/*
 * Section8Graph 
 * 
 * This class holds the graph data structure that stores the edges. 
 * The edges are stored in a TreeSet so they can be kept in 
 * lexigraphical order and only unique edges are included. 
 * 
 * This class relies on the Section8Edge class. 
 */
public class Section8Graph {
   
    private TreeSet<Section8Edge> allEdges;
    private int totalNodes;
    
    public Section8Graph(int numNodes) {
        allEdges = new TreeSet<Section8Edge>(); 
        totalNodes = numNodes;
    }

    /*
     * Inserts an edge into the graph. 
     */
    public void add(int x, int y) {
        allEdges.add(new Section8Edge(x, y));
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     * 
     * Creates a String in the specified .dot format to 
     * output a graph. 
     * 
     */
    public String toString() {

        String str = "graph {\n";
        for (int i = 1; i <= totalNodes; i++) {
            str += "        " + i + ";\n";
        }
        for (Section8Edge edge : allEdges) {
            str += edge.toString();
            str += "\n";
        }
        str += "}";
        return str; 
    }

}
