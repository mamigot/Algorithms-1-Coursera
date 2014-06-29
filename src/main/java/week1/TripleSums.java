package week1;

import java.util.ArrayList;
import java.util.List;

import shortcuts.print;

public class TripleSums {

	private static Integer[] arrayify(int a, int b, int c){
		Integer[] arr = new Integer[3];
		arr[0] = a;
		arr[1] = b;
		arr[2] = c;
		return arr;
	}
	
	private static int[] genArrayInts(int len){
		int[] nums = new int[len];
		int counter = 0;
		for(int i = 0; i < nums.length; i++){
			nums[i] = (i % 2 == 0) ? counter*-1 : counter;
			counter++;
		}
		
		return nums;
	}
	
	private static void findTriples(int[] h){
		int len = h.length;
		int tot = 0;
		
		List<Integer[]> buddies = new ArrayList<Integer[]>();
		
		for(int i = 0; i < len; i++)
			for(int j = i + 1; j < len; j++)
				for(int k = j + 1; k < len; k++)
					if(h[i] + h[j] + h[k] == 0){
						tot++;
						buddies.add(arrayify(h[i], h[j], h[k]));
					}
		
		print.ln("Number of triples: " + tot);
		
		
		for(int n = 0; n < buddies.size(); n++){
			for(int a = 0; a < buddies.get(0).length; a++){
				print.ln(buddies.get(n)[a]);
			}
			print.ln();
		}
		
		
	}
	
	public static void main(String[] args) {
		
		int[] nums = genArrayInts(5);
		findTriples(nums);

	}

}
