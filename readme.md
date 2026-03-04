# Customer Order Microservices

Dieses Repository enthält ein Microservices System mit zwei fachlichen Services (Customer und Order) sowie technischer Infrastruktur (Service Registry und API Gateway).

Das Projekt wurde im Rahmen des Seminars **Verteilte Anwendungssysteme** an der **Leuphana Universität Lüneburg** (WS 2025/26, Dozent Thomas Slotos) umgesetzt. Die Umsetzung orientiert sich am in der Veranstaltung besprochenen Shop Monolith und überführt die fachlichen Bereiche Customer und Order in eigenständige Microservices.

## Kurzüberblick

Services

- `service-registry`  
  Eureka Service Registry für Service Discovery

- `api-gateway`  
  Spring Cloud Gateway als technischer Einstiegspunkt und Router

- `customer-service`  
  REST API und relationale Persistenz für Kunden

- `order-service`  
  REST API und relationale Persistenz für Bestellungen, Validierung der Customer Beziehung via REST (OpenFeign)

Kernidee der Beziehung

- Eine Order referenziert einen Customer ausschließlich über `customerId`
- Der Order Service prüft die Existenz des Kunden über einen REST Call an den Customer Service
- Es gibt keine serviceübergreifenden Foreign Keys in der Datenbank

## Tech Stack

- Java 17  
- Spring Boot 3.2.12  
- Spring Cloud 2023.0.5  
- Spring Data JPA  
- MariaDB (lokal über XAMPP)  
- Maven

## Architektur

```mermaid
flowchart LR
  T[Test oder Client] --> G[API Gateway]
  G --> C[Customer Service]
  G --> O[Order Service]
  C --> DB1[(customer_db)]
  O --> DB2[(order_db)]
  C --> R[Eureka Registry]
  O --> R
  G --> R
  O -->|OpenFeign REST call| C

Ports
* Eureka Registry: 8761
* API Gateway: 8080
* Customer Service: 8081
* Order Service: 8082
API Endpunkte
Customer Service
* POST /customers
* GET /customers
* GET /customers/{id}
Order Service
* POST /orders
* GET /orders
* GET /orders/{id}
* GET /orders/by-customer/{customerId}
Hinweis
Beim POST /orders ruft der Order Service den Customer Service auf, um die Existenz des referenzierten Customers zu prüfen.
Lokales Setup
1. Datenbank starten
Dieses Projekt nutzt MariaDB über XAMPP.
* XAMPP öffnen
* Datenbankdienst starten
* phpMyAdmin öffnen
2. Datenbanken und Rechte initialisieren
Lege im Repo Root einen Ordner an
db
Erstelle darin die Datei
init.sql
Kopiere dort das SQL aus dem Abschnitt SQL Initialisierung unten hinein.
Danach in phpMyAdmin
* Tab SQL öffnen
* Inhalt von db/init.sql ausführen
Ergebnis
* Datenbanken customer_db und order_db existieren
* User shop hat Zugriff auf beide Datenbanken
3. Services starten
Startreihenfolge
1. service-registry
2. api-gateway
3. customer-service
4. order-service
Eureka Dashboard
http://localhost:8761
Nach dem Start sollten dort sichtbar sein
* API-GATEWAY
* CUSTOMER-SERVICE
* ORDER-SERVICE
4. Test und Demonstration
Im Seminar war keine GUI erforderlich. Der Ablauf wird über einen Integrationstest demonstriert.
Testidee
1. Customer anlegen
2. Order für diesen Customer anlegen
3. Prüfung, dass der Order Service die Customer Beziehung über REST auflöst
Test ausführen
* Alle Services laufen lassen
* In Eclipse im order-service die Klasse OrderServiceApplicationTests als JUnit Test starten
Hinweise für Reviewer
Wenn man nur einen Teil des Systems starten möchte
* Registry muss laufen, wenn Services sich registrieren sollen
* Customer und Order lassen sich auch direkt über ihre Ports ansprechen
* Gateway ist für Routing gedacht, aber für den JUnit Test nicht zwingend erforderlich
SQL Initialisierung
Datei
db/init.sql
CREATE DATABASE IF NOT EXISTS customer_db;
CREATE DATABASE IF NOT EXISTS order_db;

CREATE USER IF NOT EXISTS 'shop'@'localhost' IDENTIFIED BY 'shop';

GRANT ALL PRIVILEGES ON customer_db.* TO 'shop'@'localhost';
GRANT ALL PRIVILEGES ON order_db.* TO 'shop'@'localhost';

FLUSH PRIVILEGES;
Hinweis
Falls der User shop bereits existiert, ist das unproblematisch. Das Skript verwendet IF NOT EXISTS.
Autor
Mia Mainka
Matrikelnummer 3047072
Formularbeginn

Formularende

