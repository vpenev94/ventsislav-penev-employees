package emoployees.utils;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;

/**
 * 
 * @author Ventsislav Penev
 *
 * The class contains utility methods, related to operations with dates.
 */
public class DateUtils {

	/**
	 * This method is responsible for checking if two given date periods overlap
	 * 
	 * @param firstPeriod
	 *            - first period
	 * @param secondPeriod
	 *            - second period
	 * @return - true if the two periods overlap or false if they do not
	 */
	public static final boolean isOverlap(final Period firstPeriod, final Period secondPeriod) {

		Objects.requireNonNull(firstPeriod);
		Objects.requireNonNull(secondPeriod);

		if (secondPeriod.getEndDate().isBefore(firstPeriod.getStartDate())
				|| secondPeriod.getStartDate().isAfter(firstPeriod.getEndDate())) {

			return false;
		}

		return true;
	}

	/**
	 * This method is responsible for giving the overlapping period between two
	 * given periods.
	 * 
	 * @param firstPeriod
	 *            - first period
	 * @param secondPeriod
	 *            - second period
	 * @return - returns the overlap period between the two given periods.
	 *         Returns null if there is no overlapping period.
	 */
	public static final Period getPeriodOverlap(final Period firstPeriod, final Period secondPeriod) {

		Objects.requireNonNull(firstPeriod);
		Objects.requireNonNull(secondPeriod);

		if (!isOverlap(firstPeriod, secondPeriod)) {

			return null;
		}

		LocalDate startDate;
		LocalDate endDate;

		if (firstPeriod.getStartDate().isAfter(secondPeriod.getStartDate())) {

			startDate = firstPeriod.getStartDate();
		} else {
			startDate = secondPeriod.getStartDate();
		}

		if (firstPeriod.getEndDate().isBefore(secondPeriod.getEndDate())) {

			endDate = firstPeriod.getEndDate();
		} else {
			endDate = secondPeriod.getEndDate();
		}

		return new Period(startDate, endDate);
	}

	/**
	 * This method calculates the dates between two given dates.
	 * 
	 * @param firstDate
	 *            - first date
	 * @param secondDate
	 *            - second date
	 * @return - number of days between the two given dates.
	 */
	public static final long calculateDaysBetweenDates(final LocalDate firstDate, final LocalDate secondDate) {

		Objects.requireNonNull(firstDate);
		Objects.requireNonNull(secondDate);

		return Duration.between(firstDate.atStartOfDay(), secondDate.atStartOfDay()).toDays();
	}
}
