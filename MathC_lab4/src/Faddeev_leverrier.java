import java.util.Vector;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.solvers.LaguerreSolver;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class Faddeev_leverrier extends Matrix{
	private RealMatrix A;
	private Vector<Double> b;
	private RealMatrix K;
	private int n;
	private static RealMatrix E;
	
	public Faddeev_leverrier(double[][] matrixData) {
		A = MatrixUtils.createRealMatrix(matrixData);
		this.n = lenth(A);
		b = new Vector<Double>(n);
		E = super.Identity(n);

	}
	
	private Vector<Double> my_characteristic(RealMatrix matrix){
		boolean akj = this.n == 3;
		assert (akj);
		K = Identity(this.n);
		b.add(-((matrix.multiply(K)).getTrace()));
		b.add(1.0);
		print();
		for(int i = 2; i <= n; ++i) {
			K = (matrix.multiply(K)).add(E.scalarMultiply(b.firstElement()));
			b.add(0, matrix.multiply(K).getTrace() * (-1.0/i));
			print();
		}
		return b;
	}
	private Vector<Double> NSolve(Vector<Double> b){
		Vector<Double> roots = new Vector(b.size() + 1);
		PolynomialFunction f = new PolynomialFunction(tomasiv(b));
	    LaguerreSolver solver = new LaguerreSolver();
	    Complex[] a = solver.solveAllComplex(tomasiv(b), 0);
	    for(Complex i: a) {
	    	roots.add(i.getReal());
	    }
	    return roots;
	}
	private Vector<Double> Eigenvalues(RealMatrix A){
		Vector<Double> roots = new Vector(b.size());
		EigenDecomposition Eigen = new EigenDecomposition(A);
	    RealMatrix EigenMatr = Eigen.getD();// solve on diagonal
	    for(int i = 0; i < n; ++i) {
	    	roots.add(EigenMatr.getEntry(i, i));
	    }
	    return roots;
	}
	public void solve() {
		b = my_characteristic(A);
		print("Coefficients of the characteristic equation: ", b);
		Vector<Double> roots = NSolve(b);
		print("Characteristic roots of a square matrix: ", roots);
//	    System.out.println(); /// upper my solve
//	    Vector<Double> java_roots = Eigenvalues(A);
//	    print(java_roots);//upper java solve, make in one row
//	    RealMatrix ownvectors = Eigen.getVT();
//	    print(b1);// third task diagonal 
//	    print(ownvectors);//third task own vectors
	}
	
	private void print() {
		System.out.println("\n....Matrix K.....");
		super.print(K);
		System.out.println(".................");
	}
}
