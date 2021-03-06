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
	private int n;
	private static RealMatrix E;
	
	public Faddeev_leverrier(double[][] matrixData) {
		setMatrix(matrixData);
		b = new Vector<Double>(n);
		E = super.Identity(n);
	}
	public void setMatrix(double[][] matrixData) {
		A = MatrixUtils.createRealMatrix(matrixData);
		this.n = lenth(A);
		if(b != null) b.clear();
	}

	public Vector<Double> NSolve(Vector<Double> b){
		Vector<Double> roots = new Vector(b.size() + 1);
		PolynomialFunction f = new PolynomialFunction(tomasiv(b));
	    LaguerreSolver solver = new LaguerreSolver();
	    Complex[] a = solver.solveAllComplex(tomasiv(b), 0);
	    for(Complex i: a) {
	    	roots.add(i.getReal());
	    }
	    return roots;
	}
	
	public void solve() {
		print("\tStart Faddeev-Leverrier`s method\n");
		my_solve();
	    apache_solve();
	    print("\tEnd Faddeev-Leverrier`s method\n");
	}
	private void my_solve() {
		print("\tMy solve start\n");
		b = my_characteristic(A);
		print("Coefficients of the characteristic equation: \n", b);
		Vector<Double> roots = NSolve(b);
		print("\nCharacteristic roots of a square matrix: \n", roots);
		print("\n\tMy solve end\n\n");
	}
	private Vector<Double> my_characteristic(RealMatrix matrix){
		RealMatrix K = Identity(this.n);
		b.add(-((matrix.multiply(K)).getTrace()));
		b.add(1.0);
		printMatr(K, "K");
		for(int i = 2; i <= n; ++i) {
			K = (matrix.multiply(K)).add(E.scalarMultiply(b.firstElement()));
			b.add(0, matrix.multiply(K).getTrace() * (-1.0/i));
			printMatr(K, "K");
		}
		return b;
	}
	private void apache_solve() {
		Vector<Double> java_roots = Eigenvalues(A);
	    print("Eigenvalues finded by apache lib: \n", java_roots);
	}
	
	
}
