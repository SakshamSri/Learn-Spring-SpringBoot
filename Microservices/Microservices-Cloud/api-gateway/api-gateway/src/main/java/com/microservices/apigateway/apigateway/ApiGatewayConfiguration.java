package com.microservices.apigateway.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		/*
		any request coming to /currency-exchange/** will be redirected to uri(val) 
		=> lb = loadbalancer, //name = name on eureka server
		feign will automatically map to feign internally, 
		and default hardcoded url one will also map to its own
		we can also add new URLs like /currency-conversion-new/, here we need to rewrite the 
		incoming url to something that exists internally like /currency-conversion-feign/
		<segment> part is to copy whatever after *new/ to after *feign/
		*/
		return builder.routes()
				.route(p -> p.path("/get")
						.filters(f -> f
								.addRequestHeader("CustomHeader", "CustomHeaderValue")
								.addRequestParameter("CustomParameter", "CustomParameterValue"))
						.uri("http://httpbin.org:80"))
				.route(p -> p.path("/currency-exchange/**")
						.uri("lb://currency-exchange"))
				.route(p -> p.path("/currency-conversion/**")
						.uri("lb://currency-conversion"))
				.route(p -> p.path("/currency-conversion-feign/**")
						.uri("lb://currency-conversion"))
				.route(p -> p.path("/currency-conversion-new/**")
						.filters(f -> f.rewritePath(
								"/currency-conversion-new/(?<segment>.*)",
								"/currency-conversion-feign/${segment}"))
						.uri("lb://currency-conversion"))
				.build();
	}
}
