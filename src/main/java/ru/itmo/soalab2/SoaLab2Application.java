package ru.itmo.soalab2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.itmo.soalab2.controller.CitiesSoapController;

import javax.xml.ws.Endpoint;

@SpringBootApplication
@EnableScheduling
public class SoaLab2Application {

	public static void main(String[] args) {
		SpringApplication.run(SoaLab2Application.class, args);
		Endpoint.publish(
				"http://localhost:8083/soap_cities_controller",
				new CitiesSoapController());
	}

}
