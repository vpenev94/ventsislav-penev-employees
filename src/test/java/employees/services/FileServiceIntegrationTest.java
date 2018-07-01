package employees.services;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import employees.commons.EmployeeRecord;

/**
 * 
 * @author Ventsislav Penev
 * 
 * This class is responsible to test the functionality of
 * <b>FileServiceImpl</b> and verify that it properly reads the data from
 * file.
 *
 */
public class FileServiceIntegrationTest extends AbstractIntegrationSpringTest {

	@Autowired
	private FileService fileService;

	@Test
	public void itShouldCreateTheCorrectObjectsFromFile() throws IOException {

		// given
		final EmployeeRecord expectedRecord = new EmployeeRecord(1, 2, LocalDate.parse("2018-05-20"), //$NON-NLS-1$
				LocalDate.parse("2018-06-25")); //$NON-NLS-1$
		final String inputFile = getClass().getClassLoader().getResource("employees-basic.txt").getFile(); //$NON-NLS-1$

		// when
		final List<EmployeeRecord> readRecords = this.fileService.readEmployeeRecords(inputFile);

		// then
		assertEquals(2, readRecords.size());
		assertEquals(expectedRecord.getEmployeeId(), readRecords.get(0).getEmployeeId());
		assertEquals(expectedRecord.getProjectId(), readRecords.get(0).getProjectId());
		assertEquals(expectedRecord.getStrartDate(), readRecords.get(0).getStrartDate());
		assertEquals(expectedRecord.getEndDate(), readRecords.get(0).getEndDate());
	}
}
