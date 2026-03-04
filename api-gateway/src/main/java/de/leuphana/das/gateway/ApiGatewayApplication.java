package de.leuphana.das.gateway;

/*
 * ---------------------------------------------------------
 * Author:          Mia Mainka
 * Matrikelnummer:  3047072
 * Modul:           Verteilte Anwendungssysteme
 * Dozent:          Thomas Slotos
 * Semester:        WS 2025/26
 *
 * Klasse:          ApiGatewayApplication
 *
 * Beschreibung:
 * Startklasse des API Gateways.
 * Das Gateway kann als zentraler Einstiegspunkt dienen und Requests zu Services weiterleiten.
 *
 * API Gateway als technische Infrastruktur
 * ---------------------------------------------------------
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
