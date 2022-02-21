package com.restful.webservices.Learnrestfulwebservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.restful.webservices.Learnrestfulwebservices.bean.HelloWorldBean;

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;

	@GetMapping("/hello-world")
	// Can also use: @RequestMapping(method=RequestMethod.GET, path="/hello-world")
	public String getHelloMessage() {
		return "Hello World";
	}

	@GetMapping("/hello-world-bean")
	public HelloWorldBean getHelloWorldBean() {
		return new HelloWorldBean("Hello World Bean");
	}

	@GetMapping("/hello-world-bean/path-var/{name}")
	public HelloWorldBean getHelloWorldBeanPathVarName(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello %s", name));
	}

	@GetMapping("/hello-world-i18n") // we can specify for the incoming header key "accept-language" a locale var and
										// use message.properties file to write messages for that particular locale.
										// Naming message.properties file is important as spring boot will automatically
										// look into the correct locale file
										// we can also remove the incoming paramter as it becomes inconvenient everytime
										// to defince locale paramter and use LocaleContextHolder
	public String getHelloWorldInternationalized()
	// @RequestHeader(name = "Accept-Language", required = false) Locale locale)
	{
		return messageSource.getMessage("hello.world.message", null, "default hello world"
		// , locale);
				, LocaleContextHolder.getLocale());
	}
}
