package de.leuphana.das.customer.web;

/*
 * ---------------------------------------------------------
 * Author:          Mia Mainka
 * Matrikelnummer:  3047072
 * Modul:           Verteilte Anwendungssysteme
 * Dozent:          Thomas Slotos
 * Semester:        WS 2025/26
 *
 * Klasse:          CustomerController
 *
 * Beschreibung:
 * REST-Controller für den Customer Microservice.
 * Stellt Endpunkte zum Anlegen und Auslesen von Kunden bereit.
 *
 * Technische Details:
 * - Java 17
 * - Spring Boot 3.2.12
 * - Spring Web (REST)
 * ---------------------------------------------------------
 */

import de.leuphana.das.customer.domain.Customer;
import de.leuphana.das.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @RestController: Gibt JSON zurück und registriert die Klasse als REST-Controller.
@RequestMapping("/customers")
// Basis-Pfad für alle Endpunkte in dieser Klasse.
public class CustomerController {

    private final CustomerService service;

    // Constructor Injection: Controller hängt nur vom Service ab.
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public Customer create(@RequestBody @Valid Customer customer) {
        // @RequestBody: JSON → Java Objekt, @Valid: Bean Validation aus der Entity ausführen.
        return service.create(customer);
    }

    @GetMapping
    public List<Customer> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable Long id) {
        // @PathVariable liest die ID aus der URL.
        return service.findById(id);
    }
}