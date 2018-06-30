package org.ek.sample.polliing_helloworld;

import org.apache.log4j.Logger;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;

public class PollingHelloWorld {

	private static Logger logger  = Logger.getLogger(PollingHelloWorld.class);

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		AbstractApplicationContext context = 
				new ClassPathXmlApplicationContext("/META-INF/spring/integration/delay_hello_world.xml");
		
		PollableChannel bridgeChannel = context.getBean("bridgeChannel", PollableChannel.class);
		MessageChannel inputChannel = context.getBean("inputChannel", MessageChannel.class);
		PollableChannel outputChannel = context.getBean("outputChannel", PollableChannel.class);

		while(true) {
			Message<?> msg = bridgeChannel.receive(0);			
			if(msg != null) {
				String str = (String) msg.getPayload() ;
				inputChannel.send(new GenericMessage<String>(str));
				logger.info("==> Demo: " + outputChannel.receive(0).getPayload());
			}
			
		}
	}

}
