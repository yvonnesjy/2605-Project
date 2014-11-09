/**
 * Immutable abstraction of Matrix.
 *
 * @author Michael Maurer
 * @version 1.2
 */
public final class Matrix {

    /*
    Create final instance variables
    */
    private final float[][] matrix;
    private final int height;
    private final int width;
    /**
     * Initialize instance variables
     * @param matrix 2D array representation of Matrix
     */
    public Matrix(float[][] matrix) {
        this.matrix = matrix;
        height = matrix.length;
        width = matrix[0].length;
    }

    /**
     * Gets value located at specified row and column
     * @param i row
     * @param j column
     * @return double located at row i and column j in matrix
     */
    public float get(int i, int j) {
        if (i >= height || i < 0 || j >= width || j < 0) {
            throw new MatrixIndexOutOfBoundsException("Matrix"
                + "Index Out Of Bounds Exception: i = " + i + ", j = " + j
                + ". Matrix bound: Height = " + height + ", Width = "
                + width + ".");
        }
        return matrix[i][j];
    }

    /**
     * Get's the height of the matrix.
     * @return number of rows in matrix
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get's the width of the matrix.
     * @return number of columns in matrix
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets String representation of matrix.
     * Columns separated by tabs, rows by new lines.
     * @return String representation of matrix.
     */
    public String toString() {
        String n = "";
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                n += get(i, j);
                if (j != width - 1) {
                    n += "\t";
                }
            }
            n += "\n";
        }
        return n;
    }
}
