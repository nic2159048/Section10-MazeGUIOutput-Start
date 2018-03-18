/*
 * Section8Edge 
 * 
 * This class holds the edge data structure that supports the 
 * Section8Graph data structure. The edges are stored as an 
 * unordered pair of integers that map from node to node. Since
 * they are unordered, they represent undirected edges. 
 * 
 */
public class Section8Edge implements Comparable<Section8Edge> {
    private int node1;
    private int node2;

    public Section8Edge(int n1, int n2) {
        node1 = n1;
        node2 = n2;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     * 
     * 
     */
    public String toString() {
        return "    " + node1 + " -- " + node2 + ";";
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     * 
     * Checks if two edges are equal and returns true if they are.  
     * If the two edges are not equal return false. 
     *  
     *  ex) 1 -- 2 == 2 -- 1 is true 
     *      1 -- 3 == 1 -- 3 is true 
     *      1 -- 3 == 1 -- 3 is false  
     */
    @Override 
    public boolean equals(Object e) {
        if (this.node1 == ((Section8Edge) e).node1
                && this.node2 == ((Section8Edge) e).node2) {
            return true; 
        }
        if (this.node2 == ((Section8Edge) e).node1
                && this.node1 == ((Section8Edge) e).node2) {
            return true; 
        } 
        return false; 
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     * 
     * We overrode equals so we need to over ride hashCode. 
     */
    public int hashCode() {
        return (node1 + node2);
    }

    @Override
    public int compareTo(Section8Edge other) {
        if (this.equals(other)) {
            return 0; // this and other are equal 
        }
        else if ((node1 < other.node1)
                || (node1 == other.node1 && node2 < other.node2)) {
            return -1; // this is less than other
        }
        else {
            return 1; // this is greater than other
        }
    }
    
}
