package de.rene.threebeans.model;

import jakarta.persistence.*;

@Entity
public class Bohne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // --> Auto-increment von Hibernate
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "tageseintrag_id")
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