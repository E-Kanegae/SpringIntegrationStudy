package org.ek.sample.jms;

import java.io.File;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JmsSampleMain {

	private static final Logger LOGGER = Logger.getLogger(JmsSampleMain.class);

	private final static String[] configFilesGatewayDemo = {
		"/META-INF/spring/integration/common.xml",
		"/META-INF/spring/integration/inboundGateway.xml",
		"/META-INF/spring/integration/outboundGateway.xml"
	};

	private final static String[] configFilesChannelAdapterDemo = {
		"/META-INF/spring/integration/common.xml",
		"/META-INF/spring/integration/inboundChannelAdapter.xml",
		"/META-INF/spring/integration/outboundChannelAdapter.xml"
	};

	private final static String[] configFilesAggregationDemo = {
		"/META-INF/spring/integration/common.xml",
		"/META-INF/spring/integration/aggregation.xml"
	};


	public static void main(String[] args) {
		@SuppressWarnings("resource")
		final Scanner scanner = new Scanner(System.in);

		System.out.println("\n========================================================="
				+ "\n                                                         "
				+ "\n    Welcome to the Spring Integration JMS Sample!        "
				+ "\n                                                         "
				+ "\n    For more information please visit:                   "
				+ "\n    http://www.springintegration.org/                    "
				+ "\n                                                         "
				+ "\n=========================================================" );

		LOGGER.info("Refreshing ActiveMQ data directory.");
		File activeMqTempDir = new File("activemq-data");
		deleteDir(activeMqTempDir);

		System.out.println("\n    Which Demo would you like to run? <enter>:\n");
		System.out.println("\t1. Channel Adapter Demo");
		System.out.println("\t2. Gateway Demo");
		System.out.println("\t3. Aggregation Demo");

		while (true) {
			final String input = scanner.nextLine();

			if("1".equals(input.trim())) {
				System.out.println("    Loading Channel Adapter Demo...");
				new ClassPathXmlApplicationContext(configFilesChannelAdapterDemo, JmsSampleMain.class);
				break;
			}
			else if("2".equals(input.trim())) {
				System.out.println("    Loading Gateway Demo...");
				new ClassPathXmlApplicationContext(configFilesGatewayDemo, JmsSampleMain.class);
				break;
			}
			else if("3".equals(input.trim())) {
				System.out.println("    Loading Aggregation Demo...");
				new ClassPathXmlApplicationContext(configFilesAggregationDemo, JmsSampleMain.class);
				break;
			}
			else {
				System.out.println("Invalid choice\n\n");
				System.out.print("Enter you choice: ");
			}

		}

		System.out.println("    Please type something and hit <enter>\n");

	}

	private static void deleteDir(File directory){
		if (directory.exists()){
			String[] children = directory.list();
			if (children != null){
				for (int i=0; i < children.length; i++) {
					deleteDir(new File(directory, children[i]));
				}
			}
		}
		directory.delete();
	}

}
