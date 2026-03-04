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
 * JPA Entity für Bestellungen in order_db.
 * Wichtig: customerId ist nur eine ID Referenz, keine JPA Beziehung zu Customer.
 *
 * Database per Service, keine serviceübergreifenden Foreign Keys
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