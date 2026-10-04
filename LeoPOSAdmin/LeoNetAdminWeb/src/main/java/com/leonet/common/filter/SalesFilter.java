package com.leonet.common.filter;

public class SalesFilter {

	private String field;
	private Object value;
	private Operator operator;
	private boolean useOr;

	public SalesFilter(String field, Object value, Operator operator, boolean useOr) {
		this.field = field;
		this.value = value;
		this.operator = operator;
		this.useOr = useOr;
	}

	public String getField() {
		return field;
	}

	public Object getValue() {
		return value;
	}

	public Operator getOperator() {
		return operator;
	}

	public boolean isUseOr() {
		return useOr;
	}
}
