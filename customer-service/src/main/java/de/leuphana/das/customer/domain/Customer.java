package de.leuphana.das.customer.domain;

/*
 * ---------------------------------------------------------
 * Author:          Mia Mainka
 * Matrikelnummer:  3047072
 * Modul:           Verteilte Anwendungssysteme
 * Dozent:          Thomas Slotos
 * Semester:        WS 2025/26
 *
 * Klasse:          Customer
 *
 * Beschreibung:
 * JPA Entity für Kunden in der relationalen Datenbank customer_db.
 * Validierungsannotationen werden bei REST Requests über @Valid geprüft.
 *
 * 
 * JPA Mapping und Bean Validation
 * ---------------------------------------------------------
 */

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity // Entity wird von JPA in eine Tabelle gemappt
@Table(name = "customers") // expliziter Tabellenname
public class Customer {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Identity bedeutet die Datenbank erzeugt die ID automatisch
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @Column(nullable = false)
    private String lastName;

    @Email
    @NotBlank
    @Column(unique = true)
    // Unique Constraint hilft Duplikate auf Datenbankebene zu vermeiden
    private String email;

    // leerer Konstruktor ist für JPA notwendig
    public Customer() {
    }

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}