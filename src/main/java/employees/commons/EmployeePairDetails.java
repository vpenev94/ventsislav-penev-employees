package employees.commons;

import java.util.List;

/**
 * 
 * @author Ventsislav Penev
 * 
 * This class represents the details about the employee pair and its
 * common projects.
 */
public final class EmployeePairDetails {

	private long firstEmployeeId;
	private long secondEmployeeId;
	private List<ProjectTime> commonProjects;

	/**
	 * Constructor that creates an instance of <b>EmployeePairDetails</b>
	 * 
	 * @param firstEmployeeId
	 *            - id of the first employee
	 * @param secondEmployeeId
	 *            - id of the second employee
	 * @param commonProjects
	 *            - list containing the details about the common projects
	 */
	public EmployeePairDetails(final long firstEmployeeId, final long secondEmployeeId,
			final List<ProjectTime> commonProjects) {

		this.firstEmployeeId = firstEmployeeId;
		this.secondEmployeeId = secondEmployeeId;
		this.commonProjects = commonProjects;
	}

	/**
	 * Method that returns the id of the first employee
	 * 
	 * @return - id of the first employee
	 */
	public long getFirstEmployee() {

		return this.firstEmployeeId;
	}

	/**
	 * Method that returns the id of the second employee
	 * 
	 * @return - id of the second employee
	 */
	public long getSecondEmployee() {

		return this.secondEmployeeId;
	}

	/**
	 * Method that returns the list of common projects details
	 * 
	 * @return - list of common projects details
	 */
	public List<ProjectTime> getCommonProjects() {
		return this.commonProjects;
	}

}
