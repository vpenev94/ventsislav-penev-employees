package employees.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import employees.commons.EmployeeRecord;
import employees.exceptions.ParseException;

/**
 * @author Ventsislav Penev
 * 
 * This class is responsible for parsing a given text line,
 * representing an employee record, into an EmployeeRecord object.
 * 
 * It implements the contract defined in <b>Parser</b> interface.
 *
 */
@Component
public class EmployeeLineParser implements Parser<String, EmployeeRecord> {

	private static final Logger LOGGER = Logger.getLogger(EmployeeLineParser.class.getName());

	private Environment env;
	
	private final DateTimeFormatter dateFormatter;

	public EmployeeLineParser(final Environment env){
		
		this.env = env;
		this.dateFormatter = DateTimeFormatter.ofPattern(this.env.getProperty("date.format")); //$NON-NLS-1$
				
	}
	@Override
	public EmployeeRecord parse(String line) {

		final String[] recordElements = line.split(",\\s*"); //$NON-NLS-1$

		try {

			final long empId = Long.parseLong(recordElements[0]);
			final long projectId = Long.parseLong(recordElements[1]);
			final LocalDate startDate = convertStringToLocalDate(recordElements[2]);
			final LocalDate endDate = convertStringToLocalDate(recordElements[3]);

			return new EmployeeRecord(empId, projectId, startDate, endDate);

		}

		catch (NumberFormatException | DateTimeParseException ex) {

			final String parseExcMsg = "Error while parsing line: {"+line+"} " + ex.getMessage(); //$NON-NLS-1$ //$NON-NLS-2$
			
			LOGGER.log(Level.SEVERE, parseExcMsg);
			
			throw new ParseException(parseExcMsg, ex);
		}

	}

	private LocalDate convertStringToLocalDate(final String dateText) {

		return dateText.equals("NULL") ? LocalDate.now() : LocalDate.parse(dateText, this.dateFormatter); //$NON-NLS-1$
	}

}
