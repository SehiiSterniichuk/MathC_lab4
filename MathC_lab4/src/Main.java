import java.util.Vector;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RRQRDecomposition;
public class Main {
	
	public static double[][] matrixData = {{5,3,1},{3,5,3},{1,3,5}};;
	
	public static void main(String[] args) {		
		Faddeev_leverrier faddeev = new Faddeev_leverrier(matrixData);
		faddeev.solve();
		find_eigenvectors();
		QR_algorithm();
	}
	
	public static void find_eigenvectors() {		
		RealMatrix A = MatrixUtils.createRealMatrix(matrixData);
		RealMatrix eigenvectors = Matrix.Eigenvectors(A);
		Matrix.printMatr(eigenvectors, "Eigenvectors");
	}
	public static void QR_algorithm() {
		Vector<RealMatrix> A = new Vector();
		A.add(MatrixUtils.createRealMatrix(matrixData));
		int lenth = Matrix.lenth(A.firstElement());
		System.out.println(lenth);
		for(int i = 0; i < lenth*lenth; ++i) {
			QRDecomposition matr = new QRDecomposition(A.elementAt(i));
			RealMatrix temp = matr.getR().multiply(matr.getQ());
			A.add(temp);
		}
		Matrix.printMatr(A.lastElement(), "QR_method");
		
	}
	
}
