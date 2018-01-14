package org.ek.sample.polling;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PollingMain {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("/META-INF/spring/integration/delay.xml");
	}

}
