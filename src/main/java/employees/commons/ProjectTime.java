package employees.commons;

import java.time.LocalDate;

/**
 * 
 * @author Ventsislav Penev
 * 
 * This class represents the working time range for a given project.
 */
public final class ProjectTime {

	private long projectId;
	private LocalDate startDate;
	private LocalDate endDate;

	/**
	 * Constructor that creates an instance of <b>ProjectTime</b>
	 * 
	 * @param projectId
	 *            - id of the project
	 * @param startDate
	 *            - start date working on the project
	 * @param endDate
	 *            - end date working on project
	 */
	public ProjectTime(final long projectId, final LocalDate startDate, final LocalDate endDate) {

		this.projectId = projectId;
		this.startDate = startDate;
		this.endDate = endDate;
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
	public LocalDate getStartDate() {

		return this.startDate;
	}

	/**
	 * Method that returns the end date working on the project
	 * 
	 * @return - end date working on the project
	 */
	public LocalDate getEndDate() {

		return this.endDate;
	}
}
