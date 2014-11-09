/**
 * unchecked exception that extends IndexOutOfBoundsException
 * @author Yan Chen
 * @version 1.0
 */
public class MatrixIndexOutOfBoundsException extends IndexOutOfBoundsException {

    /**
    * non-argument constructor
    */
    public MatrixIndexOutOfBoundsException() {
        super("Matrix Index Out Of Bounds Exception.");
    }
    /**
    * one argument constructor
    * @param message message that describes detail of the exception
    */
    public MatrixIndexOutOfBoundsException(String message) {
        super(message);
    }
}
