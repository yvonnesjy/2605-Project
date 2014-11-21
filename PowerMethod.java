import java.util.Random;
import javax.swing.JApplet;
import java.awt.Color;
import java.awt.Graphics;

public class PowerMethod {
    float[][] a;
    float[] x;
    float[] y;
    int n;
    int k;
    private static final double E_VALUE = 0.00005f;
    //private static float lambda;

    public static void main(String[] args) {
        float[][] a = {{0,-3,-6},{-7,5,-2},{-6,-6,-7}};
        float[] x = {1,0,20};
        float[] y = {1,9,8};
        PowerMethod pm = new PowerMethod(a, x, y, 100);
        pm.eigenPower();
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
        xkMinus1 = xk;
        xk = matrixVectorMultiply(a, xkMinus1);
        float lambdaAfter = dotProduct(y, xk) / dotProduct(y, xkMinus1);
        k = 2;
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
                        + n + " interations.");
                return;
            }
        }
        xk = unitVector(xk);
        System.out.println("The eigenvalue is " + lambdaAfter);
        System.out.print("The eigenvector is [ ");
        for (int i = 0; i < xk.length; i++) {
            System.out.print(xk[i] + " ");
        }
        System.out.println("]");
        System.out.println("It took " + k + " iterations");
    }

    public int getCount() {
        return k;
    }


    public static float trace(float[][] a) {
        float sum = 0f;
        for (int i = 0; i < a.length; i++) {
            sum += a[i][i];
        }
        return sum;
    }

    public float norm(float[] x) {
        float sum = 0;
        for (int i = 0; i < x.length; i++) {
            sum += Math.pow(x[i],2);
        }
        return (float)Math.sqrt(sum);
    }

    public float[] unitVector(float[] x) {
        float[] unitVector = new float[x.length];
        for (int i = 0; i < x.length; i++) {
            unitVector[i] = x[i] / norm(x);
        }
        return unitVector;
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

    public static float two_determinate(float[][] input){
        return ((input[1][1]*input[0][0])-(input[0][1]*input[1][0]));
    }
    public static float[][] two_inverse(float[][] input){
        float[][] newInverse = new float[2][2];
        float invserDeter = 1/(Math.abs(two_determinate(input)));

        newInverse[0][0] = invserDeter*input[1][1];
        newInverse[0][1] = invserDeter*-input[1][0];
        newInverse[1][0] = invserDeter*-input[0][1];
        newInverse[1][1] = invserDeter*input[0][0];
        return newInverse;
    }
}