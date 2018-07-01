package employees.exceptions;

/**
 * @author Ventsislav Penev
 * 
 * This exception is thrown when parsing fails due to some reason.
 *
 */
public class ParseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ParseException(final String message) {
		super(message);
	}

	public ParseException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
