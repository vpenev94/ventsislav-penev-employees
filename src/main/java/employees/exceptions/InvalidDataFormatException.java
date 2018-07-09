package employees.exceptions;

/**
 * @author Ventsislav Penev
 * 
 * This exception is thrown when there is a problem with data format.
 *
 */
public class InvalidDataFormatException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidDataFormatException(final String message) {
		super(message);
	}

	public InvalidDataFormatException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
