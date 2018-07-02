package com.gcs.nlyte.web.util;

public class ParametarizedData {
	private String operator;
	private Object parameterObject;
	
	public ParametarizedData(String operator, Object parameterObject){
		this.operator=operator;
		this.parameterObject=parameterObject;
	}
	
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Object getParameterObject() {
		return parameterObject;
	}
	public void setParameterObject(Object parameterObject) {
		this.parameterObject = parameterObject;
	}
}
