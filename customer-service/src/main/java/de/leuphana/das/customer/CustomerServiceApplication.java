package de.leuphana.das.customer;
/*
 * ---------------------------------------------------------
 * Author:          Mia Mainka
 * Matrikelnummer:  3047072
 * Modul:           Verteilte Anwendungssysteme
 * Dozent:          Thomas Slotos
 * Semester:        WS 2025/26
 *
 * Klasse:          CustomerServiceApplication
 *
 * Beschreibung:
 * Startklasse des Customer Microservice.
 * Das Paket de.leuphana.das.customer ist die Scan Basis, damit Spring domain repository service web automatisch findet.
 *
 * 
 * Fachlicher Microservice und Component Scan
 * ---------------------------------------------------------
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

}
