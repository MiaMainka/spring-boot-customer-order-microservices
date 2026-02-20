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
 * Integrationstest zur Demonstration der REST Kommunikation
 * zwischen Customer Service und Order Service.
 *
 * Ablauf:
 * 1) Customer per HTTP anlegen
 * 2) Order mit dieser customerId anlegen
 * 3) Prüfen, ob beide erfolgreich erstellt wurden
 * ---------------------------------------------------------
 */

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceApplicationTests {

    // RestTemplate ermöglicht HTTP Requests innerhalb des Tests
    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    void shouldCreateCustomerThenCreateOrderForCustomer() {

        // ----------------------------
        // 1) Customer anlegen
        // ----------------------------

        CustomerCreateRequest customerRequest =
                new CustomerCreateRequest(
                        "Mia",
                        "Mainka",
                        "mia.junit." + System.currentTimeMillis() + "@leuphana.de"
                );

        // HTTP Header für JSON setzen
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Request + Header zusammenführen
        HttpEntity<CustomerCreateRequest> customerEntity =
                new HttpEntity<>(customerRequest, headers);

        // HTTP POST auf Customer Service
        ResponseEntity<CustomerResponse> customerResponse =
                restTemplate.exchange(
                        "http://localhost:8081/customers",
                        HttpMethod.POST,
                        customerEntity,
                        CustomerResponse.class
                );

        // Prüfen, ob der Request erfolgreich war
        assertEquals(HttpStatus.OK, customerResponse.getStatusCode());
        assertNotNull(customerResponse.getBody());
        assertNotNull(customerResponse.getBody().id);

        Long customerId = customerResponse.getBody().id;

        // ----------------------------
        // 2) Order anlegen
        // ----------------------------

        OrderCreateRequest orderRequest =
                new OrderCreateRequest(customerId, 49.99);

        HttpEntity<OrderCreateRequest> orderEntity =
                new HttpEntity<>(orderRequest, headers);

        // HTTP POST auf Order Service
        ResponseEntity<OrderResponse> orderResponse =
                restTemplate.exchange(
                        "http://localhost:8082/orders",
                        HttpMethod.POST,
                        orderEntity,
                        OrderResponse.class
                );

        // Prüfen, ob Order erfolgreich erstellt wurde
        assertEquals(HttpStatus.OK, orderResponse.getStatusCode());
        assertNotNull(orderResponse.getBody());
        assertEquals(customerId, orderResponse.getBody().customerId);
    }

    // -------------------------------------------------
    // Einfache DTO Klassen für JSON Mapping im Test
    // -------------------------------------------------

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