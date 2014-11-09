/**
 * Immutable abstraction for Vector
 *
 * @author Michael Maurer
 * @version 1.2
 */
public final class Vector {

    /*
    Create final instance variables
    */
    private final float[] vector;
    private final int length;
    /**
     * Initialize instance variables
     * @param vector array representation of vector
     */
    public Vector(float[] vector) {
        this.vector = vector;
        length = vector.length;
    }

    /**
     * Gets value located at specified index
     * @param i index in vector
     * @return double located at index 'i' in vector
     */
    public float get(int i) {
        if (i >= length || i < 0) {
            throw new VectorIndexOutOfBoundsException("Vector"
                + "Index Out Of Bounds Exception: i = " + i
                + ". Vector length: " + length + ".");
        }
        return vector[i];
    }

    /**
     * Get's the length of the Vector.
     * @return number of components in vector
     */
    public int getLength() {
        return length;
    }

    /**
     * String representation of vector with components
     * separated by tabs
     * @return String representation of vector
     */
    public String toString() {
        String n = "";
        for (int i = 0; i < length; i++) {
            n += vector[i];
            if (i != length - 1) {
                n += "\t";
            }
        }
        return n;
    }
}
