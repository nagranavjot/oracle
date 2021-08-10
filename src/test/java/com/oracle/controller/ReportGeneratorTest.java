package com.oracle.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.oracle.model.Record;

class ReportGeneratorTest {

	List<Record> recordList;
	ReportGenerator reportGenerator;

	@org.junit.jupiter.api.BeforeEach
	public void setUp() throws Exception {
		recordList = new ArrayList<>();
		Record recordA = new Record("2343225,2345,us_east,RedTeam,ProjectApple,3445s");
		Record recordB = new Record("1223456,2345,us_west,BlueTeam,ProjectBanana,2211s");
		Record recordC = new Record("1233456,2345,us_west,BlueTeam,ProjectDate,2221s");
		recordList.add(recordA);
		recordList.add(recordB);
		recordList.add(recordC);

		reportGenerator = new ReportGenerator(recordList);
	}

	@Test
	void testGetUniqueCustIdsByContract() {
		Map<String, Set<String>> map = reportGenerator.getUniqueCustIdsByContract();
		assertEquals(3, map.get("2345").size());
	}

	@Test
	void testGetUniqueCustIdsByContract_invalid_contract() {
		Map<String, Set<String>> map = reportGenerator.getUniqueCustIdsByContract();
		assertNull(map.get("234"));
	}

	@Test
	void testGetUniqueCustIdsByGeozone() {
		Map<String, Set<String>> map = reportGenerator.getUniqueCustIdsByGeozone();
		assertEquals(2, map.get("us_west").size());
	}

	@Test
	void testGetUniqueCustIdsByGeozone_invalid_geozone() {
		Map<String, Set<String>> map = reportGenerator.getUniqueCustIdsByGeozone();
		assertNull(map.get("eu_west"));
	}

	@Test
	void testGetAvgBuildDurationByGeozone() {
		Map<String, Double> map = reportGenerator.getAvgBuildDurationByGeozone();
		double expected = 2216.0;
		double actual = map.get("us_west");
		assertEquals(expected, actual);
	}
}
