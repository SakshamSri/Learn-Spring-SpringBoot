package com.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;

@RestController
public class CircuitBreakerController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	// to test what if service is down for a while
	@GetMapping("/sample-api")
	// @Retry(name = "sample-api-retry", fallbackMethod = "hardcodedResponse") // default will try hitting the url 3 times 
							 // and if failed 3rd time, it will give an error
	// @CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse") // what circuit breaker will do is try to 
				// 	retry for certain number of times and if the service still fails to respond back, 
				// it will break the circuit i.e. it will give back a default 
				// fallback response for all further calls
				// how it knows service is back? IN NOTES.
	// @RateLimiter(name = "custom-rate")
	@Bulkhead(name = "custom-bulkhead")
	public String samepleApi() {
		logger.info("Sample Api call");
		
		// added a url that we know will fail to replicate a 
		// scenario in IRL when a microservice will fail because its down
//		ResponseEntity<String> forEntity = new RestTemplate()
//				.getForEntity("http://localhost:8080/some-dummy-fail-url", String.class);
		return "Some sample api";
		// return forEntity.getBody();
	}
	
	// fallback method for what to do after # of retries
	// we can have multiple overloaded methods for different different 
	// types of Exception (we can write for all exceptions that extends Throwable class)
	private String hardcodedResponse(Exception ex) {
		return "fallback response";
	}
}
