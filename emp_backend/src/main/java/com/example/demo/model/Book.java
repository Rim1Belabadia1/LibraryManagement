package com.example.demo.model;

import java.util.Date;
import jakarta.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private String description;
    private String editeur;
    private String auteur;

    @Temporal(TemporalType.DATE)
    private Date dateDePublication;

    @Lob // Utilisé pour les grandes quantités de données binaires
    private byte[] photo;

    private String availability;

    // Constructeurs, getters et setters
    public Book() {}

    public Book(String name, String type, String description, String editeur, String auteur, Date dateDePublication, byte[] photo, String availability) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.editeur = editeur;
        this.auteur = auteur;
        this.dateDePublication = dateDePublication;
        this.photo = photo;
        this.availability = availability;
    }

    public Book(long l, String bookTitle, String bookType, String bookDescription, String bookPublisher, String bookAuthor, String date, boolean b, byte[] bytes) {
    }

    public String getAvailability() { // Correction du nom du getter
        return availability;
    }

    public void setAvailability(String availability) { // Correction du nom du setter
        this.availability = availability;
    }

    // Getters et setters restants
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Date getDateDePublication() {
        return dateDePublication;
    }

    public void setDateDePublication(Date dateDePublication) {
        this.dateDePublication = dateDePublication;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
