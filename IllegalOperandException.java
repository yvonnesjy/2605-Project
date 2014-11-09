/**
 * checked exception that extends Exception
 * @author Yan Chen
 * @version 1.0
 */
public class IllegalOperandException extends Exception {
    /**
    * non-argument constructor
    */
    public IllegalOperandException() {
        super("Illegal Operand Exception.");
    }
    /**
    * one argument constructor
    * @param message message that describes detail of the exception
    */
    public IllegalOperandException(String message) {
        super(message);
    }
}
