package com.leonet.common.filter;

import java.util.ArrayList;
import java.util.List;

public class SalesFilterGroup {
	private List<SalesFilter> filters = new ArrayList<>();

	public void addFilter(String field, Object value, Operator operator) {
		addFilter(field, value, operator, false);
	}

	public void addFilter(String field, Object value, Operator operator, boolean useOr) {
		if (value != null) {
			filters.add(new SalesFilter(field, value, operator, useOr));
		}
	}

	public List<SalesFilter> getFilters() {
		return filters;
	}
}