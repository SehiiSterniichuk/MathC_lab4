import java.util.Vector;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public abstract class Matrix {
	public static final Integer NUM_AFTER_DOT = 4;
	
	public Vector<Double> Eigenvalues(RealMatrix A){
		int lenth = lenth(A);
		Vector<Double> roots = new Vector(lenth);
		EigenDecomposition Eigen = new EigenDecomposition(A);
	    RealMatrix EigenMatr = Eigen.getD();
	    for(int i = 0; i < lenth; ++i) {
	    	roots.add(EigenMatr.getEntry(i, i));
	    }
	    return roots;
	}
	public static RealMatrix Eigenvectors(RealMatrix A){
		RealMatrix kostyl = (new EigenDecomposition(A)).getVT();
		for(int i = 1; i < kostyl.getRowDimension(); ++i) {
			for(int j = 0; j < kostyl.getColumnDimension(); ++j) {
				double temp = -kostyl.getEntry(i, j);
				kostyl.setEntry(i, j, temp);
			}
		}
	    return kostyl;
	}
	private static Vector<String> matrix_toString(RealMatrix matrix) {
		int lenth = lenth(matrix);
		Vector<String> vect = new Vector(lenth);
		double[][] square_masiv = matrix.getData();
		for(int i = 0; i < lenth; ++i) {
			String str = masiv_toString(square_masiv[i], lenth);
			vect.add(str);
		}
		return vect;
	}
	private static String masiv_toString(double[] masiv, int lenth){
		String str = "\t";
		for(int j = 0; j < lenth; ++j) {
			double num = masiv[j];
			if(Math.abs((int)(num*1000)) - Math.abs(((int)num)*1000) == 0) {
				str += String.valueOf(num);
			}
			else {
				str += String.format("%." + NUM_AFTER_DOT +  "f", num);
			}	
			if(j < lenth - 1) str += " ";
		}
		return str;
	}
	private static int widest_row(Vector<String> vect) {
		int max = vect.get(0).length();
		for(int i = 1; i < vect.size(); ++i)
			if(vect.get(i).length() > max)
				max = vect.get(i).length();
		return max;
	}
	public static void printMatr(RealMatrix matrix, String name) {
		Vector<String> vect = matrix_toString(matrix);
		int dots = widest_row(vect);
		String title = "Matrix " + name;
		int title_lenth = title.length();
		dots = Math.max(dots, title_lenth);
		String first = "\t";
		if(title_lenth < dots) {
			int l1 = title_lenth/2;
			int l2 = title_lenth - l1;
			int dot1 = dots/2;
			int dot2 = dots - dot1;
			for(int i = 0; i < dot1 - l1; ++i)
				first += ".";
			first += title;
			for(int i = 0; i < dot2 - l2; ++i)
				first += ".";	
		}	
		print("\n" + first + "\n");
		print_String_Vector(vect);
		print("\t");
		for(int i = 0; i < dots; ++i)
			print(".");
		print("\n");
	}
	public static void print_String_Vector(Vector<String> vect) {
		for(String i: vect) {
			print(i);
			print("\n");
		}
	}
	public static void printMatrOld(RealMatrix matrix, String name) {
		String first = "\t...Matrix " + name + "..";
		int dots = Math.max(lenth(matrix) * (NUM_AFTER_DOT + 3), first.length());
		int lenth_str = first.length();
		if(lenth_str < dots)
			for(int i = 0; (i < dots - lenth_str); ++i)
				first += ".";		
		print("\n" + first + "\n");
		print(matrix);
		print("\t");
		for(int i = 0; i < dots; ++i)
			print(".");
		print("\n");
	}
	
	public double[] tomasiv(Vector<Double> vect){
		double[] masiv = new double[vect.size()];
		for(int i = 0; i < vect.size(); ++i) {
			masiv[i] = vect.elementAt(i);
		}
		return masiv;
		
	}
	public static int lenth(RealMatrix A) {
		return Math.max(A.getRowDimension(), A.getColumnDimension());
	}
	public RealMatrix Identity(int size) {
		return MatrixUtils.createRealIdentityMatrix(size);
	}
	public static void print(double[][] matrix, int row, int col) {
		for(int i = 0; i < row; ++i) {
			print("\t");
			for(int j = 0; j < col; ++j) {
				print(String.format("%." + NUM_AFTER_DOT +  "f", matrix[i][j]) + " ");
			}
			System.out.println();
		}
	}
	
	public static void print(RealMatrix matrix) {
		print(matrix.getData(), matrix.getRowDimension(), matrix.getColumnDimension());
	}

	public void print(RealVector vec) {
		System.out.println("\nVector`s values: ");
		for(int i = 0; i < vec.getDimension(); ++i) {
			System.out.print(vec.getEntry(i) + " ");
		}
		System.out.println();
	}
	
	public static void print(String str) {
		System.out.print(str);
	}
	public void print(Vector<Double> vec) {
		for(Double i: vec) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	public void print(String str, Vector<Double> vec) {
		print(str);
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
	public static Vector<Double> initvect( int size, Double value){
		Vector<Double> vect = new Vector(size);
		for(int i = 0; i < size; ++i) {
			vect.add(value);
		}
		return vect;
	}
	public static double getAbsMax(double[][] mas, int row, int col) {
		double max = max = getAbsMax(mas[0], col);
		for(int i = 0; i < row; ++i) {
			double[] vect = mas[i];
			double tempmax = getAbsMax(mas[i], col);
			if(tempmax > max)
				max = tempmax;
		}
		return max;
	}
	
	public static double getAbsMax(double[] vec, int size) {
		double max = Math.abs(vec[0]);
		for( int i = 0; i < size; ++i) {
			double tempmax = Math.abs(vec[i]);
			if(max < tempmax)
				max = tempmax;
		}
		return max;
	}
	public static double[] initmasiv( int size, Double value){
		double[] mas = new double[size];
		for(int i = 0; i < size; ++i) {
			mas[i] = value;
		}
		return mas;
	}
}
