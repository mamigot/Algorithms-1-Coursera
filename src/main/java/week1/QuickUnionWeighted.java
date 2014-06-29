package week1;
import shortcuts.print;

public class QuickUnionWeighted {
	
	private static int[] ids;
	private static int[] weights;
	private static int size = 10;
	
	private static void fillIDs(){
		ids = new int[size];
		weights = new int[size];
		
		for(int i = 0; i < size; i ++){
			ids[i] = i;
			weights[i] = 1;
		}
	}
	
	private static int root(int p){
		
		while(ids[p] != p)
			p = ids[p];
		
		return p;	
	
	}
	
	private static boolean isConnected(int p, int q){
		return root(p) == root(q);
	}

	private static void union(int p, int q){
		
		int i = root(p);
		int k = root(q);
		
		if(i == k) return;
		
		if(weights[i] < weights[k]){
			ids[i] = k;
			weights[k] += weights[i];
		}else{
			ids[k] = i;
			weights[i] += weights[k]; 
		}
		
	}
	
	public static void main(String[] args){
		
		fillIDs();
		
		union(4, 0);
		union(0, 3);
		union(4, 5);
		union(6, 2);
		union(0, 7);
		union(9, 8);
		union(9, 2);		
		union(4, 6);
		union(1, 9);
		
		
		//print.ln(isConnected(5, 3) );
		
		print.ln(ids);
		//print.ln(weights);
		
	}

}
