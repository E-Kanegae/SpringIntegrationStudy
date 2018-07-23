package org.ek.sample.service.impl;

import org.apache.log4j.Logger;
import org.ek.sample.domain.User;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;

@MessageEndpoint
@EnableIntegration
public class UserServiceEnricher {
	
	private static final Logger LOGGER = Logger.getLogger(UserServiceEnricher.class);
	
	@ServiceActivator(inputChannel="findUserServiceChannel")
	public User findUser(User user) {

		LOGGER.info(String.format("Calling method 'findUser' with parameter %s", user));

		return new User(user.getUsername() + "-test",//usernameで付け足している文字列は反映されない
				   "secret",
				   user.getUsername() + "@springintegration.org");
	}

	@ServiceActivator(inputChannel="findUserByUsernameServiceChannel")
	public User findUserByUsername(String username) {

		LOGGER.info(String.format("Calling method 'findUserByUsername' with parameter: %s", username));

		return new User(username, "secret", username + "@springintegration.org");

	}


}
