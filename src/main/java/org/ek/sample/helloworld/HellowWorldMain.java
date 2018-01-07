package org.ek.sample.helloworld;

import org.apache.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;

public class HellowWorldMain {

	private static Logger logger  = Logger.getLogger(HellowWorldMain.class);

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"/META-INF/spring/integration/helloWorldDemo.xml", HellowWorldMain.class);
		MessageChannel inputChannel = context.getBean("inputChannel", MessageChannel.class);
		PollableChannel outputChannel = context.getBean("outputChannel", PollableChannel.class);

		inputChannel.send(new GenericMessage<String>("World"));
		logger.info("==> HelloWorldDemo: " + outputChannel.receive(0).getPayload());

	}

}
