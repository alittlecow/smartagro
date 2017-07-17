package com.sywl;

import com.sywl.mtqq.Mqttutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

import static com.sywl.utils.BeeCloudUtils.initBeeCloud;

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

//		initBeeCloud();

	}

	public void setMqttutils(Mqttutils mqttutils) {
		this.mqttutils = mqttutils;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		application.mqttutils.connection();
	}
}
