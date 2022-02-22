package com.restful.webservices.Learnrestfulwebservices.versioning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restful.webservices.Learnrestfulwebservices.versioning.bean.Name;
import com.restful.webservices.Learnrestfulwebservices.versioning.bean.PersonV1;
import com.restful.webservices.Learnrestfulwebservices.versioning.bean.PersonV2;

@RestController
public class PersonVersioningController {

	@GetMapping("v1/person")
	public PersonV1 getPersonV1() {
		return new PersonV1("First Last");
	}

	@GetMapping("v2/person")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("First", "Last"));
	}

	@GetMapping(value = "person/param", params = "version=1")
	public PersonV1 getPersonParamV1() {
		return new PersonV1("First Last");
	}

	@GetMapping(value = "person/param", params = "version=2")
	public PersonV2 getPersonParamV2() {
		return new PersonV2(new Name("First", "Last"));
	}

	@GetMapping(value = "person/header", headers = "VERSION=1")
	public PersonV1 getPersonHeaderV1() {
		return new PersonV1("First Last");
	}

	@GetMapping(value = "person/header", headers = "VERSION=2")
	public PersonV2 getPersonHeaderV2() {
		return new PersonV2(new Name("First", "Last"));
	}

	@GetMapping(value = "person/produces", produces = "application/some.company.v1+json")
	public PersonV1 getPersonProducesV1() {
		return new PersonV1("First Last");
	}

	@GetMapping(value = "person/produces", produces = "application/some.company.v2+json")
	public PersonV2 getPersonProducesV2() {
		return new PersonV2(new Name("First", "Last"));
	}
}
