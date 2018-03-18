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
    private int v1;
    private int v2; 

    public Section8Edge(int v, int w) {
        v1 = v;
        v2 = w; 
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     * 
     * 
     */
    public String toString() {
        return "    " + v1 + " -- "+ v2 +";";
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
        if (this.v1 == ((Section8Edge)e).v1 && this.v2 == ((Section8Edge)e).v2) {
            return true; 
        }
        if (this.v2 == ((Section8Edge)e).v1 && this.v1 == ((Section8Edge)e).v2) {
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
        return (v1 + v2); 
    }

    @Override
    public int compareTo(Section8Edge o) {
        int firstPair = this.v1 - o.v1;
        int secondPair = this.v2 - o.v2;
        if (firstPair < 0 || firstPair > 0) {
            return firstPair; 
        }
        else {
            return secondPair; 
        }
    }
    
}
