public class Main {
	
	public static double[][] matrixData = {{5,3,1},{3,5,3},{1,3,5}};;
		
	public static void main(String[] args) {		
		Faddeev_leverrier faddeev = new Faddeev_leverrier(matrixData);
		faddeev.solve();
	}
	
}
