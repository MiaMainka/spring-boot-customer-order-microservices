package de.leuphana.das.order.repository;

/*
 * ---------------------------------------------------------
 * Author:          Mia Mainka
 * Matrikelnummer:  3047072
 * Modul:           Verteilte Anwendungssysteme
 * Dozent:          Thomas Slotos
 * Semester:        WS 2025/26
 *
 * Interface:       OrderRepository
 *
 * Beschreibung:
 * Repository für Order Entitäten.
 * CRUD Operationen über Spring Data JPA.
 *
 * Technische Details
 * - Java 17
 * - Spring Boot 3.2.12
 * - Spring Data JPA
 * - MariaDB
 * ---------------------------------------------------------
 */

import de.leuphana.das.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // Alle Bestellungen zu einem Customer
    List<Order> findByCustomerId(Long customerId);
}