package com.leonet.spec.helper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.leonet.common.filter.Operator;
import com.leonet.common.filter.SalesFilter;
import com.leonet.common.filter.SalesFilterGroup;

public class SalesFilterHelper {

	/** Range filter for numeric or comparable fields */
	public static SalesFilterGroup range(String field, Object start, Object end) {
		SalesFilterGroup group = new SalesFilterGroup();
		if (start != null && end != null) {
			group.addFilter(field, new Object[] { start, end }, Operator.BETWEEN);
		}
		return group;
	}

	/** Single date filter (whole day) */
	public static SalesFilterGroup singleDate(String field, LocalDate date) {
		SalesFilterGroup group = new SalesFilterGroup();
		if (date != null) {
			// Convert LocalDate to start and end of day
			Date from = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			Date to = Date.from(date.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());
			group.addFilter(field, new Date[] { from, to }, Operator.BETWEEN);
		}
		return group;
	}

	/** Optional: create a list of filters only if values exist */
	public static List<SalesFilterGroup> buildGroups(Long startId, Long endId, LocalDate filterDate) {
		List<SalesFilterGroup> groups = new ArrayList<>();
		if (startId != null && endId != null) {
			groups.add(range("saleId", startId, endId));
		}
		if (filterDate != null) {
			groups.add(singleDate("date", filterDate));
		}
		return groups;
	}

	/** Single equality filter */
	public static SalesFilterGroup equals(String field, Object value) {
		SalesFilterGroup group = new SalesFilterGroup();

		if (value != null) {
			group.addFilter(field, value, Operator.EQ);
		}

		return group;
	}

	/** Multiple filters in one group (AND/OR controlled via useOr) */
	public static SalesFilterGroup andFilters(List<SalesFilter> filters) {
		SalesFilterGroup group = new SalesFilterGroup();

		if (filters != null) {
			filters.forEach(f -> group.addFilter(f.getField(), f.getValue(), f.getOperator(), f.isUseOr()));
		}

		return group;
	}
}
