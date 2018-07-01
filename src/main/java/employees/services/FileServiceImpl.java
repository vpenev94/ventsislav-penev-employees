package employees.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import employees.commons.EmployeeRecord;
import employees.exceptions.ParseException;

@Component
public class FileServiceImpl implements FileService {

	private static final Logger LOGGER = Logger.getLogger(FileServiceImpl.class.getName());

	@SuppressWarnings("rawtypes")
	@Autowired
	private Parser empRecordParser;
	
	@Override
	public List<EmployeeRecord> readEmployeeRecords(final String pathToFile) throws IOException {

		List<EmployeeRecord> records = new ArrayList<>();

		try (Stream<String> lines = Files.lines(Paths.get(pathToFile))) {

			records = lines.map(this::parseLine)
							.filter(record -> record != null)
							.collect(Collectors.toList());

		} catch (IOException ex) {
			
			LOGGER.log(Level.SEVERE, "Exception when reading empoyees data.", ex.toString()); //$NON-NLS-1$
			
			throw ex;
		}

		return records;
	}

	@SuppressWarnings("unchecked")
	private EmployeeRecord parseLine(final String line) {

		try {

		return (EmployeeRecord) this.empRecordParser.parse(line);

		}
		catch (ParseException ex) {

			LOGGER.log(Level.SEVERE, "Exception while parsing." + ex.getMessage()); //$NON-NLS-1$
		}

		return null;
	}

}
