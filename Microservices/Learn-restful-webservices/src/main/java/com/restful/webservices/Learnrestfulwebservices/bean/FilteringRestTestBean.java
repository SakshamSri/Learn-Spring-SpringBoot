package com.restful.webservices.Learnrestfulwebservices.bean;

import com.fasterxml.jackson.annotation.JsonFilter;

//@JsonIgnoreProperties(value = { "field1" }) // We can also define this on class level and give the names of the fields
// we want to get ignored when returning back the Bean to User.
@JsonFilter("filterTestBean") // we name the FilterProvider filters with some name when we define it. That
								// name needs to be added here on the bean so that it can identify the bean
								// correctly.
public class FilteringRestTestBean {

	private String field1;
	private String field2;

	// Suppose this field we dont want to send to user when bean is returned or this
	// is a secure field like password and we dont want to send it. We can add
	// @JsonIgnore to ignore when sending the data back.
	// @JsonIgnore
	private String field3;

	protected FilteringRestTestBean() {
	}

	public FilteringRestTestBean(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	@Override
	public String toString() {
		return "FilteringRestTestBean [field1=" + field1 + ", field2=" + field2 + ", field3=" + field3 + "]";
	}

}
