package queues_hw2;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdRandom;

public class Subset {

	public static void main(String[] args) {
		
		int k = Integer.parseInt( args[0] );
		
		// Assemble a linked-list of the read input
		String[] inputs = StdIn.readAllStrings();
		
		// Print k different elements from inputs[]
		StdRandom.shuffle(inputs);
		
		for(int i = 0; i < k; i++)
			System.out.println(inputs[i]);

	}

}
