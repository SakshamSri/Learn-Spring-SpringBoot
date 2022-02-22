package com.restful.webservices.Learnrestfulwebservices.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.restful.webservices.Learnrestfulwebservices.bean.FilteringRestTestBean;

@RestController
public class FilteringRestTestController {

	// we only want field1 and field2 to return
	@GetMapping("/filtering-test")
	public MappingJacksonValue geFilteringRestTestBean() {
		FilteringRestTestBean bean = new FilteringRestTestBean("val1", "val2", "val3");

// 		define the filters here. We want only field1 and field2.
//		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
//		FilterProvider filters = new SimpleFilterProvider().addFilter("filterTestBean", filter);

		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(bean);

		mappingJacksonValue.setFilters(filterHelperFunction("field1", "field2"));

		return mappingJacksonValue;
	}

	// we only want field2 and field3 to return
	@GetMapping("/filtering-test-list")
	public MappingJacksonValue geFilteringRestTestBeanList() {
		List<FilteringRestTestBean> list = Arrays.asList(new FilteringRestTestBean("val1", "val2", "val3"),
				new FilteringRestTestBean("val4", "val5", "val6"));

//		define the filters here. We want only field2 and field3.
//		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
//		FilterProvider filters = new SimpleFilterProvider().addFilter("filterTestBean", filter);

		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);

		mappingJacksonValue.setFilters(filterHelperFunction("field2", "field3"));

		return mappingJacksonValue;
	}

	private FilterProvider filterHelperFunction(String... strings) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(strings);

		return new SimpleFilterProvider().addFilter("filterTestBean", filter);
	}
}
