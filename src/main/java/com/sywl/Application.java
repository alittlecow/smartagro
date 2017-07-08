package com.sywl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sywl.mtqq.Mqttutils;

@SpringBootApplication
public class Application {

	@Autowired
	private Mqttutils mqttutils;

	private static Application application;

	@PostConstruct
	public void init()

	{

		application = this;

		application.mqttutils = this.mqttutils;

	}

	public void setMqttutils(Mqttutils mqttutils) {
		this.mqttutils = mqttutils;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		application.mqttutils.connection();
	}
}
