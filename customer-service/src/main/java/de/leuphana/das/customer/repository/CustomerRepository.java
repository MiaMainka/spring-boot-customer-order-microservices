package de.leuphana.das.customer.repository;

/*
 * ---------------------------------------------------------
 * Author:          Mia Mainka
 * Matrikelnummer:  3047072
 * Modul:           Verteilte Anwendungssysteme
 * Dozent:          Thomas Slotos
 * Semester:        WS 2025/26
 *
 * Interface:       CustomerRepository
 *
 * Beschreibung:
 * Spring Data JPA Repository als Interface.
 * Spring erzeugt die Implementierung zur Laufzeit automatisch.
 *
 * 
 * Repository Pattern und Spring Data JPA
 * ---------------------------------------------------------
 */

import de.leuphana.das.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Derived Query Method, Spring leitet die Query aus dem Methodennamen ab
    Optional<Customer> findByEmail(String email);
}