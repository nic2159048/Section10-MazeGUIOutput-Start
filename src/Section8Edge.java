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

     * TODO: Match the string output for edges shown in 

     * the file usage comment in Section8Main and the writeup. 

     */

    public String toString() {

        return "";

    }

    

    

    /*

     * (non-Javadoc)

     * @see java.lang.Object#equals(java.lang.Object)

     * 

     *  TODO: complete this method so that two edges that connect the same two nodes

     *  are equal - return true. If the two edges are not equal return false. 

     *  

     *  ex) 1 -- 2 == 2 -- 1 is true 

     *      1 -- 3 == 1 -- 3 is true 

     *      1 -- 3 == 1 -- 3 is false  

     */

    @Override 

    public boolean equals(Object e) {

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




    /*

     * (non-Javadoc)

     * @see java.lang.Comparable#compareTo(java.lang.Object)

     * 

     *  TODO: complete this method so that two edges can be compared 

     *  for the TreeSet to maintain order. 

     */

    @Override

    public int compareTo(Section8Edge o) {

        return 0; 

    }

    

}
