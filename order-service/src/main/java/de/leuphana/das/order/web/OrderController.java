package de.leuphana.das.order.web;

/*
 * ---------------------------------------------------------
 * Author:          Mia Mainka
 * Matrikelnummer:  3047072
 * Modul:           Verteilte Anwendungssysteme
 * Dozent:          Thomas Slotos
 * Semester:        WS 2025/26
 *
 * Klasse:          OrderController
 *
 * Beschreibung:
 * REST Endpunkte für Bestellungen.
 * ---------------------------------------------------------
 */

import de.leuphana.das.order.domain.Order;
import de.leuphana.das.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public Order create(@RequestBody @Valid Order order) {
        return service.create(order);
    }

    @GetMapping
    public List<Order> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/by-customer/{customerId}")
    public List<Order> findByCustomerId(@PathVariable Long customerId) {
        return service.findByCustomerId(customerId);
    }
}