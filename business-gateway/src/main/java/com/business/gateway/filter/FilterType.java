package com.business.gateway.filter;

public enum FilterType {

	SESSION_FILTER("SessionFilter"),
	LOGGER_FILTER("LoggerFilter"),
	ERROR_FILTER("ErrorFilter");
	
	private String value;
	
	private FilterType(String value){
		this.value = value;
	}
	public String value() {
		return value;
	}
}
