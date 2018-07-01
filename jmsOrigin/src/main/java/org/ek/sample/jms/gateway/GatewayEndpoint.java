package org.ek.sample.jms.gateway;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;

@EnableIntegration
@MessageEndpoint
public class GatewayEndpoint {

	@ServiceActivator(inputChannel="requestChannel")
	public String upperCase(String input) {
		return "JMS response: " + input.toUpperCase();
	}
}
