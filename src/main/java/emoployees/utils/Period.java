package emoployees.utils;

import java.time.LocalDate;
import java.util.Objects;

/**
 * 
 * @author Ventsislav Penev
 * 
 * This class is responsible to wrap a given date period.
 */
public final class Period {

	private LocalDate start;
	private LocalDate end;

	/**
	 * Constructor that creates an instance of <strong>Period</strong> class.
	 * 
	 * @param startDate
	 *            - start date of the period
	 * @param endDate
	 *            - end date of the period.
	 */
	public Period(final LocalDate startDate, final LocalDate endDate) {

		Objects.requireNonNull(startDate);
		Objects.requireNonNull(endDate);

		if (startDate.isAfter(endDate)) {

			final String errorMessage = String.format("Start date(%s) can not after end date(%s)!", startDate, endDate); //$NON-NLS-1$
			throw new IllegalArgumentException(errorMessage);
		}
		this.start = startDate;
		this.end = endDate;
	}

	/**
	 * Method that returns the start date of the period.
	 * 
	 * @return - start date of the period.
	 */
	public LocalDate getStartDate() {

		return this.start;
	}

	/**
	 * Method that retuns the start date of the period.
	 * 
	 * @return - end date of the period.
	 */
	public LocalDate getEndDate() {

		return this.end;
	}

}
