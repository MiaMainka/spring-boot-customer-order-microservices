# Spring Boot Customer Order Microservices

A distributed microservices system built with Spring Boot and Spring Cloud.

This project demonstrates a basic microservices architecture consisting of:

- Service Registry (Eureka Server)
- API Gateway (Spring Cloud Gateway)
- Customer Service
- Order Service

The Order Service communicates with the Customer Service via REST and validates the existence of a customer before creating an order.

The system uses:

- Spring Boot
- Spring Cloud (Eureka, Gateway)
- Spring Data JPA
- Relational Databases
- RESTful communication
- JUnit testing for integration validation

---

## Architecture Overview

The system consists of four independent Spring Boot applications:

- `service-registry`
- `api-gateway`
- `customer-service`
- `order-service`

Each service is independently deployable and registered through Eureka.

The API Gateway routes external requests to the corresponding services.

---

## Technologies

- Java 17
- Spring Boot 3.x
- Spring Cloud 2023.x
- Maven
- JPA / Hibernate
- Relational Database (MariaDB or H2)
- REST
- JUnit

---

## Project Purpose

This project demonstrates:

- Decomposition of a monolithic application into microservices
- Service discovery using Eureka
- REST based service communication
- Separation of component, connector, and configuration layers
- Infrastructure setup for distributed systems

---

## Running the System

Start services in the following order:

1. service-registry
2. api-gateway
3. customer-service
4. order-service

After startup:

- Eureka Dashboard: http://localhost:8761
- Gateway Endpoint: http://localhost:8080

---

## Example Flow

1. Create a customer
2. Create an order for the created customer
3. Order service validates the customer via REST call

---

## Author

Mia Mainka
