/**
 * A subclass of Exception that is thrown when an HiringTable object is
 * filled with the max number of Applicant objects.
 *
 * @author
 * Nicole Niemiec
 * CSE 214 REC08
 * HOMEWORK #1
 *
 * @version 1
 */

public class FullTableException extends Exception {

    /**
     * Message that explains why the exception was caused.
     */
    private String code;

    /**
     * Constructor method that creates an FullTableException object.
     */
    public FullTableException(){

    }

    /**
     * Constructor method with parameters that create an FullTableException object.
     * @param code
     *      Reason why the exception is being thrown.
     */
    public FullTableException(String code){
        super("Table is full: " + code);
        this.code = code;
    }

    /**
     * An accessor method for an explanation of the exception that has occurred.
     * @return
     *      Returns a String of the message that explains
     *      why the exception has occured.
     */
    public String getCode() {
        return code;
    }
}

