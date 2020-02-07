/**
 * A subclass of Exception that is thrown when an Applicant is not found
 * in a HiringTable object.
 *
 * @author
 * Nicole Niemiec
 * #112039349
 * CSE 214 REC08
 * HOMEWORK #1
 *
 * @version 1
 */

public class ApplicantNotFoundException extends Exception{

    /**
     * Message that explains why the exception was caused.
     */
    private String code;

    /**
     * Constructor method that creates an ApplicantNotFoundException object.

    public ApplicantNotFoundException(){

    }

    /**
     * Constructor method with parameters that create an ApplicantNotFoundException bpject.
     * @param code
     *      Reason why the exception is being thrown.
     */
    public ApplicantNotFoundException(String code){
        super("Applicant not found in the table: " + code);
        this.code = code;
    }

    /**
     * An accessor method for an explanation of the exception that has occurredd.
     * @return
     *      Returns a String of the message that explains
     *      why the exception has occured.
     */
    public String getCode() {
        return code;
    }
}


