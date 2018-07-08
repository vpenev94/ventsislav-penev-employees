package employees.commons;

/**
 * 
 * @author Ventsislav Penev
 * 
 * This class represents an employee pair.
 */
public final class EmployeePair {

	private long firstEmployee;
	private long secondEmployee;

	/**
	 * Constructor that creates an instance of EmployeePair
	 * 
	 * @param firstEmployeeId
	 *            - id of the first employee
	 * @param secondEmployeeId
	 *            - id of the second employee
	 */
	public EmployeePair(final long firstEmployeeId, final long secondEmployeeId) {

		this.firstEmployee = firstEmployeeId;
		this.secondEmployee = secondEmployeeId;
	}

	/**
	 * Method that returns the id of the first employee
	 * 
	 * @return - id of the first employee
	 */
	public long getFirstEmployeeId() {

		return this.firstEmployee;
	}

	/**
	 * Method that returns the id of the second employee
	 * 
	 * @return - id of the second employee
	 */
	public long getSecondEmployeeId() {

		return this.secondEmployee;
	}

	@Override
	public boolean equals(final Object obj) {

		if (obj == null) {

			return false;
		}

		if (this == obj) {

			return true;
		}

		if (!(obj instanceof EmployeePair)) {

			return false;
		}

		final EmployeePair otherPair = (EmployeePair) obj;

		return (this.firstEmployee == otherPair.firstEmployee && this.secondEmployee == otherPair.secondEmployee)
				|| (this.firstEmployee == otherPair.secondEmployee && this.secondEmployee == otherPair.firstEmployee);
	}

	@Override
	public int hashCode() {

		long smallerId;
		long biggerId;
		if (this.firstEmployee <= this.secondEmployee) {

			smallerId = this.firstEmployee;
			biggerId = this.secondEmployee;

		} else {

			smallerId = this.secondEmployee;
			biggerId = this.firstEmployee;
		}

		int result = 17;
		result = 31 * result + Long.hashCode(smallerId);
		result = 31 * result + Long.hashCode(biggerId);

		return result;
	}
}
