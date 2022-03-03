package com.microservices.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CurrencyExchangeController {

	@Autowired
	private Environment environment;

	@Autowired
	private CurrencyExchangeRepository repository;

	@Retry(name = "sample-api-retry") // resilience4j retries
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange getExchangeValue(@PathVariable String from, @PathVariable String to) {
		from = from.toUpperCase();
		to = to.toUpperCase();
		CurrencyExchange exchange = repository.findByFromAndTo(from, to);
		if (exchange == null)
			throw new CurrencyExchangeNotFoundException(
					"Unable to find " + from + " to " + to + " conversion rate in database.");
		String port = environment.getProperty("local.server.port");
		exchange.setEnvironment(port);
		return exchange;
	}
}
