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

	private EmployeePair empPair;
	private List<ProjectTime> commonProjects;

	/**
	 * Constructor that creates an instance of <b>EmployeePairDetails</b>
	 * 
	 * @param employeePair
	 *            - represents the employee pair
	 * 
	 * @param commonProjects
	 *            - list containing the details about the common projects
	 */
	public EmployeePairDetails(final EmployeePair empPair, final List<ProjectTime> commonProjects) {

		this.empPair = empPair;
		this.commonProjects = commonProjects;
	}

	/**
	 * Method that returns the employee pair.
	 * 
	 * @return - the employee pair
	 */
	public EmployeePair getEmployeePair() {

		return this.empPair;
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
