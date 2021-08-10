package com.oracle.model;

import com.oracle.exception.UnableToParseInputException;

/**
 * This class maps each entry in input string to a java record. If more columns
 * are added in future to the input line this is the only class that need to
 * change.
 * 
 * @author navjot
 *
 */
public class Record {
	private String customerId;
	private String contractId;
	private String geoZone;
	private String teamCode;
	private String projectCode;
	private long buildDuration;

	public Record(String line) throws UnableToParseInputException {
		String[] array = line.trim().split(",");
		if (array.length != 6) {
			throw new UnableToParseInputException("Invalid record. Unable to parse it.");
		}
		this.customerId = array[0];
		this.contractId = array[1];
		this.geoZone = array[2];
		this.teamCode = array[3];
		this.projectCode = array[4];
		this.buildDuration = Long.parseLong(array[5].substring(0, array[5].length() - 1));
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getGeoZone() {
		return geoZone;
	}

	public void setGeoZone(String geoZone) {
		this.geoZone = geoZone;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public long getBuildDuration() {
		return buildDuration;
	}

	public void setBuildDuration(long buildDuration) {
		this.buildDuration = buildDuration;
	}

}
