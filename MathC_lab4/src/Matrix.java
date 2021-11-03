import java.util.Vector;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public abstract class Matrix {
	
	public double[] tomasiv(Vector<Double> vect){
		double[] masiv = new double[vect.size()];
		for(int i = 0; i < vect.size(); ++i) {
			masiv[i] = vect.elementAt(i);
		}
		return masiv;
		
	}
	public int lenth(RealMatrix A) {
		return Math.max(A.getRowDimension(), A.getColumnDimension());
	}
	public RealMatrix Identity(int size) {
		return MatrixUtils.createRealIdentityMatrix(size);
	}
	public void print(double[][] matrix, int row, int col) {
		for(int i = 0; i < row; ++i) {
			for(int j = 0; j < col; ++j) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void print(RealMatrix matrix) {
		print(matrix.getData(), matrix.getRowDimension(), matrix.getColumnDimension());
	}

	public void print(RealVector vec) {
		System.out.println("\nVector`s values: ");
		for(int i = 0; i < vec.getDimension(); ++i) {
			System.out.print(vec.getEntry(i) + " ");
		}
		System.out.println();
	}
	
	public void print(String str) {
		System.out.println(str);
	}
	public void print(Vector<Double> vec) {
		for(Double i: vec) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	public void print(String str, Vector<Double> vec) {
		System.out.print(str);
		print(vec);
	}

	public double[][] init(int row, int col) {
		double[][] matrix = new double[row][];
		for(int i = 0; i < row; ++i) {
			matrix[i] = new double[col];
			for(int j = 0; j < col; ++j) {
				matrix[i][j] = 0;
			}
		}
		return matrix;
	}
}
