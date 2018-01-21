package org.ek.sample.jms;

import org.springframework.integration.annotation.MessageEndpoint;

@MessageEndpoint
public class DemoBean {

	public String upperCase(String input) {
		return "JMS response: " + input.toUpperCase();
	}
}
