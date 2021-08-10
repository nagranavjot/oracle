package com.oracle.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oracle.model.Record;

/**
 * This class will have the client facing API's and will be making requests to
 * the ReportGenerator as required.
 * 
 * @author navjot
 *
 */
public class ReportManager {

	private Map<String, Set<String>> geozoneToCustomersMap;
	private ReportGenerator rGenerator;

	public ReportManager(List<Record> recordsList) {
		this.rGenerator = new ReportGenerator(recordsList);
		initializeLookUpTables();
	}

	/**
	 * This will store in memory the geozone to customers mapping to AVOID iterating
	 * over entire stream TWICE( for getCustomerCountByGeozone() and
	 * getCustomersByGeozone() api's)
	 */
	private void initializeLookUpTables() {
		geozoneToCustomersMap = rGenerator.getUniqueCustIdsByGeozone();
	}

	/**
	 * Returns the count of customers for each contract
	 * 
	 * @return
	 */
	public Map<String, Integer> getCustomerCountByContract() {
		Map<String, Integer> contractToCustomerCount = new HashMap<>();
		rGenerator.getUniqueCustIdsByContract().entrySet()
				.forEach(entry -> contractToCustomerCount.put(entry.getKey(), entry.getValue().size()));
		return contractToCustomerCount;
	}

	/**
	 * Returns the count of customers in each geozone
	 * 
	 * @return
	 */
	public Map<String, Integer> getCustomerCountByGeozone() {
		Map<String, Integer> geozoneToCustomerCount = new HashMap<>();
		geozoneToCustomersMap.entrySet()
				.forEach(entry -> geozoneToCustomerCount.put(entry.getKey(), entry.getValue().size()));
		return geozoneToCustomerCount;
	}

	/**
	 * Returns the list of customers in each geozone
	 * 
	 * @return
	 */
	public Map<String, Set<String>> getCustomersByGeozone() {
		return geozoneToCustomersMap;
	}

	/**
	 * Returns the average build duration for each geozone
	 * 
	 * @return
	 */
	public Map<String, Double> getAvgBuildDurationByGeozone() {
		return rGenerator.getAvgBuildDurationByGeozone();

	}
}
