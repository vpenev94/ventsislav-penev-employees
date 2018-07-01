package employees.ui;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	private static final Logger LOGGER = Logger.getLogger(UIEmployeeService.class.getName());

	@Autowired
	private FileService fileService;

	@Autowired
	private EmployeeService empService;

	public EmployeePairDetails findLongestEmployeePair(final String pathToFile) throws IOException{

		try {

			final List<EmployeeRecord> empRecords = this.fileService.readEmployeeRecords(pathToFile);

			return this.empService.findEmployeePairWorkedLongest(empRecords);

		} catch (IOException ex) {

			LOGGER.log(Level.SEVERE, "Exception when loading employees.", ex.toString()); //$NON-NLS-1$
			
			throw ex;
		}
	}

}
