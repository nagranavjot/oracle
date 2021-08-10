package com.oracle.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.oracle.exception.UnableToParseInputException;
import com.oracle.model.Record;

class TxtFileReaderTest {

	@Test
	void testGetRecords() {
		TxtFileReader txtFileReader = new TxtFileReader("src/test/resources/input.txt");
		try {
			List<Record> recordsList = txtFileReader.getRecords();
			assertEquals(5, recordsList.size());
		} catch (UnableToParseInputException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testGetRecords_fileNotFound() {
		TxtFileReader txtFileReader = new TxtFileReader("src/test/resources/invalid.txt");
		try {
			txtFileReader.getRecords();
		} catch (UnableToParseInputException e) {
			assertEquals("Unable to find the input file src/test/resources/invalid.txt", e.getMessage());
		}
	}
	
	@Test
	void testGetRecords_unableToParseInput() {
		TxtFileReader txtFileReader = new TxtFileReader("src/test/resources/unparsable.txt");
		try {
			txtFileReader.getRecords();
		} catch (UnableToParseInputException e) {
			assertEquals("Invalid record. Unable to parse it.", e.getMessage());
		}
	}
}
