package week1;
import shortcuts.print;

public class QuickUnion {
	
	private static int[] ids;
	private static int size = 10;
	
	private static void fillIDs(){
		ids = new int[size];
		
		for(int i = 0; i < size; i ++)
			ids[i] = i;
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
	
		//set p's root to point to q's root
		ids[p] = root(q);
			
	}
	
	public static void main(String[] args){
		
		fillIDs();
		
		union(4, 3);
		union(3, 9);
		union(2, 5);
		union(5, 9);
		
		print.ln(isConnected(5, 3));
		
		print.ln(ids);
		
	}

}
