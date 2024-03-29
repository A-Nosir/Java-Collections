package comp2402a1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeSet;
import java.util.SortedSet;

public class Part6 {
	
	/**
	 * Your code goes here - see Part0 for an example
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
            // A sophisticated comparator
            // A sophisticated comparator
            SortedSet<String> s = new TreeSet<>();
            SortedSet<String> tailSet;
                    
            boolean isSuffix; //keeps track if any entry has line as a suffix

            for (String line = r.readLine(); line != null; line = r.readLine()) {
                //If set is not empty we do the checking logic          
                isSuffix=false;    //Reset to false for next line
                tailSet = s.tailSet(line); //Find Set that is larger than line
                
                if(!tailSet.isEmpty()){
                    //if the first element does not end with line,with the way it is sorted, means the other strings don't matter
                    if(tailSet.first().endsWith(line) && !line.isEmpty()){   //checks if the line is an empty line. (They count as a suffix, and that violates requiremtn)
                        isSuffix = true;

                    }
                }
                //if i is equal to size-1, that means all entries were checked and none of them are suffixes
                if(isSuffix == false){
                    w.println(line);
                    s.add(line);
                }
                
          //      w.println(tailSet.toString());
            }
           
        //  w.println(s.toString());
}
        

	/**
	 * The driver.  Open a BufferedReader and a PrintWriter, either from System.in
	 * and System.out or from filenames specified on the command line, then call doIt.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader r;
			PrintWriter w;
			if (args.length == 0) {
				r = new BufferedReader(new InputStreamReader(System.in));
				w = new PrintWriter(System.out);
			} else if (args.length == 1) {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(System.out);				
			} else {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(new FileWriter(args[1]));
			}
			long start = System.nanoTime();
			doIt(r, w);
			w.flush();
			long stop = System.nanoTime();
			System.out.println("Execution time: " + 10e-9 * (stop-start));
		} catch (IOException e) {
			System.err.println(e);
			System.exit(-1);
		}
	}
}

