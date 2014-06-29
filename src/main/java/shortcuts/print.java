package shortcuts;

public final class print {
	
	public static void ln(){
		System.out.println();
	}
	
	public static void ln(Object arg){
		System.out.println(arg.toString());
	}
	
	public static void ln(String arg){
		System.out.println(arg);
	}

	public static void ln(boolean arg){
		System.out.println(arg);
	}
	
	public static void ln(int arg){
		System.out.println(arg);
	}
	
	public static void ln(float arg){
		System.out.println(arg);
	}
	
	public static void ln(double arg){
		System.out.println(arg);
	}
	
	public static void ln(int[] arg){
		System.out.print("[ ");
		
		for(int a:arg)
			System.out.print(a + " ");
		
		System.out.println("]");
	}
	
	public static void ln(float[] arg){
		System.out.print("[ ");
		
		for(float a:arg)
			System.out.print(a + " ");
		
		System.out.println("]");
	}
	
	public static void ln(double[] arg){
		System.out.print("[ ");
		
		for(double a:arg)
			System.out.print(a + " ");
		
		System.out.println("]");
	}

	public static void ln(int[][] arg){
		// Print square multidimensional array
		int len = arg.length;
		
		for(int i = 0; i < len; i++){
			System.out.print("[");
			
			for(int j = 0; j < len; j++){
				if( j == len-1)
					System.out.print("\t" + arg[i][j] + "\t");
				else
					System.out.print("\t" + arg[i][j] + "\t");
			}
			
			System.out.println("]");
		}
	}
	
	public static void ln(boolean[][] arg){
		// Print square multidimensional array
		int len = arg.length;
		
		for(int i = 0; i < len; i++){
			System.out.print("[");
			
			for(int j = 0; j < len; j++){
				if( j == len-1)
					System.out.print("\t" + arg[i][j] + "\t");
				else
					System.out.print("\t" + arg[i][j] + "\t");
			}
			
			System.out.println("]");
		}
	}
}
