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
 * Enthält die Geschäftslogik für Customer.
 * Controller delegiert an diese Schicht statt direkt an das Repository.
 *
 * 
 * Trennung Controller Service Repository
 * ---------------------------------------------------------
 */

import de.leuphana.das.customer.domain.Customer;
import de.leuphana.das.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Business Schicht als Spring Bean
public class CustomerService {

    private final CustomerRepository repository;

    // Constructor Injection ist bewusst gewählt, da Abhängigkeiten klar sind
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer create(Customer customer) {
        // einfache Business Regel, vor DB Unique Constraint schon prüfen
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