import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Section1Main {

    public static void main(String[] args) throws FileNotFoundException {
       
        // TODO: write Scanner declaration here
        Scanner in = null;
        
        //Stores the HashMap created by countDepartures for later printing
        HashMap<String, Integer> flights = countDepartures(in);
       
        //Look at write up to understand the alphabetical sorting 
        ArrayList<String> airportsSorted = new ArrayList<String>(
                flights.keySet());
        Collections.sort(airportsSorted);

        //TODO: Loop over HashMap and print 

    }

    /*
    * function: countDepartures(Scanner in) 
    *
    * parameter in: in Scanner is used to loop over input file
    * 
    * This function loops over the input file to create a HashMap 
    * that maps from airport codes to number of flights.
    * 
    * returns: HashMap<String, Integer> that was created 
    */
    public static HashMap<String, Integer> countDepartures(Scanner in) {

        HashMap<String, Integer> airportToNumFlights = new HashMap<String, Integer>();

        // TODO: HashMap write code here
        

        return airportToNumFlights;
    }
}
