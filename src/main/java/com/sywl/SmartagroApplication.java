package com.sywl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sywl.mtqq.Mqttutils;

@SpringBootApplication
public class SmartagroApplication {

	@Autowired
	private Mqttutils mqttutils;

	private static SmartagroApplication smartagroApplication;

	@PostConstruct
	public void init()

	{

		smartagroApplication = this;

		smartagroApplication.mqttutils = this.mqttutils;

	}

	public void setMqttutils(Mqttutils mqttutils) {
		this.mqttutils = mqttutils;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SmartagroApplication.class, args);
		smartagroApplication.mqttutils.connection();
	}
}
