package employees.services;

import java.util.List;
import java.util.Map;

import employees.commons.EmployeePair;
import employees.commons.EmployeePairDetails;
import employees.commons.EmployeeRecord;
import employees.commons.ProjectTime;

/**
 * @author Ventsislav Penev
 * 
 * This interface declares the contract that each implementation of
 * <b>EmployeeService</b> must implement.
 *
 */
public interface EmployeeService {

	/**
	 * Method that finds all the employee pairs and their corresponding project
	 * times from a given list of employee records.
	 * 
	 * @param empRecords
	 *            - list of employee records
	 * 
	 * @return - map of key value pairs, where the key represents a given
	 *         employee pair and the value represents its corresponding common projects.
	 */
	public abstract Map<EmployeePair, List<ProjectTime>> findEmployeePairs(final List<EmployeeRecord> empRecords);

	/**
	 * Method that finds the employee pair worked longest on common projects and
	 * its corresponding details.
	 * 
	 * @param empRecords
	 *            - list of employee records
	 * 
	 * @return - details about the employee pair worked longest.
	 */
	public abstract EmployeePairDetails findEmployeePairWorkedLongest(final List<EmployeeRecord> empRecords);
}
