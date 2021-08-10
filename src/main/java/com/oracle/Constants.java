package com.oracle;

/**
 * A class with all the constants that are used in different classes of the
 * application
 * 
 * @author navjot
 *
 */
public class Constants {

	private Constants() {
		throw new IllegalStateException("Constants class");
	}

	public static final String CUSTOMERS_WITH_CONTRACT = "The number of unique customerIds for each contractId:";
	public static final String CUSTOMER_COUNT_IN_GEOZONE = "The number of unique customerIds for each geozone:";
	public static final String CUSTOMERS_IN_GEOZONE = "The list of unique customerIds for each geozone:";
	public static final String AVG_BUILD_IN_GEOZONE = "The average buildduration for each geozone:";
}
