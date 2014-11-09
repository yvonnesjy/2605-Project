/**
 * unchecked exception that extends IndexOutOfBoundsException
 * @author Yan Chen
 * @version 1.0
 */
public class VectorIndexOutOfBoundsException extends IndexOutOfBoundsException {
    /**
    * non-argument constructor
    */
    public VectorIndexOutOfBoundsException() {
        super("Vector Index Out Of Bounds Exception.");
    }
    /**
    * one argument constructor
    * @param message message that describes detail of the exception
    */
    public VectorIndexOutOfBoundsException(String message) {
        super(message);
    }
}
