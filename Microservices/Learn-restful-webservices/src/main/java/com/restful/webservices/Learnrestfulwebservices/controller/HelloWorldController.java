package com.restful.webservices.Learnrestfulwebservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.restful.webservices.Learnrestfulwebservices.bean.HelloWorldBean;

@RestController
public class HelloWorldController {

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
}
