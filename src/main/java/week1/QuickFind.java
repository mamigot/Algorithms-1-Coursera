package week1;
import shortcuts.print;

public class QuickFind {

	private static int[] ids;
	private static int size = 10;
		
	private static void fillIDs(){
		ids = new int[size];
		
		for(int i = 0; i < size; i ++)
			ids[i] = i;
	}
	
	private static boolean isConnected(int p, int q){
		return ids[p] == ids[q];
	}

	private static void union(int p, int q){
		
		int pickMe = ids[p];
		int changeToThis = ids[q];
		
		for(int i = 0; i < size; i++){
			if(ids[i] == pickMe) ids[i] = changeToThis;
		}
		
	}
	
	
	public static void main(String[] args) {

		fillIDs();
		
		union(8,9);
		union(8,1);
		union(1,3);
		union(5,1);
		union(3,6);
		union(0,1);
		
		print.ln(ids);
		
	}

}
