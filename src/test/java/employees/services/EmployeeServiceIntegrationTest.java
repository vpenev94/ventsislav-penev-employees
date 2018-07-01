package employees.services;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import employees.commons.EmployeePairDetails;
import employees.commons.EmployeeRecord;

/**
 * 
 * @author Ventsislav Penev
 * 
 * This class is responsible to test the whole functionality of the
 * <b>EmployeeServiceImpl</b>.
 *
 */
public class EmployeeServiceIntegrationTest extends AbstractIntegrationSpringTest {

	@Autowired
	private FileService fileService;

	@Autowired
	private EmployeeService empService;

	@Test
	public void itShouldCorrectlyFindLongestEmpPair() throws IOException {

		// given
		final String inputFile = getClass().getClassLoader().getResource("employees-integration.txt").getFile(); //$NON-NLS-1$

		// when
		final List<EmployeeRecord> empRecords = this.fileService.readEmployeeRecords(inputFile);
		final EmployeePairDetails longestEmpPair = this.empService.findEmployeePairWorkedLongest(empRecords);

		// then
		assertEquals(3, longestEmpPair.getFirstEmployee());
		assertEquals(4, longestEmpPair.getSecondEmployee());
		assertEquals(2, longestEmpPair.getCommonProjects().size());
	}
}