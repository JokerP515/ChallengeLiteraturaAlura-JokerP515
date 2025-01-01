package com.alurachallenge.literatura.jokerp515.challenge_literatura_jokerp515.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> languages;

    private Integer downloadCount;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book() {
    }

    public Book(String title, List<String> languages, Integer downloadCount, Author author) {
        this.title = title;
        this.languages = languages;
        this.downloadCount = downloadCount;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString(){
        return "Descripción del libro:" +
                "\nTitulo: " + title +
                "\nIdiomas: " + languages +
                "\nNúmero de Descargas: " + downloadCount +
                "\nAutor: " + author+"\n\n";
    }

}