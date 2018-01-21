package org.ek.sample.jmx;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JmxAdapterDemoTest {

	@Test
	public void testJmxAdapterDemo() throws Exception{
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"/META-INF/spring/integration/JmxAdapterDemo-context.xml", JmxAdapterDemoTest.class);
		Thread.sleep(50000);
		context.stop();
	}

}
