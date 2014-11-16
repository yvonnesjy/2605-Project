import java.lang.Math;
public class PowerMethod {
	float[][] a;
    float[] x;
    float[] y;
    int n;
	private static final double E_VALUE = 0.00005f;
	//private static float lambda;


    public static void main(String[] args) {
        float[][] a = {{(float)-2, (float)-3}, {(float)-1, (float)0}};
        float[] x = {(float)1, (float)0};
        float[] y = {(float)1, (float)0};
        int n = 50;

        PowerMethod test1 = new PowerMethod(a, x, y, n);
        test1.eigenPower();
    }
    
	public PowerMethod(float[][] a, float[] x, float[] y, int n) {
		this.a = a;
        this.x = x;
        this.y = y;
        this.n = n;
	}

	public void eigenPower() {
		float[] xkMinus1 = x;
		float[] xk = matrixVectorMultiply(a, xkMinus1);
		float lambdaBefore = dotProduct(y, xk) / dotProduct(y, xkMinus1);
        System.out.println(lambdaBefore);
		xkMinus1 = xk;
		xk = matrixVectorMultiply(a, xkMinus1);
		float lambdaAfter = dotProduct(y, xk) / dotProduct(y, xkMinus1);
		int k = 2;
        boolean keepGoing = true;
        float b = lambdaAfter - lambdaBefore;
		while (keepGoing && Math.abs(lambdaAfter - lambdaBefore) > E_VALUE) {
			lambdaBefore = lambdaAfter;
			xkMinus1 = xk;
			xk = matrixVectorMultiply(a, xkMinus1);
			lambdaAfter = dotProduct(y, xk) / dotProduct(y, xkMinus1);
			k++;
			if (k > n) {
                keepGoing = false;
				System.out.println("We did not get an answer within "
					+ n + "interations.");
                return;
			}
		}
		xk = unitEigenvector(xk);
		System.out.println("val " + lambdaAfter);
        System.out.println("vec");
        for (int i = 0; i < xk.length; i++) {
            System.out.print(xk[i] + " ");
        }
        System.out.println();
        System.out.println("iter ");
        System.out.println(k);
	}

	// public static boolean isDiagDominant(float[][] a) {
	// 	float 
	// 	for (int i = 0; i < a.length; i++ ) {
	// 		for (int j = 0; j < a[0].length; j++) {
	// 			if (a[i][i] < a[i][j]) {
	// 				return false;
	// 			}
	// 		}
	// 	}
	// 	return true;
	// }

	public float norm(float[] x) {
		float sum = 0;
		for (int i = 0; i < x.length; i++) {
			sum += x[i] * x[i];
		}
		double eigenvalue = (double) sum;
		return (float)Math.sqrt(eigenvalue);  
	}

	public float[] unitEigenvector(float[] x) {
		float[] unitEigenvector = new float[x.length];
		for (int i = 0; i < x.length; i++) {
			unitEigenvector[i] = x[i] / norm(x);
		}
		return unitEigenvector;
	}


	public float dotProduct(float[] v1, float[] v2) throws
    	IllegalArgumentException {
        if (v1.length != v2.length) {
            throw new IllegalArgumentException("Sorry, something went wrong.\n"
                + "Cannot operate dot product of a vector of length "
                + v1.length + " with a vector of length " + v2.length
                + ". \n"
                + "The length of two vectors have to be same.");
        }
        float a = 0;
        for (int i = 0; i < v1.length; i++) {
            a += v1[i] * v2[i];
        }
        return a;
    }

	public float[] matrixVectorMultiply(float[][] m, float[] v) throws
     	IllegalArgumentException {
        if (m[0].length != v.length) {
            throw new IllegalArgumentException("Sorry, something went wrong.\n"
                + "Cannot multiply a matrix of width " + m[0].length
                + " with a vector of length " + v.length + ". \nLength"
                + " of vector should be same as the width of the matrix.");
        }
        float[] a = new float[m.length];
        for (int i = 0; i < m.length; i++) {
            a[i] = 0;
            for (int j = 0; j < v.length; j++) {
                a[i] += m[i][j] * v[j];
            }
        }
        return a;
    }
}