package employees.ui;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import employees.commons.EmployeePairDetails;
import employees.commons.EmployeeRecord;
import employees.services.EmployeeService;
import employees.services.FileService;

/**
 * 
 * @author Ventsislav Penev
 * 
 * This class is responsible to encapsulate the logic that will be
 * needed for the UI main screen.
 */
@Component
class UIEmployeeService {

	@Autowired
	private FileService fileService;

	@Autowired
	private EmployeeService empService;

	public EmployeePairDetails findLongestEmployeePair(final String pathToFile) throws IOException {


			final List<EmployeeRecord> empRecords = this.fileService.readEmployeeRecords(pathToFile);

			return this.empService.findEmployeePairWorkedLongest(empRecords);

	}

}
