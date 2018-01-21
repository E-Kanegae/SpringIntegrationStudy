package org.ek.sample.jmx;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

@Component
@ManagedResource
public class StopWatch implements InitializingBean {

	private final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
	private volatile Future<?> future;
	private final AtomicInteger seconds = new AtomicInteger();

	@ManagedAttribute
	public int getSeconds() {
		return this.seconds.get();
	}

	public void afterPropertiesSet() throws Exception {
		this.start();
	}

	@ManagedOperation
	public void start() {
		this.scheduler.initialize();
		this.future = this.scheduler.scheduleAtFixedRate(new Runnable() {
			public void run() {
				seconds.incrementAndGet();
			}
		}, 1000);
	}

	@ManagedOperation
	public void stop() {
		this.future.cancel(true);
	}

	@ManagedOperation
	public void reset() {
		this.seconds.set(0);
	}


}
