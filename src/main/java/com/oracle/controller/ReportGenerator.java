package com.oracle.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.oracle.model.Record;

/**
 * This class streams over all the records that are stored in memory and runs
 * queries over it to generate reports
 * 
 * @author navjot
 *
 */
public class ReportGenerator {
	List<Record> recordsList;

	public ReportGenerator(List<Record> recordsList) {
		this.recordsList = recordsList;
	}

	/**
	 * Returns the list of customer ids for each contract
	 * 
	 * @return
	 */
	public Map<String, Set<String>> getUniqueCustIdsByContract() {
		return recordsList.stream().collect(Collectors.groupingBy(Record::getContractId,
				Collectors.mapping(Record::getCustomerId, Collectors.toSet())));

	}

	/**
	 * Returns the list of customer ids for each geozone
	 * 
	 * @return
	 */
	public Map<String, Set<String>> getUniqueCustIdsByGeozone() {
		return recordsList.stream().collect(Collectors.groupingBy(Record::getGeoZone,
				Collectors.mapping(Record::getCustomerId, Collectors.toSet())));

	}

	/**
	 * Returns the Avg build duration for each geozone
	 * 
	 * @return
	 */
	public Map<String, Double> getAvgBuildDurationByGeozone() {
		return recordsList.stream()
				.collect(Collectors.groupingBy(Record::getGeoZone, Collectors.averagingLong(Record::getBuildDuration)));

	}
}
