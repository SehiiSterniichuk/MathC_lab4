import java.util.Collections;
import java.util.Vector;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RRQRDecomposition;
public class Main {
	
	public static double[][] matrixData = {{5,3,1},{3,5,3},{1,3,5}};;
	public static final int max = 1;
	public static final int next = 2;
	public static void main(String[] args) {		
		RealMatrix A = MatrixUtils.createRealMatrix(matrixData);
		Faddeev_leverrier faddeev = new Faddeev_leverrier(matrixData);
		faddeev.solve();
		find_eigenvectors(A);
		QR_algorithm();
		double lambda = max_eigenvalue(A, max);
		next_max_eigenvalue(A, lambda);
	}
	
	public static RealMatrix find_eigenvectors(RealMatrix A) {		
		RealMatrix eigenvectors = Matrix.Eigenvectors(A);
		Matrix.printMatr(eigenvectors, "Eigenvectors");
		return eigenvectors;
	}
	public static double max_eigenvalue(RealMatrix A, int choice) {		
		int lenth = Matrix.lenth(A);
		RealMatrix x = MatrixUtils.createColumnRealMatrix(Matrix.initmasiv(lenth, 1.0));
		if(choice == next) {
			x = MatrixUtils.createColumnRealMatrix(Matrix.initmasiv(lenth, 0.0));
			x.setEntry(lenth - 1, 0, 1.0);
		}
		double lambda = 1.0;
		for(int i = 0; i < lenth * lenth; ++i) {
			x = A.multiply(x);
			lambda = x.getNorm();
			x = x.scalarMultiply(1.0/lambda);
		}
		Matrix.print("\nLargest eigen value " + String.valueOf(lambda) + "\n");
		return lambda;
	}
	public static void next_max_eigenvalue(RealMatrix A , double lambda) {
		int lenth = Matrix.lenth(A);
		RealMatrix V = find_eigenvectors(A);
		RealMatrix V1 = V.getRowMatrix(0);
		RealMatrix square = V1.transpose().multiply(V1);
		RealMatrix A1 = A.subtract(square.scalarMultiply(lambda));
		max_eigenvalue(A1, next);
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
