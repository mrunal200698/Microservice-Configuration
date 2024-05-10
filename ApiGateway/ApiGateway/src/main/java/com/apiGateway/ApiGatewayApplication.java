package com.apiGateway;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		Logger logger=LoggerFactory.getLogger(ApiGatewayApplication.class);
		SpringApplication.run(ApiGatewayApplication.class, args);
		logger.info("Hi I am in ApiGatewayApplication");
	}

}
