package employees.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

import emoployees.utils.DateUtils;
import emoployees.utils.Period;

/**
 * 
 * @author Ventsislav Penev
 * 
 * This class is responsible to test the functionality of the methods in
 * the <b>DateUtils</b> class.
 *
 */
public class DateUtilsUnitTest {

	@SuppressWarnings("static-method")
	@Test
	public void twoPeriodsShouldOverlap() {

		// given
		final LocalDate firstPeriodStartDate = LocalDate.of(2018, 06, 20);
		final LocalDate firstPeriodEndDate = LocalDate.of(2018, 06, 26);

		final LocalDate secondPeriodStartDate = LocalDate.of(2018, 06, 18);
		final LocalDate secondPeriodEndDate = LocalDate.of(2018, 06, 28);

		final Period firstPeriod = new Period(firstPeriodStartDate, firstPeriodEndDate);
		final Period secondPeriod = new Period(secondPeriodStartDate, secondPeriodEndDate);

		// when
		final boolean isOverlap = DateUtils.isOverlap(firstPeriod, secondPeriod);

		// then
		assertTrue(isOverlap);
	}

	@SuppressWarnings("static-method")
	@Test
	public void twoPeriodsShouldNotOverlap() {

		// given
		final LocalDate firstPeriodStartDate = LocalDate.of(2018, 06, 22);
		final LocalDate firstPeriodEndDate = LocalDate.of(2018, 06, 26);

		final LocalDate secondPeriodStartDate = LocalDate.of(2018, 06, 18);
		final LocalDate secondPeriodEndDate = LocalDate.of(2018, 06, 20);

		final Period firstPeriod = new Period(firstPeriodStartDate, firstPeriodEndDate);
		final Period secondPeriod = new Period(secondPeriodStartDate, secondPeriodEndDate);

		// when
		final boolean isOverlap = DateUtils.isOverlap(firstPeriod, secondPeriod);

		// then
		assertFalse(isOverlap);
	}

	@SuppressWarnings("static-method")
	@Test
	public void itShouldReturnCorrectPeriodOverlap() {

		// given
		final LocalDate firstPeriodStartDate = LocalDate.of(2018, 06, 20);
		final LocalDate firstPeriodEndDate = LocalDate.of(2018, 06, 26);

		final LocalDate secondPriodStartDate = LocalDate.of(2018, 06, 22);
		final LocalDate secondPeriodEndDate = LocalDate.of(2018, 06, 27);

		final Period firstPeriod = new Period(firstPeriodStartDate, firstPeriodEndDate);
		final Period secondPeriod = new Period(secondPriodStartDate, secondPeriodEndDate);

		// when
		final Period overlappedPeriod = DateUtils.getPeriodOverlap(firstPeriod, secondPeriod);

		// then
		assertEquals(secondPriodStartDate, overlappedPeriod.getStartDate());
		assertEquals(firstPeriodEndDate, overlappedPeriod.getEndDate());
	}

	@SuppressWarnings("static-method")
	@Test
	public void itShoulCorrectlyCalculateDaysBetweenDates() {

		// given
		final LocalDate startDate = LocalDate.of(2018, 06, 20);
		final LocalDate endDate = LocalDate.of(2018, 06, 26);
		final long expectedDays = 6;

		// when
		final long actualDays = DateUtils.calculateDaysBetweenDates(startDate, endDate);

		// then
		assertEquals(expectedDays, actualDays);
	}
}
