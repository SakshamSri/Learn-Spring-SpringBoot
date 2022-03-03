package com.microservices.currencyconversionservice.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// @FeignClient(name = "currency-exchange", url = "localhost:8000") // we need to use the name defined in the calling
// microservice. For eg. here in
// currency-exchange-service we defined
// spring.application.name = currency-exchange
// for url we only define the ip and port. the rest url will be added as method in the class.
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion getExchangeValue(@PathVariable String from, @PathVariable String to); // will change the
																									// return type to
																									// type of this
																									// class
}
