package de.rene.threebeans.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bohne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // --> Auto-increment von Hibernate
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "tageseintrag_id") // legt Spalte in der Bohne-Tabelle fest, die den FK speichert
    @JsonIgnore // mit der Annotation sage ich, dass dieses Feld beim Umwandeln in Json ignoriert werden soll
    private Tageseintrag tageseintrag;

    public Bohne() {
    }

    public Bohne(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Tageseintrag getTageseintrag() {
        return tageseintrag;
    }

    public void setTageseintrag(Tageseintrag tageseintrag) {
        this.tageseintrag = tageseintrag;
    }
}