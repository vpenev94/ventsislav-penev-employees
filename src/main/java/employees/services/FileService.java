package employees.services;

import java.io.IOException;
import java.util.List;

import employees.commons.EmployeeRecord;

/**
 * @author Ventsislav Penev
 * 
 * This interface declares the contract that each implementation of
 * <b>FileService</b> must implement.
 *
 */
public interface FileService {

	/**
	 * Method that read all employee records from a given file
	 * 
	 * @param pathToFIle
	 *            - path to the file
	 * 
	 * @return - list of employee records
	 * 
	 * @throws IOException
	 *             - if something goes wrong during reading data from the file.
	 * @throws InvalidDataFormatException
	 *             - if there is a problem with the format of the data.
	 */
	public abstract List<EmployeeRecord> readEmployeeRecords(final String pathToFIle) throws IOException;
}
