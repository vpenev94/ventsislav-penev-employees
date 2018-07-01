package employees.services;

/**
 * @author Ventsislav Penev
 *
 * This interface contains method that each parser should implement.
 *
 * @param <S>
 *            - the Type of the source object.
 * @param <T>
 *            - the Type of the destination object
 */
public interface Parser<S, T> {

	/**
	 * Method responsible to parse an object from type S to object from type T.
	 * 
	 * @param source
	 *            - source object of type S
	 * 
	 * @return - returns the new parsed object of type T, or throws
	 *         <b>ParseException</b> if parsing fails due to some reason.
	 */
	public abstract T parse(S source);
}
