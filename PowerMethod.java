import java.util.Random;
public class PowerMethod {
    float[][] a;
    float[] x;
    float[] y;
    int n;
    private static final double E_VALUE = 0.00005f;
    //private static float lambda;


    public static void main(String[] args) {
        // float[][] a = {{(float)-2, (float)-3}, {(float)-1, (float)0}};
        // float[] x = {(float)1, (float)0};
        // float[] y = {(float)1, (float)0};
        // int n = 50;

        // PowerMethod test1 = new PowerMethod(a, x, y, n);
        // test1.eigenPower();

        int num = 1000;
        Random r = new Random();
        for (int i = 0; i < num; i++) {
            float a00 = r.nextFloat() * 4 - 2f;
            float a01 = r.nextFloat() * 4 - 2f;
            float a10 = r.nextFloat() * 4 - 2f;
            float a11 = r.nextFloat() * 4 - 2f;
            float[][] a = {{a00, a01}, {a10, a11}};
            while (two_determinate(a) == 0) {
                a00 = r.nextFloat() * 4 - 2f;
                a01 = r.nextFloat() * 4 - 2f;
                a10 = r.nextFloat() * 4 - 2f;
                a11 = r.nextFloat() * 4 - 2f;
                //                a = {{a00, a01}, {a10, a11}};
            }
            a[0][0] = a00;
            a[0][1] = a01;
            a[1][0] = a10;
            a[1][1] = a11;
            //            a = {{a00, a01}, {a10, a11}};
            float[] x = {1, 0};
            float[] y = {1, 0};
            PowerMethod power = new PowerMethod(a, x, y, 100);
            float[][] b = two_inverse(a);
            PowerMethod inversePower = new PowerMethod(b, x, y, 100);
            power.eigenPower();
            inversePower.eigenPower();
            System.out.println("The trace of the matrix is " + trace(a));
            System.out.println("The determinant of the matrix is " + two_determinate(a));
        }
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
        // System.out.println(lambdaBefore);
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
            sum += x[i] * x[i];
        }
        double eigenvalue = sum;
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