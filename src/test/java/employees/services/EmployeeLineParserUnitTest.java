package employees.services;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import employees.commons.EmployeeRecord;

/**
 * 
 * @author Ventsislav Penev
 * 
 * This class is responsible to test the functionality of
 * <b>EmployeeLineParser</b>.
 *
 */
public class EmployeeLineParserUnitTest extends AbstractIntegrationSpringTest {

	@Autowired
	private Parser<String, EmployeeRecord> empLineParser;

	@Test
	public void itShouldCorrectlyParseLine() {

		// given
		final String empRecord = "1,2,2018-05-20,2018-05-25"; //$NON-NLS-1$
		final EmployeeRecord expectedRecord = new EmployeeRecord(1, 2, LocalDate.parse("2018-05-20"), //$NON-NLS-1$
				LocalDate.parse("2018-05-25")); //$NON-NLS-1$

		// when
		final EmployeeRecord actualRecord = this.empLineParser.parse(empRecord);

		// then
		assertEquals(expectedRecord.getEmployeeId(), actualRecord.getEmployeeId());
		assertEquals(expectedRecord.getProjectId(), actualRecord.getProjectId());
		assertEquals(expectedRecord.getStrartDate(), actualRecord.getStrartDate());
		assertEquals(expectedRecord.getEndDate(), actualRecord.getEndDate());
	}
}
