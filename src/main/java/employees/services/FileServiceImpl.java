package employees.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import employees.commons.EmployeeRecord;
import employees.exceptions.InvalidDataFormatException;
import employees.exceptions.ParseException;

@Component
public class FileServiceImpl implements FileService {

	private static final Logger LOGGER = Logger.getLogger(FileServiceImpl.class.getName());

	@SuppressWarnings("rawtypes")
	@Autowired
	private Parser empRecordParser;

	@Override
	public List<EmployeeRecord> readEmployeeRecords(final String pathToFile) throws IOException {

		try (Stream<String> lines = Files.lines(Paths.get(pathToFile))) {

			final List<EmployeeRecord> empRecords = lines.map(this::parseLine)
														 .collect(Collectors.toList());

			return empRecords;

		} catch (IOException ex) {

			LOGGER.log(Level.SEVERE, "Error when reading empoyees data.", ex.getMessage()); //$NON-NLS-1$

			throw ex;
		}

	}

	@SuppressWarnings("unchecked")
	private EmployeeRecord parseLine(final String line) {

		try {

			return (EmployeeRecord) this.empRecordParser.parse(line);

		} catch (ParseException ex) {

			LOGGER.log(Level.SEVERE, "Error while parsing employee record." + ex.getMessage()); //$NON-NLS-1$
			
			throw new InvalidDataFormatException("Invalid data format for: {"+line+"}",ex); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

}
