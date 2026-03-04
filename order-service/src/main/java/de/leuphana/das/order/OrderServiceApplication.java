package de.leuphana.das.order;

/*
 * ---------------------------------------------------------
 * Author:          Mia Mainka
 * Matrikelnummer:  3047072
 * Modul:           Verteilte Anwendungssysteme
 * Dozent:          Thomas Slotos
 * Semester:        WS 2025/26
 *
 * Klasse:          OrderServiceApplication
 *
 * Beschreibung:
 * Startklasse des Order Microservice.
 * Feign wird genutzt, um den Customer Service per REST aufzurufen.
 *
 * REST Kommunikation zwischen Microservices
 * ---------------------------------------------------------
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // aktiviert Feign Client Interfaces in diesem Service
public class OrderServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(OrderServiceApplication.class, args);
  }
}