package de.leuphana.das.registry;

/*
 * ---------------------------------------------------------
 * Author:          Mia Mainka
 * Matrikelnummer:  3047072
 * Modul:           Verteilte Anwendungssysteme
 * Dozent:          Thomas Slotos
 * Semester:        WS 2025/26
 *
 * Klasse:          ServiceRegistryApplication
 *
 * Beschreibung:
 * Startklasse der Service Registry.
 * Eureka verwaltet welche Services verfügbar sind und wo sie laufen.
 *
 * 
 * Service Registry und Service Discovery
 * ---------------------------------------------------------
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // macht diese Anwendung zum Eureka Server
public class ServiceRegistryApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServiceRegistryApplication.class, args);
  }
}