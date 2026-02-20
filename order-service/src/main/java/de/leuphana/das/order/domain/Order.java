package de.leuphana.das.order.domain;

/*
 * ---------------------------------------------------------
 * Author:          Mia Mainka
 * Matrikelnummer:  3047072
 * Modul:           Verteilte Anwendungssysteme
 * Dozent:          Thomas Slotos
 * Semester:        WS 2025/26
 *
 * Klasse:          Order
 *
 * Beschreibung:
 * JPA-Entität zur Abbildung einer Bestellung.
 * Referenziert einen Customer über dessen ID.
 *
 * Technische Details:
 * - Java 17
 * - Spring Boot 3.2.12
 * - Spring Data JPA
 * - MariaDB
 * ---------------------------------------------------------
 */

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Long customerId;
    // Referenz auf Customer Service 

    @NotNull
    @Column(nullable = false)
    private Double totalAmount;

    private LocalDateTime createdAt;

    public Order() {
    }

    public Order(Long customerId, Double totalAmount) {
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}