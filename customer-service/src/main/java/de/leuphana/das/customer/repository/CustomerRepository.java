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
 * Repository für Customer Entitäten.
 * Stellt CRUD Operationen über Spring Data JPA bereit.
 *
 * Technische Details:
 * - Java 17
 * - Spring Boot 3.2.12
 * - Spring Data JPA
 * - MariaDB
 * ---------------------------------------------------------
 */

import de.leuphana.das.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Spring erzeugt automatisch eine Query basierend auf dem Methodennamen.
    Optional<Customer> findByEmail(String email);
}