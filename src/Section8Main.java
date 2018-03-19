import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 * KEY
 * 
 * @author AlexandraGilliland
 *         This class reads in the input file described below and creates a
 *         graph from it. Beginning
 *         lines that are comments are ignored and nodes that map to themselves
 *         (1 to 1 or 4 to 4)
 *         are ignored. A .dot file is created and can be opened as a visual
 *         representation of the graph.
 * 
 *         Input - Unknown number of comment lines at the beggining, they start
 *         with %
 *         - first line of input file is row, column and number of entries
 *         - all lines after that are mapping entries, representing a row and
 *         column value
 * 
 *         Example input file:
 * 
 *         %%MatrixMarket matrix coordinate pattern symmetric
 *         %-------------------------------------------------------------------------------
 *         % Demo file
 *         %-------------------------------------------------------------------------------
 *         5 5 7
 *         1 1
 *         3 3
 *         1 4
 *         4 2
 *         4 4
 *         4 5
 *         5 5
 * 
 *         Example Output
 *         graph {
 *              1;
 *              2;
 *              3;
 *              4;
 *              5;
 *         1 -- 4;
 *         4 -- 2;
 *         4 -- 5;
 *         }
 * 
 */
public class Section8Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(args[0]));
        in.nextLine();
        in.nextLine(); 
        while (in.hasNext("%")) {
           in.nextLine();
        }
        in.nextLine();
        
        String fileInfo = in.nextLine(); 
        String[] fileInfoSplit = fileInfo.split(" "); 
        int numNodes = Math.max(Integer.parseInt(fileInfoSplit[0].trim()),
                Integer.parseInt(fileInfoSplit[1].trim()));
        
        int entries = Integer.parseInt(fileInfoSplit[2].trim()); 

        Section8Graph graph = new Section8Graph(numNodes); 

        for (int i = 0; i < entries; i++) {            
            int r = in.nextInt();
            int c = in.nextInt();
            if (r != c) {
                graph.add(r, c);

            }
        }
        PrintWriter writer = new PrintWriter("graph.dot"); 
        
        writer.println(graph.toString());
        writer.close();
        System.out.println(graph.toString());
    }
    
}
