package de.leuphana.das.customer.service;

/*
 * ---------------------------------------------------------
 * Author:          Mia Mainka
 * Matrikelnummer:  3047072
 * Modul:           Verteilte Anwendungssysteme
 * Dozent:          Thomas Slotos
 * Semester:        WS 2025/26
 *
 * Klasse:          CustomerService
 *
 * Beschreibung:
 * Service-Komponente zur Kapselung der Geschäftslogik im Customer Microservice.
 * Enthält Operationen zum Anlegen und Auslesen von Kunden und nutzt dafür das Repository.
 *
 * Technische Details
 * - Java 17
 * - Spring Boot 3.2.12
 * - Spring Data JPA
 * ---------------------------------------------------------
 */

import de.leuphana.das.customer.domain.Customer;
import de.leuphana.das.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// @Service registriert die Klasse als Spring Bean (Business-Schicht).
public class CustomerService {

    private final CustomerRepository repository;

    // Constructor Injection macht die Abhängigkeit eindeutig.
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer create(Customer customer) {
        // Email soll eindeutig sein (zusätzlich zur DB-Constraint).
        repository.findByEmail(customer.getEmail()).ifPresent(existing -> {
            throw new IllegalArgumentException("Customer with this email already exists");
        });

        return repository.save(customer);
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Customer findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    }
}