package de.leuphana.das.order;

/*
 * ---------------------------------------------------------
 * Author:          Mia Mainka
 * Matrikelnummer:  3047072
 * Modul:           Verteilte Anwendungssysteme
 * Dozent:          Thomas Slotos
 * Semester:        WS 2025/26
 *
 * Klasse:          OrderServiceApplicationTests
 *
 * Beschreibung:
 * Integrationstest über HTTP.
 * Der Test demonstriert den geforderten Ablauf:
 * zuerst Customer anlegen, danach Order für diesen Customer anlegen.
 *
 * Prüfungsanker:
 * Integrationstest statt GUI, REST Aufrufe zwischen Services
 * ---------------------------------------------------------
 */

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceApplicationTests {

    // RestTemplate sendet HTTP Requests, damit der Ablauf wie bei einer echten verteilten Nutzung getestet wird
    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    void shouldCreateCustomerThenCreateOrderForCustomer() {

        // Schritt 1: Customer per HTTP im Customer Service anlegen
        CustomerCreateRequest customerRequest =
                new CustomerCreateRequest(
                        "Mia",
                        "Mainka",
                        "mia.junit." + System.currentTimeMillis() + "@leuphana.de"
                );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CustomerCreateRequest> customerEntity =
                new HttpEntity<>(customerRequest, headers);

        ResponseEntity<CustomerResponse> customerResponse =
                restTemplate.exchange(
                        "http://localhost:8081/customers",
                        HttpMethod.POST,
                        customerEntity,
                        CustomerResponse.class
                );

        assertEquals(HttpStatus.OK, customerResponse.getStatusCode());
        assertNotNull(customerResponse.getBody());
        assertNotNull(customerResponse.getBody().id);

        Long customerId = customerResponse.getBody().id;

        // Schritt 2: Order per HTTP im Order Service anlegen, Order Service validiert Customer über Feign
        OrderCreateRequest orderRequest =
                new OrderCreateRequest(customerId, 49.99);

        HttpEntity<OrderCreateRequest> orderEntity =
                new HttpEntity<>(orderRequest, headers);

        ResponseEntity<OrderResponse> orderResponse =
                restTemplate.exchange(
                        "http://localhost:8082/orders",
                        HttpMethod.POST,
                        orderEntity,
                        OrderResponse.class
                );

        assertEquals(HttpStatus.OK, orderResponse.getStatusCode());
        assertNotNull(orderResponse.getBody());
        assertEquals(customerId, orderResponse.getBody().customerId);
    }

    // DTOs im Test sind bewusst minimal, damit der Test unabhängig von internen Klassen bleibt

    static class CustomerCreateRequest {
        public String firstName;
        public String lastName;
        public String email;

        public CustomerCreateRequest(String firstName, String lastName, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }
    }

    static class CustomerResponse {
        public Long id;
        public String firstName;
        public String lastName;
        public String email;
    }

    static class OrderCreateRequest {
        public Long customerId;
        public Double totalAmount;

        public OrderCreateRequest(Long customerId, Double totalAmount) {
            this.customerId = customerId;
            this.totalAmount = totalAmount;
        }
    }

    static class OrderResponse {
        public Long id;
        public Long customerId;
        public Double totalAmount;
        public String createdAt;
    }
}