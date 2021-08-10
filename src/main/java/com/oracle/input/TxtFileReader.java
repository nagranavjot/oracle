package com.oracle.input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.oracle.exception.UnableToParseInputException;
import com.oracle.model.Record;

/**
 * Class to read the input string from text file
 * 
 * @author navjot
 *
 */
public class TxtFileReader implements Reader {
	private String filePath;

	public TxtFileReader(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Returns the list of records that will be used by ReportManager to produce
	 * different kinds of reports
	 */
	@Override
	public List<Record> getRecords() throws UnableToParseInputException {
		List<Record> recordsList = null;
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			final String fileAsText = reader.lines().collect(Collectors.joining(System.lineSeparator()));
			recordsList = parseFileText(fileAsText);

		} catch (FileNotFoundException e) {
			throw new UnableToParseInputException(String.format("Unable to find the input file %s", filePath));
		} catch (IOException e) {
			throw new UnableToParseInputException(
					String.format("Unable to parse input file %s. %s", filePath, e.getMessage()));
		}
		return recordsList;

	}

	private List<Record> parseFileText(String input) throws UnableToParseInputException {
		List<String> records = Arrays.asList(input.split(System.lineSeparator()));
		List<Record> allRecords = new ArrayList<>();
		for (String line : records) {
			allRecords.add(new Record(line));
		}
		return allRecords;
	}

}
