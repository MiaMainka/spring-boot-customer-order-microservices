package de.leuphana.das.order.service;

/*
 * ---------------------------------------------------------
 * Author:          Mia Mainka
 * Matrikelnummer:  3047072
 * Modul:           Verteilte Anwendungssysteme
 * Dozent:          Thomas Slotos
 * Semester:        WS 2025/26
 *
 * Klasse:          OrderService
 *
 * Beschreibung:
 * Geschäftslogik für Orders.
 * Vor dem Speichern wird der Customer Service per REST aufgerufen, um die Existenz zu prüfen.
 *
 * REST Beziehung zwischen fachlichen Microservices
 * ---------------------------------------------------------
 */

import de.leuphana.das.order.client.CustomerClient;
import de.leuphana.das.order.domain.Order;
import de.leuphana.das.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final CustomerClient customerClient;

    public OrderService(OrderRepository repository, CustomerClient customerClient) {
        this.repository = repository;
        this.customerClient = customerClient;
    }

    public Order create(Order order) {
        // zentrale Stelle der Service Beziehung: Order ruft Customer über REST auf
        customerClient.getCustomerById(order.getCustomerId());

        return repository.save(order);
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Order findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    public List<Order> findByCustomerId(Long customerId) {
        return repository.findByCustomerId(customerId);
    }
}