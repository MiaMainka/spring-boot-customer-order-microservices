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
 * Spring Data JPA Repository für Orders.
 * Die Implementierung wird von Spring automatisch erzeugt.
 *
 * Repository Interface und Query Ableitung
 * ---------------------------------------------------------
 */

import de.leuphana.das.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // Query wird aus dem Methodennamen abgeleitet
    List<Order> findByCustomerId(Long customerId);
}