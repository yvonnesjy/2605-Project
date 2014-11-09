/**
* contain 4 static method for Linear Algebra calculation
* @author Yan Chen
* @version 1.0
*/
public class LinearAlgebra {
    /**
    * a static method that operates matrix vector multiplication
    * @throws IllegalOperandException throw IllegalOperandException if
    * length of input vector is not the same as the width of input matrix.
    * @param m a matrix
    * @param v a vector
    * @return result of matrix vector multiplication
    */
    public static Vector matrixVectorMultiply(Matrix m, Vector v) throws
     IllegalOperandException {
        if (m.getWidth() != v.getLength()) {
            throw new IllegalOperandException("Sorry, something went wrong."
                + "\nCannot multiply a matrix of width " + m.getWidth()
                + " with a vector of length " + v.getLength() + ". \nLength"
                + " of vector should be same as the width of the matrix.");
        }
        double[] n = new double[m.getHeight()];
        for (int i = 0; i < m.getHeight(); i++) {
            n[i] = 0;
            for (int j = 0; j < v.getLength(); j++) {
                n[i] += m.get(i, j) * v.get(j);
            }
        }
        return new Vector(n);
    }
    /**
    * a static method that operates matrix addition
    * @throws IllegalOperandException throw IllegalOperandException if
    * size of the tow input matrix are not the same.
    * @param m1 a matrix
    * @param m2 an other matrix
    * @return result of matrix addition
    */
    public static Matrix matrixAdd(Matrix m1, Matrix m2) throws
    IllegalOperandException {
        if (m1.getWidth() != m2.getWidth()
            || m1.getHeight() != m2.getHeight()) {
            throw new IllegalOperandException("Sorry, something went wrong.\n"
                + "Cannot add a matrix of width " + m1.getWidth() + " heigth "
                + m1.getHeight() + " with a matrix of width " + m2.getWidth()
                + " heigth " + m2.getHeight() + ". \n"
                + "The size of two matrix have to be same.");
        }
        double[][] n = new double[m1.getHeight()][m1.getWidth()];
        for (int i = 0; i < m1.getHeight(); i++) {
            for (int j = 0; j < m1.getWidth(); j++) {
                n[i][j] = m1.get(i, j) + m2.get(i, j);
            }
        }
        return new Matrix(n);
    }
    /**
    * a static method that operates vector dot production
    * @throws IllegalOperandException throw IllegalOperandException if
    * length of the two input vector are not the same.
    * @param v1 a vector
    * @param v2 an other vector
    * @return result of vector dot production
    */
    public static double dotProduct(Vector v1, Vector v2) throws
    IllegalOperandException {
        if (v1.getLength() != v2.getLength()) {
            throw new IllegalOperandException("Sorry, something went wrong.\n"
                + "Cannot operate dot product of a vector of length "
                + v1.getLength() + " with a vector of length " + v2.getLength()
                + ". \n"
                + "The length of two vectors have to be same.");
        }
        double n = 0;
        for (int i = 0; i < v1.getLength(); i++) {
            n += v1.get(i) * v2.get(i);
        }
        return n;
    }
    /**
    * a static method that operates vector addition
    * @throws IllegalOperandException throw IllegalOperandException if
    * length of the two input vector are not the same.
    * @param v1 a vector
    * @param v2 an other vector
    * @return result of vector addition
    */
    public static Vector vectorAdd(Vector v1, Vector v2) throws
    IllegalOperandException {
        if (v1.getLength() != v2.getLength()) {
            throw new IllegalOperandException("Sorry, something went wrong.\n"
                + "Cannot operate vector addtion of a vector of length "
                + v1.getLength() + " with a vector of length " + v2.getLength()
                + ". \n"
                + "The length of two vectors have to be same.");
        }
        float[] n = new float[v1.getLength()];
        for (int i = 0; i < v1.getLength(); i++) {
            n[i] = v1.get(i) + v2.get(i);
        }
        return new Vector(n);
    }
}
