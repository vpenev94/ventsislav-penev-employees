package employees.services;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import emoployees.utils.DateUtils;
import emoployees.utils.Period;
import employees.commons.EmployeePair;
import employees.commons.EmployeePairDetails;
import employees.commons.EmployeeRecord;
import employees.commons.ProjectTime;

@Component
public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public EmployeePairDetails findEmployeePairWorkedLongest(final List<EmployeeRecord> empRecords) {


		final Map<EmployeePair, List<ProjectTime>> empPairsProjecs = findEmployeePairs(empRecords );


		final Map.Entry<EmployeePair, List<ProjectTime>> bestPair = empPairsProjecs.entrySet().stream()
																						.sorted(this::compare)
																						.findFirst()
																						.orElse(null);


		if (bestPair != null) {

			return new EmployeePairDetails(bestPair.getKey(), bestPair.getValue());
		}

		return null;
	}

	@Override
	public final Map<EmployeePair, List<ProjectTime>> findEmployeePairs(final List<EmployeeRecord> empRecords) {

		final Map<EmployeePair, List<ProjectTime>> pairProjectTimes = new HashMap<>();

		for (int i = 0; i < empRecords.size() - 1; i++) {

			final EmployeeRecord firstRecord = empRecords.get(i);

			for (int j = i + 1; j < empRecords.size(); j++) {

				final EmployeeRecord secondRecord = empRecords.get(j);

				if (firstRecord.getEmployeeId() == secondRecord.getEmployeeId()
						|| firstRecord.getProjectId() != secondRecord.getProjectId()) {
				
					continue;
				}
				
				final Period overlappedPeriod = DateUtils.getPeriodOverlap(
						new Period(firstRecord.getStrartDate(), firstRecord.getEndDate()),
						new Period(secondRecord.getStrartDate(), secondRecord.getEndDate()));

				if (overlappedPeriod != null) {

					final ProjectTime projTime = new ProjectTime(firstRecord.getProjectId(),
							overlappedPeriod.getStartDate(), overlappedPeriod.getEndDate());
					final EmployeePair empPair = new EmployeePair(firstRecord.getEmployeeId(),
							secondRecord.getEmployeeId());

					addProjTimeForEmpPair(pairProjectTimes, empPair, projTime);
				}
			}
		}
		return pairProjectTimes;
	}


	@SuppressWarnings("static-method")
	private void addProjTimeForEmpPair(final Map<EmployeePair, List<ProjectTime>> pairProjectTimes,
			final EmployeePair empPair, final ProjectTime projTime) {

		if (pairProjectTimes.containsKey(empPair)) {

			pairProjectTimes.get(empPair).add(projTime);
		}

		else {
			final List<ProjectTime> projTimes = new ArrayList<>();
			projTimes.add(projTime);
			pairProjectTimes.put(empPair, projTimes);
		}
	}


	
	private int compare(Map.Entry<EmployeePair, List<ProjectTime>> pair1,
			Map.Entry<EmployeePair, List<ProjectTime>> pair2) {

		final long firstPairWorkedDays = calculateTotalDaysWorked(pair1.getValue());
		final long secondPairWorkedDays = calculateTotalDaysWorked(pair2.getValue());

		return firstPairWorkedDays > secondPairWorkedDays ? -1 : firstPairWorkedDays < secondPairWorkedDays ? 1 : 0;
	}


	
	private long calculateTotalDaysWorked(final List<ProjectTime> projTimes) {

		return projTimes.stream().mapToLong(this::calculateWorkedDays).sum();
	}



	private long calculateWorkedDays(final ProjectTime projTime) {

		return Duration.between(projTime.getStartDate().atStartOfDay(), projTime.getEndDate().atStartOfDay()).toDays();
	}
}
