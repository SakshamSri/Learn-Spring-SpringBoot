package com.microservices.currencyexchangeservice;

@SuppressWarnings("serial")
public class CurrencyExchangeNotFoundException extends RuntimeException {

	public CurrencyExchangeNotFoundException(String string) {
		super(string);
	}

}
