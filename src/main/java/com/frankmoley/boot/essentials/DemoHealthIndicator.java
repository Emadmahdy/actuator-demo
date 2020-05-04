package com.frankmoley.boot.essentials;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class DemoHealthIndicator implements HealthIndicator{

	@Override
	public Health health() {
		// TODO Auto-generated method stub
		return Health.up().withDetail("reason", "test").build();
	}

}
