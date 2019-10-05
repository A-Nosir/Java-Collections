package comp2402a1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Part7 {
	
	/**
	 * Your code goes here - see Part0 for an example
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
            // Your code goes here - see Part0 for an example
            //Biggest 1000 lines. Use to check and update what the biggest 1000 lines are. can read threshold from head because smallest priority == smallest String
            PriorityQueue<String> sBig = new PriorityQueue<>();     
            
            //smallest 1000 lines. Use to check and update what the smalles 1000 lines are. can read threshold from head because smallest priority == biggest string
            PriorityQueue<String> sSmall = new PriorityQueue<>(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    // Define comparing logic here
                    return -1 * o1.compareTo(o2);//multiply by -1 to flip the logic and make head the biggest
                }
            });   
            
            int Thresholds = 2000;        //sum of thresholds 1000 smallest + 1000 biggest
            int ThresholdsCounter = 0;      
               
            //Populate with first 2000 lines
            for (String line = r.readLine(); line != null; line = r.readLine()) {
                if(ThresholdsCounter < Thresholds){ 
                //populatewith firt 2000
                    if (sBig.isEmpty()){
                        sBig.add(line);
                    }
                    else{
                        if(sBig.peek().compareTo(line) < 0){
                            sBig.add(line);
                        }
                        else if(sSmall.isEmpty()){
                            sSmall.add(line);
                        }
                        else if(sBig.peek().compareTo(line) == 0){
                            sBig.add(line);
                        }
                        else if(sBig.peek().compareTo(sSmall.peek()) == 0){
                            sBig.add(sSmall.remove());
                            sSmall.add(line);
                        }
                        else{
                            sSmall.add(line);
                        }   
                    }

                    ThresholdsCounter++;
                }
                else if(ThresholdsCounter == Thresholds){
                    //Balance first 2000 lines
                     if(sBig.size() > Thresholds/2){
                         while(sBig.size() != Thresholds/2){
                             sSmall.add(sBig.poll());
                         }
                     }
                     else if(sSmall.size() > Thresholds/2){
                         while(sSmall.size() != Thresholds/2){
                             sBig.add(sSmall.poll());
                         }
                     }
                    //deal with current line
                    if(sBig.peek().compareTo(line) > 0 && sSmall.peek().compareTo(line) < 0){              
                       w.println(line);
                    }
                    else if(sSmall.peek().compareTo(line) > 0){
                       sSmall.add(line);
                       sSmall.poll();
                    }
                    else if(sBig.peek().compareTo(line) < 0){
                        sBig.add(line);
                        sBig.poll();
                    }
                    ThresholdsCounter++;
                }
                else{
                    //print lines if they are smaller than biggest threshold and larger than smallest threshold
                    if(sBig.peek().compareTo(line) > 0 && sSmall.peek().compareTo(line) < 0){              
                       w.println(line);
                    }
                    else if(sSmall.peek().compareTo(line) > 0){
                       sSmall.add(line);
                       sSmall.poll();
                    }
                    else if(sBig.peek().compareTo(line) < 0){
                        sBig.add(line);
                        sBig.poll();
                    }
                }
            } 
       //     w.println(sSmall.toString());
       //     w.println(sBig.toString());
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
