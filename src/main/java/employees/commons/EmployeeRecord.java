package employees.commons;

import java.time.LocalDate;

/**
 * 
 * @author Ventsislav Penev
 * 
 * This class represents an employee record.
 */
public final class EmployeeRecord {

	private long employeeId;
	private long projectId;
	private LocalDate dateFrom;
	private LocalDate dateTo;

	/**
	 * Constructor that creates an instance of <b>EmployeeRecord</b>
	 * 
	 * @param employeeId
	 *            - id of the employee
	 * @param projectId
	 *            - id of the project
	 * @param dateFrom
	 *            - start date working on project
	 * @param dateTo
	 *            - end date working on project
	 */
	public EmployeeRecord(final long employeeId, final long projectId, final LocalDate dateFrom, final LocalDate dateTo) {

		this.employeeId = employeeId;
		this.projectId = projectId;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	/**
	 * Method that returns the id of the employee
	 * 
	 * @return - id of the employee
	 */
	public long getEmployeeId() {

		return this.employeeId;
	}

	/**
	 * Method that returns the id of the project
	 * 
	 * @return - id of the project
	 */
	public long getProjectId() {

		return this.projectId;
	}

	/**
	 * Method that returns the start date working on the project
	 * 
	 * @return - start date working on the project
	 */
	public LocalDate getStrartDate() {

		return this.dateFrom;
	}

	/**
	 * Method that returns the end date working on the project
	 * 
	 * @return - end date working on the project
	 */
	public LocalDate getEndDate() {

		return this.dateTo;
	}

}
