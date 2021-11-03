import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class Main {
	
	public static double[][] matrixData = {{5,3,1},{3,5,3},{1,3,5}};;
		
	public static void main(String[] args) {		
		Faddeev_leverrier faddeev = new Faddeev_leverrier(matrixData);
		faddeev.solve();
		find_eigenvectors();
	}
	public static void find_eigenvectors() {
		RealMatrix A = MatrixUtils.createRealMatrix(matrixData);
		RealMatrix eigenvectors = Matrix.Eigenvectors(A);
		Matrix.printMatr(eigenvectors, "Eigenvectors");
	}
	
}
