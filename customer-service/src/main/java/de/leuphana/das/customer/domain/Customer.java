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
 * JPA-Entität zur Abbildung eines Kunden in der relationalen
 * Datenbank (customer_db). Diese Klasse definiert die persistente
 * Datenstruktur sowie Validierungsregeln für REST-Eingaben.
 *
 * Technische Details:
 * - Java 17
 * - Spring Boot 3.2.12
 * - Spring Data JPA
 * - MariaDB
 * ---------------------------------------------------------
 */

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
// @Entity markiert diese Klasse als persistente JPA-Entität.
// Spring erkennt sie dadurch als Datenbanktabelle.
@Table(name = "customers")
// @Table legt den konkreten Tabellennamen in der Datenbank fest.
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*
     * @Id definiert das Primärschlüsselfeld.
     * @GeneratedValue mit IDENTITY bedeutet:
     * Die Datenbank erzeugt die ID automatisch (Auto Increment).
     */
    private Long id;

    @NotBlank
    /*
     * @NotBlank sorgt dafür, dass das Feld nicht leer oder null sein darf.
     * Die Validierung erfolgt bei REST-Requests automatisch.
     */
    @Column(nullable = false)
    /*
     * @Column(nullable = false) erzwingt zusätzlich auf Datenbankebene,
     * dass dieses Feld nicht NULL sein darf.
     */
    private String firstName;

    @NotBlank
    @Column(nullable = false)
    private String lastName;

    @Email
    /*
     * @Email validiert, dass das Feld dem Format einer E-Mail-Adresse entspricht.
     */
    @Column(unique = true)
    /*
     * unique = true stellt sicher, dass keine zwei Kunden
     * dieselbe E-Mail-Adresse haben dürfen.
     */
    private String email;

    // Leerer Konstruktor ist für JPA zwingend notwendig.
    public Customer() {
    }

    // Optionaler Konstruktor zur einfacheren Erstellung von Objekten
    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Getter und Setter

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