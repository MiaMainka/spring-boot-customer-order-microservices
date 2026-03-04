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
 * REST API des Customer Service.
 * Nimmt HTTP Requests entgegen und delegiert an CustomerService.
 *
 * 
 * REST Controller und Request Mapping
 * ---------------------------------------------------------
 */

import de.leuphana.das.customer.domain.Customer;
import de.leuphana.das.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // liefert JSON Responses
@RequestMapping("/customers") // Basis Pfad dieses Service
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public Customer create(@RequestBody @Valid Customer customer) {
        // @Valid triggert die Bean Validation Annotationen aus der Entity
        return service.create(customer);
    }

    @GetMapping
    public List<Customer> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable Long id) {
        return service.findById(id);
    }
}