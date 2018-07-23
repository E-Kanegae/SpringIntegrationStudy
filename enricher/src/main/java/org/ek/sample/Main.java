/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ek.sample;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.ek.sample.domain.User;
import org.ek.sample.service.UserService;


/**
 * Starts the Spring Context and will initialize the Spring Integration routes.
 *
 * @author E-Kanegae
 * @since 1.0
 *
 */
public final class Main {

	private static final Logger LOGGER = Logger.getLogger(Main.class);
	private Main() { }

	/**
	 * Load the Spring Integration Application Context
	 *
	 * @param args - command line arguments
	 */
	public static void main(final String... args) {

		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("\n========================================================="
					  + "\n                                                         "
					  + "\n          Welcome to Spring Integration!                 "
					  + "\n                                                         "
					  + "\n    For more information please visit:                   "
					  + "\n    http://www.springsource.org/spring-integration       "
					  + "\n                                                         "
					  + "\n=========================================================" );
		}

		final AbstractApplicationContext context =
				new ClassPathXmlApplicationContext("classpath:META-INF/spring/integration/*-context.xml");

		context.registerShutdownHook();

		final Scanner scanner = new Scanner(System.in);

		final UserService service = context.getBean(UserService.class);

		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("\n========================================================="
					  + "\n                                                         "
					  + "\n    Please press 'q + Enter' to quit the application.    "
					  + "\n                                                         "
					  + "\n=========================================================" );
		}

		System.out.print("Please enter a string and press <enter>: ");

		while (true) {

			final String input = scanner.nextLine();

			if("q".equals(input.trim())) {
				break;
			}

			User user = new User();

			if ("1".equals(input)) {

				final User fullUser = service.findUser(user);
				printUserInformation(fullUser);

			} else if ("2".equals(input)) {

				final User fullUser = service.findUserByUsername(user);
				printUserInformation(fullUser);

			} else if ("3".equals(input)) {

				final Map<String, Object> userData = new HashMap<String, Object>();
				userData.put("username", "foo_map");
				userData.put("username2", "bar_map");
				final Map<String, Object> enrichedUserData = service.findUserWithUsernameInMap(userData);
				printUserFullInformation(enrichedUserData);

			} else if("4".equals(input)){
				MessageChannel mc = context.getBean("tryHeaderEnricherChannel", MessageChannel.class);
				PollableChannel pc = context.getBean("tryHeaderEnricherPollarChannel", PollableChannel.class);
				mc.send(new GenericMessage<String>("foo.bar"));
				printHeaderInfo(pc.receive().getHeaders());				
				
			} else{			
				LOGGER.info("\n\n    Please enter '1', '2', or '3' <enter>:\n\n");
			}			
		}
		
		LOGGER.info("\n\nExiting application...bye.");

		scanner.close();
		context.close();

	}
	
	private static void printUserInformation(User user) {

		if (user != null) {
			LOGGER.info(String.format("\n\n    User found - Username: '%s',  Email: '%s', Password: '%s'.\n\n",
					user.getUsername(), user.getEmail(), user.getPassword()));

		} else {
			LOGGER.info("\n\n    No User found for username: 'foo'.\n\n");
		}
	}
	
	private static void printUserFullInformation(Map<String, Object> userdataMap) {
		for(Map.Entry<String, Object> entry : userdataMap.entrySet()) {
			Object val = entry.getValue();
			if(val instanceof User) {
				User user = (User) entry.getValue();
				LOGGER.info(String.format("\n\n    User found - Key of Map: '%s',  Username: '%s',  Email: '%s', Password: '%s'.\n\n",
						entry.getKey(), user.getUsername(), user.getEmail(), user.getPassword()));
			}else {
				LOGGER.info(String.format("\n\n    User found - Key of Map: '%s',  Username: '%s',  Email: '%s', Password: '%s'.\n\n",
						entry.getKey(), val, null, null));
			}
		}
	}
	
	private static void printHeaderInfo(MessageHeaders mh) {
		LOGGER.info("\n\n    " + "headerTest :" + mh.get("headerTest") + ", addedHeader :" + mh.get("addedHeader"));
	}

	
}
