package com.oracle;

import static com.oracle.Constants.AVG_BUILD_IN_GEOZONE;
import static com.oracle.Constants.CUSTOMERS_IN_GEOZONE;
import static com.oracle.Constants.CUSTOMERS_WITH_CONTRACT;
import static com.oracle.Constants.CUSTOMER_COUNT_IN_GEOZONE;

import java.util.Map;

import com.oracle.controller.ReportManager;
import com.oracle.exception.UnableToParseInputException;
import com.oracle.input.Reader;
import com.oracle.input.TxtFileReader;

/**
 * This class is the entry point for the application
 * 
 * @author navjot
 *
 */
public class App {

	public static void main(String[] args) {
		try {
			if (args.length==0 || args[0] == null || args[0].isEmpty()) {
				throw new UnableToParseInputException("Filename cannot be null or empty");
			}
			Reader reader = new TxtFileReader(args[0]);
			ReportManager reportManager = new ReportManager(reader.getRecords());
			display(reportManager.getCustomerCountByContract(), CUSTOMERS_WITH_CONTRACT);
			display(reportManager.getCustomerCountByGeozone(), CUSTOMER_COUNT_IN_GEOZONE);
			display(reportManager.getAvgBuildDurationByGeozone(), AVG_BUILD_IN_GEOZONE);
			display(reportManager.getCustomersByGeozone(), CUSTOMERS_IN_GEOZONE);

		} catch (UnableToParseInputException e) {
			App.displayMsg(e.getMessage());
		}
	}

	private static <T> void display(Map<String, T> customerCount, String header) {
		displayMsg(header);
		customerCount.entrySet().forEach(entry -> formatMsg(entry.getKey(), String.valueOf(entry.getValue())));
	}

	private static void formatMsg(String key, String value) {

		System.out.format("%40c%s, %s%c%n", '[', key, value, ']');
	}

	private static void displayMsg(String message) {
		System.out.println(message);

	}
}
