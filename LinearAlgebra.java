/**
* contain 4 static method for Linear Algebra calculation
* @author Yan Chen
* @version 1.0
*/
import java.util.Arrays;

public class LinearAlgebra {


    public static float[][] matrixMultiplication(float[][] m1, float[][] m2)
        throws IllegalOperandException {
            if (m1[0].length != m2.length) {
                throw new IllegalOperandException("Sorry, something went wrong."
                + "\nCannot multiply two matrices with different dimensions.");
            }
            float[][] n = new float[m1.length][m2[0].length];
            for (int i = 0; i < m1.length; i++) {
                for (int j = 0; j < m2[0].length; j++) {
                    n[i][j] = 0;
                    for (int k = 0; k < m1[0].length; k++) {
                        n[i][j] += m1[i][k] * m2[k][j];
                    }
                }
            }
        return n;
    }


    public static void main(String[] args) {
        float[][] m1 = {{1,2,3}, {1,2,3}, {1,2,3}, {1,2,3}};
        float[][] m2 = {{1,2,3,4}, {1,2,3,4}, {1,2,3,4}};

        float[] v1 = {1,2,3};
        float[] v2 = {1,2,3};
        float[] v3 = {5,6,7};


        try {
            for (int i = 0; i < m1.length; i++) {
                System.out.println();
                for (int j = 0; j < m1.length; j++) {
                    System.out.print(matrixMultiplication(m1, m2)[i][j] + " ");
                }
            }
            System.out.println();
            System.out.println(Arrays.toString(vectorAdd(v1, v2)));
            System.out.println(Arrays.toString(vectorSub(v3, v2)));
            System.out.println(Arrays.toString(matrixVectorMultiply(m1, v2)));

            for (int i = 0; i < m1.length; i++) {
                System.out.println();
                for (int j = 0; j < m1[0].length; j++) {
                    System.out.print(matrixScalar(m1, 3)[i][j] + " ");
                }
            }

        } catch (IllegalOperandException e) {
            System.out.println("haha");
        }
        
    }
    
    public static float[] vectorAdd(float[] v1, float[] v2) throws
    IllegalOperandException {
        if (v1.length != v2.length) {
            throw new IllegalOperandException("Sorry, something went wrong.\n"
                + "Cannot operate vector addtion of a vector of length "
                + v1.length + " with a vector of length " + v2.length
                + ". \n"
                + "The length of two vectors have to be same.");
        }
        float[] n = new float[v1.length];
        for (int i = 0; i < v1.length; i++) {
            n[i] = v1[i] + v2[i];
        }
        return n;
    }

    public static float[] vectorSub(float[] v1, float[] v2) throws
    IllegalOperandException {
        if (v1.length != v2.length) {
            throw new IllegalOperandException("Sorry, something went wrong.\n"
                + "Cannot operate vector addtion of a vector of length "
                + v1.length + " with a vector of length " + v2.length
                + ". \n"
                + "The length of two vectors have to be same.");
        }
        float[] n = new float[v1.length];
        for (int i = 0; i < v1.length; i++) {
            n[i] = v1[i] - v2[i];
        }
        return n;
    }
    public static float[] matrixVectorMultiply(float[][] m, float[] v) throws
     IllegalOperandException {
        if (m[0].length != v.length) {
            throw new IllegalOperandException("Sorry, something went wrong."
                + "\nCannot multiply a matrix of width " + m[0].length
                + " with a vector of length " + v.length + ". \nLength"
                + " of vector should be same as the width of the matrix.");
        }
        float[] n = new float[m.length];
        for (int i = 0; i < m.length; i++) {
            n[i] = 0;
            for (int j = 0; j < v.length; j++) {
                n[i] += m[i][j] * v[j];
            }
        }
        return n;
    }
    public static float[][] matrixScalar(float[][] m, float s) {
        float[][] n = new float[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                n[i][j] = s * m[i][j];
            }
        }
        return n;
    }
}
