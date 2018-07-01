package org.ek.sample.jms.gateway;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GatewayMain {

	private final static String[] configFilesGatewayDemo = {
			"/META-INF/spring/integration/common/common.xml",
			"/META-INF/spring/integration/gateway/inboundGateway.xml",
			"/META-INF/spring/integration/gateway/outboundGateway.xml"
		};
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext(configFilesGatewayDemo, GatewayMain.class);
	}

}
