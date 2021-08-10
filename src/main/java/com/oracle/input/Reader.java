package com.oracle.input;

import java.util.List;

import com.oracle.exception.UnableToParseInputException;
import com.oracle.model.Record;

/**
 * Interface to support different kinds of Readers, to read from csv file or
 * read from console, read from JSON
 * 
 * @author navjot
 *
 */
public interface Reader {
	public List<Record> getRecords() throws UnableToParseInputException;
}
