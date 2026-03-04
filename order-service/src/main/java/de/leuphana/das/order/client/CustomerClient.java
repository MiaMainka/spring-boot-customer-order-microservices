package de.leuphana.das.order.client;

/*
 * ---------------------------------------------------------
 * Author:          Mia Mainka
 * Matrikelnummer:  3047072
 * Modul:           Verteilte Anwendungssysteme
 * Dozent:          Thomas Slotos
 * Semester:        WS 2025/26
 *
 * Interface:       CustomerClient
 *
 * Beschreibung:
 * Feign Client Interface für REST Aufrufe an den Customer Service.
 * Der Name entspricht dem spring.application.name des Customer Service und damit dem Eureka Service Namen.
 *
 * Feign als deklarativer REST Client und Service Discovery über Eureka
 * ---------------------------------------------------------
 */
import de.leuphana.das.order.web.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerClient {

    @GetMapping("/customers/{id}")
    OrderDto getCustomerById(@PathVariable("id") Long id);
}