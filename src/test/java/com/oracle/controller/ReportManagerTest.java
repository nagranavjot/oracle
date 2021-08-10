package com.oracle.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.oracle.model.Record;

class ReportManagerTest {

	List<Record> recordList;
	ReportManager reportManager;

	@org.junit.jupiter.api.BeforeEach
	public void setUp() throws Exception {
		recordList = new ArrayList<>();
		Record recordA = new Record("2343225,2345,us_east,RedTeam,ProjectApple,3445s");
		Record recordB = new Record("1223456,2345,us_west,BlueTeam,ProjectBanana,2211s");
		Record recordC = new Record("1233456,2345,us_west,BlueTeam,ProjectDate,2221s");
		recordList.add(recordA);
		recordList.add(recordB);
		recordList.add(recordC);

		reportManager = new ReportManager(recordList);
	}

	@Test
	void testGetCustomerCountByContract() {
		Map<String, Integer> map = reportManager.getCustomerCountByContract();
		assertEquals(3, map.get("2345"));
	}

	@Test
	void testGetCustomerCountByGeozone() {
		Map<String, Integer> map = reportManager.getCustomerCountByGeozone();
		assertEquals(2, map.get("us_west"));
	}

	@Test
	void testGetCustomersByGeozone() {
		Map<String, Set<String>> map = reportManager.getCustomersByGeozone();
		assertTrue(map.get("us_east").contains("2343225"));
	}

	@Test
	void testGetCustomersByGeozone_correct_report() {
		Map<String, Set<String>> map = reportManager.getCustomersByGeozone();
		assertFalse(map.get("us_east").contains("234322"));
	}

	@Test
	void testGetAvgBuildDurationByGeozone() {
		Map<String, Double> map = reportManager.getAvgBuildDurationByGeozone();
		double expected = 2216.0;
		double actual = map.get("us_west");
		assertEquals(expected, actual);
	}
}
