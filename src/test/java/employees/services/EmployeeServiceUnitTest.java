package employees.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import employees.commons.EmployeePair;
import employees.commons.EmployeePairDetails;
import employees.commons.EmployeeRecord;
import employees.commons.ProjectTime;

/**
 * 
 * @author Ventsislav Penev
 * 
 * This class is responsible to test the functionality of
 * <b>EmployeeServiceImpl</b> in isolation.
 *
 */
public class EmployeeServiceUnitTest {

	private static FileService fileService;
	private static EmployeeService empService;

	@BeforeClass
	public static void init() {

		fileService = mock(FileService.class);
		empService = new EmployeeServiceImpl();
	}

	@SuppressWarnings({ "nls", "static-method" })
	@Test
	public void itShoudFindEmpPairsPerProject() throws IOException {

		// given
		final List<EmployeeRecord> empRecords = new ArrayList<>();

		empRecords.add(new EmployeeRecord(3, 0, LocalDate.parse("2018-06-15"), LocalDate.parse("2018-06-20")));
		empRecords.add(new EmployeeRecord(4, 0, LocalDate.parse("2018-06-16"), LocalDate.parse("2018-06-20")));
		empRecords.add(new EmployeeRecord(5, 0, LocalDate.parse("2018-06-25"), LocalDate.parse("2018-06-30")));
		empRecords.add(new EmployeeRecord(6, 0, LocalDate.parse("2018-06-27"), LocalDate.parse("2018-06-30")));

		final EmployeePair expectedPair1 = new EmployeePair(3, 4);
		final EmployeePair expectedPair2 = new EmployeePair(5, 6);

		// when
		when(fileService.readEmployeeRecords(any(String.class))).thenReturn(empRecords);
		final List<EmployeeRecord> inputRecords = fileService.readEmployeeRecords("");

		final Map<EmployeePair, ProjectTime> empPairs = empService.findEmployeePairs(inputRecords);

		// then
		assertEquals(2, empPairs.size());
		assertTrue(empPairs.containsKey(expectedPair1));
		assertTrue(empPairs.containsKey(expectedPair2));

	}

	@SuppressWarnings({ "nls", "static-method" })
	@Test
	public void itShouldFindLongestPair() throws IOException {

		// given
		final List<EmployeeRecord> empRecords = new ArrayList<>();

		empRecords.add(new EmployeeRecord(3, 0, LocalDate.parse("2018-06-15"), LocalDate.parse("2018-06-20")));
		empRecords.add(new EmployeeRecord(4, 0, LocalDate.parse("2018-06-16"), LocalDate.parse("2018-06-20")));
		empRecords.add(new EmployeeRecord(1, 2, LocalDate.parse("2018-06-20"), LocalDate.parse("2018-06-25")));
		empRecords.add(new EmployeeRecord(2, 2, LocalDate.parse("2018-06-20"), LocalDate.parse("2018-06-25")));
		empRecords.add(new EmployeeRecord(3, 3, LocalDate.parse("2018-06-28"), LocalDate.parse("2018-06-30")));
		empRecords.add(new EmployeeRecord(4, 3, LocalDate.parse("2018-06-27"), LocalDate.parse("2018-06-30")));

		// when
		when(fileService.readEmployeeRecords(any(String.class))).thenReturn(empRecords);
		final List<EmployeeRecord> inputRecords = fileService.readEmployeeRecords("");

		final EmployeePairDetails longestEmpPair = empService.findEmployeePairWorkedLongest(inputRecords);

		// then
		assertEquals(3, longestEmpPair.getFirstEmployee());
		assertEquals(4, longestEmpPair.getSecondEmployee());
		assertEquals(2, longestEmpPair.getCommonProjects().size());
	}
}
