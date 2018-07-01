package employees.services;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	public EmployeePairDetails findEmployeePairWorkedLongest(List<EmployeeRecord> empRecords) {

		final HashMap<EmployeePair, List<ProjectTime>> pairProjecTimes = new HashMap<>();

		final Map<Long, List<EmployeeRecord>> employeesPerProject = empRecords.stream()
																				.collect(Collectors.groupingBy(EmployeeRecord::getProjectId, Collectors.toList()));

		
		for (Map.Entry<Long, List<EmployeeRecord>> project : employeesPerProject.entrySet()) {
			
			final Map<EmployeePair, ProjectTime> empPairsPerProject = findEmployeePairs(project.getValue());

			empPairsPerProject.entrySet().stream()
			                             .forEach(entry -> addProjTimeForEmpPair(pairProjecTimes, entry));
		}

		
		Map.Entry<EmployeePair, List<ProjectTime>> bestPair = pairProjecTimes.entrySet().stream()
																						.sorted(this::compare)
																						.findFirst()
																						.orElse(null);
		
		if(bestPair != null){
			
			return new EmployeePairDetails(bestPair.getKey().getFirstEmployeeId(), bestPair.getKey().getSecondEmployeeId(), bestPair.getValue());
		}
		
		return null;
	}

	@Override
	public final Map<EmployeePair, ProjectTime> findEmployeePairs(final List<EmployeeRecord> empRecords) {

		final HashMap<EmployeePair, ProjectTime> pairProjectTimes = new HashMap<>();

		for (int i = 0; i < empRecords.size() - 1; i++) {

			final EmployeeRecord firstRecord = empRecords.get(i);

			for (int j = i + 1; j < empRecords.size(); j++) {

				final EmployeeRecord secondRecord = empRecords.get(j);

				final Period overlappedPeriod = DateUtils.getPeriodOverlap(
						new Period(firstRecord.getStrartDate(), firstRecord.getEndDate()),
						new Period(secondRecord.getStrartDate(), secondRecord.getEndDate()));

				if (overlappedPeriod != null) {

					final ProjectTime projTime = new ProjectTime(firstRecord.getProjectId(),
							overlappedPeriod.getStartDate(), overlappedPeriod.getEndDate());
					final EmployeePair empPair = new EmployeePair(firstRecord.getEmployeeId(),
							secondRecord.getEmployeeId());

					pairProjectTimes.put(empPair, projTime);
				}
			}
		}
		return pairProjectTimes;
	}

	@SuppressWarnings("static-method")
	private void addProjTimeForEmpPair(final HashMap<EmployeePair, List<ProjectTime>> pairProjectTimes,
			final Map.Entry<EmployeePair, ProjectTime> pairProjTime) {

		if (pairProjectTimes.containsKey(pairProjTime.getKey())) {

			pairProjectTimes.get(pairProjTime.getKey()).add(pairProjTime.getValue());
		}

		else {
			final List<ProjectTime> projTimes = new ArrayList<>();
			projTimes.add(pairProjTime.getValue());
			pairProjectTimes.put(pairProjTime.getKey(), projTimes);
		}
	}

	private int compare(Map.Entry<EmployeePair, List<ProjectTime>> pair1,
			Map.Entry<EmployeePair, List<ProjectTime>> pair2) {
		
		final long firstPairWorkedDays = calculateTotalDaysWorked(pair1.getValue());
		final long secondPairWorkedDays = calculateTotalDaysWorked(pair2.getValue());
		
		return firstPairWorkedDays > secondPairWorkedDays ? -1 : firstPairWorkedDays < secondPairWorkedDays ? 1 : 0;
	}
	
	private long calculateTotalDaysWorked(final List<ProjectTime> projTimes){

		return projTimes.stream().mapToLong(this::calculateWorkedDays).sum();
	}
	
	private long calculateWorkedDays(final ProjectTime projTime){
		
		return Duration.between(projTime.getStartDate().atStartOfDay(), projTime.getEndDate().atStartOfDay()).toDays();
	}
}
